package ru.gb.springbootdemoapp.service;

import org.springframework.stereotype.Service;
import ru.gb.springbootdemoapp.component.Cart;
import ru.gb.springbootdemoapp.model.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CartService {
    private final ProductService productService;
    private final Map<String, Cart> cartMap;

    public CartService(ProductService productService) {
        this.productService = productService;
        this.cartMap = new HashMap<>();
    }

    public List<Product> getCartProducts(String userName) {
        if (!cartMap.containsKey(userName)) {
            Cart newCart = new Cart();
            cartMap.put(userName, newCart);
        }
        return cartMap.get(userName).getProducts();
    }

    public void addProductById(Long id, String userName) {
        if (!cartMap.containsKey(userName)) {
            Cart newCart = new Cart();
            newCart.addProduct(productService.findById(id));
            cartMap.put(userName, newCart);
        }
        cartMap.get(userName).addProduct(productService.findById(id));
    }

    public void removeProductById(Long id, String userName) {
        Cart cart = cartMap.get(userName);

        cart.removeProduct(productService.findById(id));

        cartMap.put(userName, cart);
    }
}