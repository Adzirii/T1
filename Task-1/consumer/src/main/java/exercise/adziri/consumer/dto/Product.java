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
    @NotBlank(message = "Имя не может быть пустыи")
    private String name;
    @NotBlank(message = "Описание не может быть пустым")
    private String description;
    @NotNull(message = "Цена не может быть отрицательной")
    @DecimalMin(value = "0")
    @DecimalMax(value = "999999")
    private Double price;
    @Valid
    private Category category;
}