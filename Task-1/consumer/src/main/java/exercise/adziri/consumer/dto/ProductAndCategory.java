package exercise.adziri.consumer.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductAndCategory {
    private List<Product> product;
    private List<Category> category;
}
