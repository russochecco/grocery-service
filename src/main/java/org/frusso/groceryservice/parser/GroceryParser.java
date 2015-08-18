package org.frusso.groceryservice.parser;

import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.frusso.groceryservice.domain.Grocery;
import org.frusso.groceryservice.domain.Product;

public class GroceryParser {

	private static final Log logger = LogFactory.getLog(GroceryParser.class);
	private static final DecimalFormat decimalFormat = new DecimalFormat("#.##");

	// offset used to access to the fields of interest
	private static final int OFFSET_TITLE = 7;
	private static final int OFFSET_LINK = 6;
	private static final int OFFSET_PRICE = 41;

	// the grocery
	private final Grocery grocery = new Grocery();

	// the URL where retrieve the products
	private URL url;

	public GroceryParser() {
	}
	
	public GroceryParser(URL url) {
		this.url = url;
	}

	public Grocery getGrocery() {
		return grocery;
	}

	public void setURL(URL url) {
		this.url = url;
	}

	public void parseProduct() {
		try {

			// retrieve the products
			HttpGet get = new HttpGet(url.toString());
			get.setHeader("User-Agent", "Mozilla/5.0");
			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpResponse response = httpClient.execute(get);

			// parse the HTML output
			List<String> lines = IOUtils.readLines(response.getEntity().getContent(), "UTF-8");

			// clear the grocery
			grocery.getProducts().clear();

			String line = null;
			String title = null;
			String price = null;
			String link = null;
			String description = null;
			String[] parts = null;
			String subline = null;
			String size = null;

			URL url = null;
			URLConnection urlConnection = null;

			// for each line...
			for (int index = 0; index < lines.size(); index++) {

				// the current line processed
				line = lines.get(index);

				// if the current line is a product tag...
				if (line.contains("<div class=\"product \">")) {

					// title
					title = StringEscapeUtils.unescapeHtml3(lines.get(index + OFFSET_TITLE).trim());
					logger.info("Title '" + title + "'");

					// price
					price = StringEscapeUtils.unescapeHtml3(lines.get(index + OFFSET_PRICE).trim());
					price = price.substring(1, price.indexOf("<"));
					logger.info("Price '" + price + "'");

					// link to access to the product's details page
					link = lines.get(index + OFFSET_LINK).trim().split(" ")[1];
					link = link.substring(link.indexOf("=") + 2, link.length() - 1);
					get = new HttpGet(link);
					get.setHeader("User-Agent", "Mozilla/5.0");
					response = httpClient.execute(get);
					if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK)
						throw new RuntimeException("wrong response");

					// scan all the HTML line of the product's details page
					try (Scanner scanner = new Scanner(response.getEntity().getContent(), "UTF-8")) {
						scanner.useDelimiter(System.getProperty("line.separator"));
						description = null;
						size = null;
						while (scanner.hasNext()) {
							subline = scanner.next();

							// process the description line
							if (subline.contains("description")) {
								parts = subline.split("=");
								description = parts[2];
								description = description.substring(1, description.indexOf("/>") - 1);
								description = StringEscapeUtils.unescapeHtml3(description);
								logger.info("Description '" + description + "'");
							}

							// process the image line
							if (subline.contains("og:image")) {
								parts = subline.split("=");
								link = parts[2];
								link = link.substring(1, link.indexOf("/>") - 2);
								url = new URL(link);
								urlConnection = url.openConnection();
								size = decimalFormat.format(urlConnection.getContentLengthLong() / 1024.0) + "kb";
								logger.info("Size '" + size + "'");
							}

							// avoid to score unnecessary lines
							if (description != null && size != null)
								break;
						}
					}

					// add product to the grocery
					Product product = new Product(title, size, Double.valueOf(price), description);
					grocery.getProducts().add(product);
					logger.info("Added the product '" + product.getTitle() + "'");
				}
			}

			// update the total
			grocery.updateTotal();
			logger.info("Grocery total '" + grocery.getTotal() + "'");
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}
