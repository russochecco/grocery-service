package org.frusso.groceryservice.domain;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

@XmlRootElement(name = "grocery")
public class Grocery {

	@JsonProperty("results")
	private final List<Product> products = new ArrayList<Product>();

	@JsonProperty("total")
	private Double total;

	public Grocery() {
	}

	public List<Product> getProducts() {
		return products;
	}

	public Double getTotal() {
		return total;
	}
	
	public void updateTotal() {
		total = products.stream().mapToDouble(product -> product.getUnitPrice()).sum();
	}
}
