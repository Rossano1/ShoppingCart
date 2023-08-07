package com.amazon.Shopping2;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/shopping")
public class ShoppingCart {
ArrayList<Product> products = new ArrayList<>();
private int quantityLimit;

public ShoppingCart(@Value("${shopping.quantityLimit}")int quantityLimit) {
	super();
	this.quantityLimit = quantityLimit;
}
@PostMapping("/addProduct")
public void addProduct(@RequestBody Product product) {
	int existingIndex = products.indexOf(product);
    if (existingIndex != -1) {
        Product existingProduct = products.get(existingIndex);
        int newQuantity = existingProduct.getQuantity() + product.getQuantity();
        if (newQuantity <= quantityLimit) {
            existingProduct.setQuantity(newQuantity);
            System.out.println("You added " + product.getQuantity() + " more " + product.getName());
        } else {
            int remainingCapacity = quantityLimit - existingProduct.getQuantity();
            System.out.println("Cannot add more than " + remainingCapacity + " of " + product.getName());
        }
    } else {
        if (product.getQuantity() <= quantityLimit) {
            products.add(product);
            System.out.println("You added " + product.getQuantity() + " " + product.getName());
        } else {
            System.out.println("Cannot add more than " + quantityLimit + " of " + product.getName());
        }
    }
	
}
@DeleteMapping("/removeProduct")
public void removeProduct(@RequestBody Product pro) {
	for(int i = 0; i< products.size(); i++) {
		if (products.get(i).equals(pro)) {
		products.remove(i);
		System.out.println("This product has been removed from the cart: " + pro.getName());
        return;
		}
	}
	
	
}
int total = 0;
@GetMapping("/totalPrice")
public int totalPrice() {
	for(int i = 0; i< products.size();i++) {
		Product pro = products.get(i);
		 total += pro.getPrice() * pro.getQuantity();
		
	
	}
	return total;
}
@GetMapping("/tax")
public double tax() {
DecimalFormat df = new DecimalFormat("#.##");
	double saletax = total *.10;
	String taxes = df.format(saletax);
	return Double.parseDouble(taxes);
}
@GetMapping("/viewCart")
public List<Product> viewCart() {
    return products;
}
}
