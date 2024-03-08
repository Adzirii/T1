package exercise.adziri.t1.config;

import exercise.adziri.t1.model.Category;
import exercise.adziri.t1.model.Product;
import exercise.adziri.t1.services.CategoryService;
import exercise.adziri.t1.services.ProductService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SetupDataLoader {
    private final ProductService productService;
    private final CategoryService categoryService;

    @PostConstruct
    public void setupDataLoader() {
        Category category1 = new Category();
        category1.setName("Category 1");
        categoryService.save(category1);

        Category category2 = new Category();
        category2.setName("Category 2");
        categoryService.save(category2);

        Product product1 = new Product();
        product1.setName("Орешки");
        product1.setDescription("С солью");
        product1.setPrice(90.0);
        product1.setCategory(category1);
        productService.save(product1);

        Product product2 = new Product();
        product2.setName("Творог");
        product2.setDescription("Обезжиренный");
        product2.setPrice(130d);
        product2.setCategory(category2);
        productService.save(product2);

        Product product3 = new Product();
        product3.setName("Корм");
        product3.setDescription("Для собак");
        product3.setPrice(400d);
        product3.setCategory(category1);
        productService.save(product3);

        Product product4 = new Product();
        product4.setName("Мясо");
        product4.setDescription("Курица");
        product4.setPrice(300d);
        product4.setCategory(category1);
        productService.save(product4);
    }

    }
