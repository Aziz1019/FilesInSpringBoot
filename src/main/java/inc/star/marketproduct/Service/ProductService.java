package inc.star.marketproduct.Service;

import inc.star.marketproduct.Model.Product;
import inc.star.marketproduct.Model.ResMessage;

public interface ProductService extends BasicService<Product>{
    ResMessage findAllByCategoryId(int id);
}
