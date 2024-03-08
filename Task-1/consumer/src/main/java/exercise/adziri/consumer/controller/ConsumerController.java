package exercise.adziri.consumer.controller;

import exercise.adziri.consumer.dto.Category;
import exercise.adziri.consumer.dto.Product;
import exercise.adziri.consumer.dto.ProductAndCategory;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/consumer")
public class ConsumerController {

    @Value("${supplier.url}")
    private String url;

    private final RestTemplate restTemplate;


    @GetMapping("/info")
    public ProductAndCategory getProductAndCategory() {
        var prodAndCategoryDto = new ProductAndCategory();
        prodAndCategoryDto.setCategory(List.of(Objects.requireNonNull(restTemplate.getForObject(url + "/categories", Category[].class))));
        prodAndCategoryDto.setProduct(List.of(Objects.requireNonNull(restTemplate.getForObject(url + "/products", Product[].class))));
        return prodAndCategoryDto;
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody @Valid  Product product) {
        if (product.getCategory() != null)
            product.setCategory(restTemplate.postForObject(url + "/categories", product.getCategory(), Category.class));
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(restTemplate.postForObject(url + "/products", product, Product.class));
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        restTemplate.delete(url + "/products/" + id);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body("Продукт удален");
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        restTemplate.put(url + "/products/" + id, product);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body("Продукт обновлен");
    }

    @GetMapping("/products")
    public List<Product> productsSortAscendDescend() {
        return List.of(Objects.requireNonNull(restTemplate.getForObject(url + "/products", Product[].class)));
    }

    @GetMapping("/products/filtered")
    public ResponseEntity<List<Product>> filteredByPrice(
            @RequestParam(required = false) String nameSort,
            @RequestParam(required = false) String priceSort,
            @RequestParam(required = false, defaultValue = "") String name,
            @RequestParam(required = false, defaultValue = "") String description,
            @RequestParam(required = false, defaultValue = "") String categoryName,
            @RequestParam(required = false, defaultValue = "0") Double minPrice,
            @RequestParam(required = false, defaultValue = "9999999") Double maxPrice,
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size) {

        List<Product> products = productsSortAscendDescend();

        List<Product> sorted = products.stream()
                .filter(product -> product.getPrice() > minPrice && product.getPrice() < maxPrice)
                .filter(product -> product.getName().startsWith(name))
                .filter(product -> product.getDescription().startsWith(description))
                .filter(product -> product.getCategory().getName().startsWith(categoryName))
                .toList();

        sorted = productsSortAscendDescend(nameSort, sorted, Comparator.comparing(Product::getName));
        sorted = productsSortAscendDescend(priceSort, sorted, Comparator.comparing(Product::getPrice));


        return ResponseEntity.ok(getProductsPaginated(sorted, page, size));


    }

    private List<Product> getProductsPaginated(List<Product> products, int page, int size) {
        int startIndex = page * size;
        int endIndex = Math.min(startIndex + size, products.size());

        if (startIndex >= endIndex) {
            return List.of();
        }

        return products.subList(startIndex, endIndex);
    }

    private List<Product> productsSortAscendDescend(String nameSort, List<Product> sorted, Comparator<Product> comparing) {
        if (nameSort != null && nameSort.equals("asc")) {
            sorted = sorted.stream()
                    .sorted(comparing)
                    .collect(Collectors.toList());
        } else if (nameSort != null && nameSort.equals("desc")) {
            sorted = sorted.stream()
                    .sorted(comparing.reversed())
                    .collect(Collectors.toList());
        }
        return sorted;
    }


}
