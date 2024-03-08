package exercise.adziri.consumer.dto;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductAndCategory {
    @Valid
    private List<Product> product;
    @Valid
    private List<Category> category;
}
