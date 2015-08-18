package org.frusso.groceryservice.controller.rest;

import static org.junit.Assert.assertEquals;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.frusso.groceryservice.GroceryServiceApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = GroceryServiceApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:8080")
public class GroceryServiceRESTfulTests {

	private static final Log logger = LogFactory.getLog(GroceryServiceRESTfulTests.class);

	private static final String url = "http://localhost:8080/grocery-service/rest";

	private final RestTemplate restTemplate = new RestTemplate();

	private static final String expected = "{\"results\":[{\"title\":\"Sainsbury's Avocado Ripe & Ready XL Loose 300g\",\"size\":\"3.63kb\",\"unit_price\":1.5,\"description\":\"Buy Sainsbury's Avocado Ripe & Ready XL Loose 300g online from Sainsbury's, the same great quality, freshness and choice you'd find in store. Choose from 1 hour delivery slots and collect Nectar points.\"},{\"title\":\"Sainsbury's Avocado, Ripe & Ready x2\",\"size\":\"4.4kb\",\"unit_price\":1.8,\"description\":\"Burgers are a summer must-have and these homemade ones are perfect for a barbecue, topped with cool avocado and served with oven-baked potato wedges. \"},{\"title\":\"Sainsbury's Avocados, Ripe & Ready x4\",\"size\":\"4.53kb\",\"unit_price\":3.2,\"description\":\"Buy Sainsbury's Avocados, Ripe & Ready x4 online from Sainsbury's, the same great quality, freshness and choice you'd find in store. Choose from 1 hour delivery slots and collect Nectar points.\"},{\"title\":\"Sainsbury's Conference Pears, Ripe & Ready x4 (minimum)\",\"size\":\"4.51kb\",\"unit_price\":2.0,\"description\":\"Buy Sainsbury's Conference Pears, Ripe & Ready x4 (minimum) online from Sainsbury's, the same great quality, freshness and choice you'd find in store. Choose from 1 hour delivery slots and collect Nectar points.\"},{\"title\":\"Sainsbury's Kiwi Fruit, Ripe & Ready x4\",\"size\":\"35.21kb\",\"unit_price\":1.8,\"description\":\"Buy Sainsbury's Kiwi Fruit, Ripe & Ready x4 online from Sainsbury's, the same great quality, freshness and choice you'd find in store. Choose from 1 hour delivery slots and collect Nectar points.\"},{\"title\":\"Sainsbury's Mango, Ripe & Ready x2\",\"size\":\"3.94kb\",\"unit_price\":2.0,\"description\":\"Buy Sainsbury's Mango, Ripe & Ready x2 online from Sainsbury's, the same great quality, freshness and choice you'd find in store. Choose from 1 hour delivery slots and collect Nectar points.\"},{\"title\":\"Sainsbury's Nectarines, Ripe & Ready x4\",\"size\":\"17.01kb\",\"unit_price\":2.0,\"description\":\"Buy Sainsbury's Nectarines, Ripe & Ready x4 online from Sainsbury's, the same great quality, freshness and choice you'd find in store. Choose from 1 hour delivery slots and collect Nectar points.\"},{\"title\":\"Sainsbury's Peaches Ripe & Ready x4\",\"size\":\"16.37kb\",\"unit_price\":2.0,\"description\":\"Buy Sainsbury's Peaches Ripe & Ready x4 online from Sainsbury's, the same great quality, freshness and choice you'd find in store. Choose from 1 hour delivery slots and collect Nectar points.\"},{\"title\":\"Sainsbury's Pears, Ripe & Ready x4 (minimum)\",\"size\":\"5.53kb\",\"unit_price\":2.0,\"description\":\"Buy Sainsbury's Pears, Ripe & Ready x4 (minimum) online from Sainsbury's, the same great quality, freshness and choice you'd find in store. Choose from 1 hour delivery slots and collect Nectar points.\"},{\"title\":\"Sainsbury's Plums Ripe & Ready x5\",\"size\":\"13.76kb\",\"unit_price\":2.5,\"description\":\"Buy Sainsbury's Plums Ripe & Ready x5 online from Sainsbury's, the same great quality, freshness and choice you'd find in store. Choose from 1 hour delivery slots and collect Nectar points.\"},{\"title\":\"Sainsbury's Ripe & Ready Golden Plums x6\",\"size\":\"3.91kb\",\"unit_price\":2.5,\"description\":\"Buy Sainsbury's Ripe & Ready Golden Plums x6 online from Sainsbury's, the same great quality, freshness and choice you'd find in store. Choose from 1 hour delivery slots and collect Nectar points.\"},{\"title\":\"Sainsbury's White Flesh Nectarines, Ripe & Ready x4\",\"size\":\"15.21kb\",\"unit_price\":2.0,\"description\":\"Buy Sainsbury's White Flesh Nectarines, Ripe & Ready x4 online from Sainsbury's, the same great quality, freshness and choice you'd find in store. Choose from 1 hour delivery slots and collect Nectar points.\"}],\"total\":25.3}";

	@Test
	public void testGetGroceryProducts() {
		ResponseEntity<String> response = restTemplate.getForEntity(String.format("%s/products", url), String.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(expected, response.getBody());
		logger.info(response.getBody());
	}
}
