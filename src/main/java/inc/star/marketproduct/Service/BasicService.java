package inc.star.marketproduct.Service;

import inc.star.marketproduct.Model.ResMessage;

public interface BasicService<T> {
    ResMessage getAll();
    ResMessage saveData(T t);
    ResMessage getById(int id);
    ResMessage deleteById(int id);
}
