package com.amazon.Shopping2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Shopping2Application {

	public static void main(String[] args) {
		SpringApplication.run(Shopping2Application.class, args);
		
		ConfigurableApplicationContext context = SpringApplication.run(Shopping2Application.class, args);

 
        ShoppingCart cart = context.getBean(ShoppingCart.class);

  
        Product videogame = new Product();
        videogame.setName("Diablo");
        videogame.setPrice(60);
        videogame.setId(647);
        videogame.setDescription("Action RPG game");
        videogame.setQuantity(5);

        Product tv = new Product();
        tv.setName("Roku");
        tv.setPrice(229);
        tv.setId(234);
        tv.setDescription("Smart TV");
        tv.setQuantity(4);

        cart.addProduct(videogame);
        cart.addProduct(tv);

        cart.removeProduct(tv);

        int totalPrice = cart.totalPrice();
        double tax = cart.tax();

        System.out.println("Shopping Cart Contents:");
        cart.viewCart();

        System.out.println("Total Price: $" + totalPrice);
        System.out.println("Tax: $" + tax);

        
        context.close();
    
	}

}
