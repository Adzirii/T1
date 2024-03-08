package exercise.adziri.t1.services;

import exercise.adziri.t1.model.Category;
import exercise.adziri.t1.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Category save(Category categoryForm){
        var category = categoryRepository.findByName(categoryForm.getName());
        return category.orElse(categoryRepository.save(categoryForm));
    }

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public Category getById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Категории с таким id не существует"));
    }

    public Category update(Long id, Category category) {
        category.setId(id);
        return categoryRepository.save(category);
    }

    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
