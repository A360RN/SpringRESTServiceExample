package pe.com.alonso.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pe.com.alonso.to.Product;

@RestController
public class ProductController {

	private List<Product> products;
	private final AtomicLong counter = new AtomicLong();

	public ProductController() {
		products = new ArrayList<Product>();

		products.add(new Product(counter.incrementAndGet(), "Product 1"));
		products.add(new Product(counter.incrementAndGet(), "Product 2"));
		products.add(new Product(counter.incrementAndGet(), "Product 3"));
		products.add(new Product(counter.incrementAndGet(), "Product 4"));
		products.add(new Product(counter.incrementAndGet(), "Product 5"));
	}

	@RequestMapping(value = "/product", method = RequestMethod.GET)
	public List<Product> listProducts() {
		return products;
	}

	@RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
	public Product getProduct(@PathVariable("id") long id) {
		return products.get(new Long(id).intValue() + 1);
	}

	@RequestMapping(value = "/product", method = RequestMethod.POST)
	public Product addProduct(@RequestParam(value = "name", defaultValue= "default") String name) {
		Product newProduct = new Product(counter.incrementAndGet(), name);
		products.add(newProduct);
		return newProduct;
	}
}
