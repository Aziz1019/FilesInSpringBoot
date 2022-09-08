package inc.star.marketproduct.Controller;

import inc.star.marketproduct.Model.Category;
import inc.star.marketproduct.Model.ResMessage;
import inc.star.marketproduct.Service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor

public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public ResMessage getCategories(){
        return categoryService.getAll();
    }

    @PostMapping
    public ResMessage saveCategory(@RequestBody Category category){
        return categoryService.saveData(category);
    }

    @GetMapping("{id}") // id is a value here entered from Website
    public ResMessage getCategoryById(@PathVariable int id){
        return categoryService.getById(id);
    }

    @GetMapping("{id}")
    public ResMessage deleteById(@PathVariable int id){
        return categoryService.getById(id);
    }

}
