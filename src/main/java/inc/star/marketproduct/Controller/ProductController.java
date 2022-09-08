package inc.star.marketproduct.Controller;

import inc.star.marketproduct.Model.Product;
import inc.star.marketproduct.Model.ResMessage;
import inc.star.marketproduct.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResMessage add_student(@RequestBody Product product){
        return productService.saveData(product);
    }

    @GetMapping
    public ResMessage getAllProduct(){
        return productService.getAll();
    }
    @GetMapping("{category_id}")
    public ResMessage getByCategoryId(@PathVariable int category_id){
        return productService.findAllByCategoryId(category_id);
    }



}
