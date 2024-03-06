package exercise.adziri.consumer.dto;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    @Null
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Category category;
}