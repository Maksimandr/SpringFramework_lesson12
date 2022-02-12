package ru.gb.springbootdemoapp.component;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.gb.springbootdemoapp.dto.ProductDto;
import ru.gb.springbootdemoapp.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс корзины
 */
@Component
@Scope("prototype")
@Data
public class Cart {

    private final List<ProductDto> products;

    public Cart() {
        products = new ArrayList<>();
    }

    public void addProduct(ProductDto productDto) {
        products.add(productDto);
    }

    public void removeProduct(ProductDto productDto) {
        products.remove(productDto);
    }
}