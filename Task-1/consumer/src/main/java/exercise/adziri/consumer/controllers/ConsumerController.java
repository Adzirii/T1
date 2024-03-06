package exercise.adziri.consumer.controllers;

import exercise.adziri.consumer.dto.Category;
import exercise.adziri.consumer.dto.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/consumer")
public class ConsumerController {

    @Value("${supplier.url}")
    private String url;

    private final RestTemplate restTemplate;


    @GetMapping("/categories")
    public List<Category> getCategories() {

        return List.of(Objects.requireNonNull(restTemplate.getForObject(url + "/categories", Category[].class)));
    }

    @PostMapping("/products")
    public Product createProduct(@RequestBody Product product) {
        if (product.getCategory() == null)
            product.setCategory(restTemplate.postForObject(url + "/categories", product.getCategory(), Category.class));
        return restTemplate.postForObject(url + "/products", product, Product.class);
    }
}
