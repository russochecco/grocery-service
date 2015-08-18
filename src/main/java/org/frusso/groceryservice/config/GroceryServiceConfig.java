package org.frusso.groceryservice.config;

import java.net.MalformedURLException;
import java.net.URL;

import org.frusso.groceryservice.parser.GroceryParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GroceryServiceConfig {

	@Value("${http.grocery.url}")
	private String url;

	@Bean(name = "defaultGroceryParser", initMethod = "parseProduct")
	public GroceryParser getDefaultGroceryParser() throws MalformedURLException {
		return new GroceryParser(new URL(url));
	}
}
