package ru.gb.springbootdemoapp.service;

import org.springframework.stereotype.Service;
import ru.gb.springbootdemoapp.component.Cart;
import ru.gb.springbootdemoapp.converter.ProductMapper;
import ru.gb.springbootdemoapp.model.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CartService {
    private final ProductService productService;
    private final ProductMapper productMapper;
    private final Map<String, Cart> cartMap;

    public CartService(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
        this.cartMap = new HashMap<>();
    }

    public List<Product> getCartProducts(String userName) {
        if (!cartMap.containsKey(userName)) {
            cartMap.put(userName, new Cart());
        }
        return cartMap.get(userName).getProducts();
    }

    public void addProductById(Long id, String userName) {
        if (!cartMap.containsKey(userName)) {
            cartMap.put(userName, new Cart());
        }
        cartMap.get(userName).addProduct(productService.findById(id));
    }

    public void removeProductById(Long id, String userName) {
        Cart cart = cartMap.get(userName);
        Product product = productService.findById(id);
        int n = -1;
        for (Product p :
                cart.getProducts()) {
            if (p.getId().equals(product.getId())) {
                n = cart.getProducts().indexOf(p);
                break;
            }
        }
        System.out.println(n);
        if (n >= 0) {
            cart.removeProductByIndex(n);
        }
    }
}