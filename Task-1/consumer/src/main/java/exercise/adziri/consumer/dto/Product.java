package exercise.adziri.consumer.dto;


import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    @Null
    private Long id;
    @NotBlank(message = "Продукт обязан иметь имя")
    private String name;
    @NotBlank(message = "Продукт обязан иметь описание")
    private String description;
    @NotNull
    @DecimalMin(value = "0", message = "Продукт обязан иметь положительную цену")
    @DecimalMax(value = "999999")
    private Double price;
    @Valid
    private Category category;
}