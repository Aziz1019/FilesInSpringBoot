package inc.star.marketproduct.Service.impl;

import inc.star.marketproduct.Model.Category;
import inc.star.marketproduct.Model.ResMessage;
import inc.star.marketproduct.Service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final JdbcTemplate jdbcTemplate;


    @Override
    public ResMessage getAll() {
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select id, name, created_date from category where is_active <> false");
        return new ResMessage(200, "ok", list);
    }

    @Override
    public ResMessage saveData(Category category) {
        int update = jdbcTemplate.update("insert into category(name) values (?)", category.getName());
        if(update > 0){
            return new ResMessage(200, "0k");
        }
        return new ResMessage(101, "not saved");
    }

    @Override
    public ResMessage getById(int id) {
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select c.id, c.name, c.created_date, p.count from category c left join product p on c.id = p.category_id " +
                "where c.id = ? and c.is_active <> false", id);
        return new ResMessage(200, "ok", list);

    }

    @Override
    public ResMessage deleteById(int id) {
        int update = jdbcTemplate.update("update category set is_active = false where  id = ?", id);
        if (update>0){
            return new ResMessage(0,"ok");
        }
        return new ResMessage(102,"not deleted");
    }
}
