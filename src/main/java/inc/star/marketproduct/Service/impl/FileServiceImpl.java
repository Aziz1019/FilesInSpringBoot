package inc.star.marketproduct.Service.impl;

import inc.star.marketproduct.Model.FileModel.FileContent;
import inc.star.marketproduct.Model.FileModel.FileData;
import inc.star.marketproduct.Model.ResMessage;
import inc.star.marketproduct.Service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;


import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService, RowMapper<FileData> {

    private final JdbcTemplate jdbcTemplate;
    private final Path root = Paths.get("uploads");

    @Override
    public void init() {
        try {
            Files.createDirectory(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(MultipartFile file) {
        try {
            Date date = new Date();
            String fileUrl = date.getTime() + file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
            Files.copy(file.getInputStream(), root.resolve(fileUrl));
            jdbcTemplate.update("insert into filedata (file_size, file_name, file_url, content_type) VALUES (?,?,?,?)"
                    , file.getSize(), file.getOriginalFilename(), fileUrl, file.getContentType());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Resource load(String filename) {
        Path resolve = root.resolve(filename);
        try {
            Resource resource = new UrlResource(resolve.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public ResMessage loadAll() {
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select file_name,'http://localhost:8080/upload/' || filedata.file_url as file_url from filedata");
        return new ResMessage(0, "ok", maps);
    }

    @Override
    public ResMessage saveFileToDB(MultipartFile file) {
        try {
            byte[] bytes = file.getBytes();
            Integer id = jdbcTemplate.queryForObject("insert into filecontent(content) values (?) returning id", Integer.class, bytes);
            int update = jdbcTemplate.update("insert into filedata(file_size, file_name, file_url, content_type, filecontent_id) VALUES " +
                    "(?,?,?,?,?)", file.getSize(), file.getName(), file.getOriginalFilename(), file.getContentType(), id);
            if (update > 0) {
                return new ResMessage(0, "ok", null);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResMessage(100, "not saved", null);
    }

    @Override
    public FileData getFile(Integer id) {
        return jdbcTemplate.queryForObject("select  fd.file_size, fd.file_name, fd.content_type,  f.content from filedata fd  left join filecontent f on f.id = fd.filecontent_id where  fd.id = ?",
                this, id);

    }

    @Override
    public FileData mapRow(ResultSet resTemplate, int rowNum) throws SQLException {
        FileData data = new FileData();
        data.setFileSize(resTemplate.getLong(1));
        data.setFileName(resTemplate.getString(2));
        data.setContentType(resTemplate.getString(3));
        FileContent fileContent = new FileContent();
        fileContent.setContent(resTemplate.getBytes(4));
        data.setFileContent(fileContent);
        return data;
    }

}
