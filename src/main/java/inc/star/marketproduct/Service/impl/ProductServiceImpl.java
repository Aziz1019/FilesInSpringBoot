package inc.star.marketproduct.Service.impl;


import inc.star.marketproduct.Model.Product;
import inc.star.marketproduct.Model.ResMessage;
import inc.star.marketproduct.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public ResMessage getAll() {
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from product where is_active <> false");
        return new ResMessage(0,"ok",maps);
    }
    @Override
    public ResMessage saveData(Product product) {
        int update = jdbcTemplate.update("insert into product (product_name, count, category_id) values (?,?,?)",
                product.getProductName(), product.getCount(), product.getCategoryId());
        if(update > 0){
            return new ResMessage(200, "ok", product);
        }
        return new ResMessage(101, "not saved");
    }
    @Override
    public ResMessage getById(int id) {
        return null;
    }

    @Override
    public ResMessage deleteById(int id) {
        return null;
    }

    @Override
    public ResMessage findAllByCategoryId(int id) {
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from product where category_id = ? and is_active = true", id);
        return new ResMessage(200, "ok", list);
    }
}
