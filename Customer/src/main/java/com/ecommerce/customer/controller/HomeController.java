package com.ecommerce.customer.controller;

import com.ecommerce.library.model.Customer;
import com.ecommerce.library.model.Product;
import com.ecommerce.library.model.ShoppingCart;
import com.ecommerce.library.service.CustomerService;
import com.ecommerce.library.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = { "/index", "/" }, method = RequestMethod.GET)
    public String home(Model model, Principal principal, HttpSession session) {
        if (principal != null) {
            session.setAttribute("username", principal.getName());

            Customer customer = customerService.findByUsername(principal.getName());
            ShoppingCart cart = customer.getShoppingCart();

            if (cart != null) {
                session.setAttribute("totalItems", cart.getTotalItems());
            } else {
                session.removeAttribute("totalItems");
            }

        } else {
            session.removeAttribute("username");
        }

        List<Product> productDtos = productService.getAllProducts();

        List<Product> featuredProducts = getRandomProducts(productDtos, 6);
        model.addAttribute("featuredProducts", featuredProducts);

        List<Product> onSaleProducts = getRandomProducts(productDtos, 6);
        model.addAttribute("onSaleProducts", onSaleProducts);

        List<Product> topRateProducts = getRandomProducts(productDtos, 6);
        model.addAttribute("topRateProducts", topRateProducts);

        List<Product> trendingProducts = getRandomProducts(productDtos, 8);
        model.addAttribute("trendingProducts", trendingProducts);
        return "home";
    }

    public void shuffleProducts(List<Product> products) {
        Collections.shuffle(products);
    }

    public List<Product> getRandomProducts(List<Product> products, int count) {
        if (count > products.size()) {
            throw new IllegalArgumentException("Count cannot be greater than the list size.");
        }
        shuffleProducts(products); // Shuffle the list first
        return new ArrayList<>(products.subList(0, count));
    }

    @GetMapping("/contact-us")
    public String contactUs(Model model) {
        // Add any necessary model attributes for the "Contact Us" page
        return "contact-us";
    }

}
