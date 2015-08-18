Grocery service is a RESTful web service that allows to retrieve and show in JSON format a
grocery of product listed in a HTML page. As Spring Boot project, it can be executed through the command line or deployed in a web container. With Maven is possible to use the the command 'mvn spring-boot:run'. The URL of the service is 'http://localhost:8080/grocery-service/rest/products'.
Under the package 'src/test/java' there are two simple test cases, one to test the class 'GroceryServiceRESTful' (org.frusso.groceryservice.controller.rest.GroceryServiceRESTfulTests), which exposes the web service, and another for the class 'GroceryParser' which implements the grocery products parser (org.frusso.groceryservice.parser.GroceryParserTests).

This is an example of the output using 'Postman':

{
  "results": [
    {
      "title": "Sainsbury's Avocado Ripe & Ready XL Loose 300g",
      "size": "3.63kb",
      "unit_price": 1.5,
      "description": "Buy Sainsbury's Avocado Ripe & Ready XL Loose 300g online from Sainsbury's, the same great quality, freshness and choice you'd find in store. Choose from 1 hour delivery slots and collect Nectar points."
    },
    {
      "title": "Sainsbury's Avocado, Ripe & Ready x2",
      "size": "4.4kb",
      "unit_price": 1.8,
      "description": "Burgers are a summer must-have and these homemade ones are perfect for a barbecue, topped with cool avocado and served with oven-baked potato wedges. "
    },
    {
      "title": "Sainsbury's Avocados, Ripe & Ready x4",
      "size": "4.53kb",
      "unit_price": 3.2,
      "description": "Buy Sainsbury's Avocados, Ripe & Ready x4 online from Sainsbury's, the same great quality, freshness and choice you'd find in store. Choose from 1 hour delivery slots and collect Nectar points."
    },
    {
      "title": "Sainsbury's Conference Pears, Ripe & Ready x4 (minimum)",
      "size": "4.51kb",
      "unit_price": 2,
      "description": "Buy Sainsbury's Conference Pears, Ripe & Ready x4 (minimum) online from Sainsbury's, the same great quality, freshness and choice you'd find in store. Choose from 1 hour delivery slots and collect Nectar points."
    },
    {
      "title": "Sainsbury's Kiwi Fruit, Ripe & Ready x4",
      "size": "35.21kb",
      "unit_price": 1.8,
      "description": "Buy Sainsbury's Kiwi Fruit, Ripe & Ready x4 online from Sainsbury's, the same great quality, freshness and choice you'd find in store. Choose from 1 hour delivery slots and collect Nectar points."
    },
    {
      "title": "Sainsbury's Mango, Ripe & Ready x2",
      "size": "3.94kb",
      "unit_price": 2,
      "description": "Buy Sainsbury's Mango, Ripe & Ready x2 online from Sainsbury's, the same great quality, freshness and choice you'd find in store. Choose from 1 hour delivery slots and collect Nectar points."
    },
    {
      "title": "Sainsbury's Nectarines, Ripe & Ready x4",
      "size": "17.01kb",
      "unit_price": 2,
      "description": "Buy Sainsbury's Nectarines, Ripe & Ready x4 online from Sainsbury's, the same great quality, freshness and choice you'd find in store. Choose from 1 hour delivery slots and collect Nectar points."
    },
    {
      "title": "Sainsbury's Peaches Ripe & Ready x4",
      "size": "16.37kb",
      "unit_price": 2,
      "description": "Buy Sainsbury's Peaches Ripe & Ready x4 online from Sainsbury's, the same great quality, freshness and choice you'd find in store. Choose from 1 hour delivery slots and collect Nectar points."
    },
    {
      "title": "Sainsbury's Pears, Ripe & Ready x4 (minimum)",
      "size": "5.53kb",
      "unit_price": 2,
      "description": "Buy Sainsbury's Pears, Ripe & Ready x4 (minimum) online from Sainsbury's, the same great quality, freshness and choice you'd find in store. Choose from 1 hour delivery slots and collect Nectar points."
    },
    {
      "title": "Sainsbury's Plums Ripe & Ready x5",
      "size": "13.76kb",
      "unit_price": 2.5,
      "description": "Buy Sainsbury's Plums Ripe & Ready x5 online from Sainsbury's, the same great quality, freshness and choice you'd find in store. Choose from 1 hour delivery slots and collect Nectar points."
    },
    {
      "title": "Sainsbury's Ripe & Ready Golden Plums x6",
      "size": "3.91kb",
      "unit_price": 2.5,
      "description": "Buy Sainsbury's Ripe & Ready Golden Plums x6 online from Sainsbury's, the same great quality, freshness and choice you'd find in store. Choose from 1 hour delivery slots and collect Nectar points."
    },
    {
      "title": "Sainsbury's White Flesh Nectarines, Ripe & Ready x4",
      "size": "15.21kb",
      "unit_price": 2,
      "description": "Buy Sainsbury's White Flesh Nectarines, Ripe & Ready x4 online from Sainsbury's, the same great quality, freshness and choice you'd find in store. Choose from 1 hour delivery slots and collect Nectar points."
    }
  ],
  "total": 25.3
}

Thank you for the interest in this repository.

Kind Regards,
Francesco