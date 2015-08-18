package org.frusso.groceryservice.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.frusso.groceryservice.GroceryServiceApplication;
import org.frusso.groceryservice.domain.Product;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = GroceryServiceApplication.class)
public class GroceryParserTests {

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Autowired
	private GroceryParser groceryParser;

	@Test
	public void testParseProductWrongURL() throws MalformedURLException {
		String url = "http://wrong-url.com";
		GroceryParser groceryParser = new GroceryParser(new URL(url));
		exception.expect(RuntimeException.class);
		groceryParser.parseProduct();
	}

	@Test
	public void testParseProduct() {
		final Product expected = new Product(
				"Sainsbury's Avocado Ripe & Ready XL Loose 300g",
				"3.63kb",
				1.5,
				"Buy Sainsbury's Avocado Ripe & Ready XL Loose 300g online from Sainsbury's, the same great quality, freshness and choice you'd find in store. Choose from 1 hour delivery slots and collect Nectar points.");

		groceryParser.parseProduct();
		
		final List<Product> products = groceryParser.getGrocery().getProducts();		
		assertTrue(products.contains(expected));
		
		Product found = products.get(products.indexOf(expected));
		assertEquals(expected.getTitle(), found.getTitle());
		assertEquals(expected.getSize(), found.getSize());
		assertEquals(expected.getUnitPrice(), found.getUnitPrice());
		assertEquals(expected.getDescription(), found.getDescription());
	}
}
