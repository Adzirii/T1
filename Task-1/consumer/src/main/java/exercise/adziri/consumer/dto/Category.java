package exercise.adziri.consumer.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Category {
    @Null
    private Long id;
    @NotBlank(message = "Категория обязана иметь имя")
    private String name;
}

