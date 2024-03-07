package exercise.adziri.consumer.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Category {
    @Null
    private Long id;
    @NotNull
    private String name;
}

