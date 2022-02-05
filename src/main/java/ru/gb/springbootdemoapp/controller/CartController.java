package ru.gb.springbootdemoapp.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gb.springbootdemoapp.converter.ProductMapper;
import ru.gb.springbootdemoapp.dto.ProductDto;
import ru.gb.springbootdemoapp.service.CartService;
import ru.gb.springbootdemoapp.service.CategoryService;
import ru.gb.springbootdemoapp.service.ProductService;

import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final ProductService productService;
    private final ProductMapper productMapper;
    private final CartService cartService;
    private final CategoryService categoryService;

    public CartController(ProductService productService, ProductMapper productMapper, CartService cartService, CategoryService categoryService) {
        this.productService = productService;
        this.productMapper = productMapper;
        this.cartService = cartService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public String getAllCartProducts(Model model, Principal principal) {
        List<ProductDto> products;
        if (principal.getName() != null) {
            products = cartService.getCartProducts(principal.getName());
        } else {
            products = Collections.emptyList();
        }
        model.addAttribute("products", products);
        return "cart";
    }

    @GetMapping("/add/{id}")
    public String addProductToCart(@PathVariable Long id, Model model, Principal principal) {
        if (principal.getName() != null) {
            cartService.addProductById(id, principal.getName());
            return "redirect:/cart";
        }
        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id, Principal principal) {
        if (principal.getName() != null) {
            cartService.removeProductById(id, principal.getName());
        }
        return "redirect:/cart";
    }
}
