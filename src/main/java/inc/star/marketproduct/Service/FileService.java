package inc.star.marketproduct.Service;

import inc.star.marketproduct.Model.FileModel.FileData;
import inc.star.marketproduct.Model.ResMessage;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    void init();
    void save(MultipartFile file);
    Resource load(String filename);
    void deleteAll();
    ResMessage loadAll();

    ResMessage saveFileToDB(MultipartFile file);

    FileData getFile(Integer id);
}
