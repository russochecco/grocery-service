package org.frusso.groceryservice.controller.rest;

import javax.annotation.Resource;

import org.frusso.groceryservice.controller.rest.errorhandling.GroceryEmptyException;
import org.frusso.groceryservice.controller.rest.errorhandling.ServiceError;
import org.frusso.groceryservice.domain.Grocery;
import org.frusso.groceryservice.parser.GroceryParser;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/grocery-service/rest")
public class GroceryServiceRESTful {

	@Resource(name="defaultGroceryParser")
	private GroceryParser groceryParser;

	@RequestMapping(value = "/products", method = RequestMethod.GET, produces = "application/json")
	public Grocery getGroceryProducts() {
		Grocery grocery = groceryParser.getGrocery();
		if (grocery == null || grocery.getProducts().isEmpty())
			throw new GroceryEmptyException("The grocery is empty");

		return grocery;
	}

	@ExceptionHandler(GroceryEmptyException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ServiceError handlerEmptyListItem(GroceryEmptyException e) {
		return new ServiceError(HttpStatus.NOT_FOUND.value(), e.getMessage());
	}
}
