package inc.star.marketproduct.Model.FileModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class FileData {

    private Integer id;
    private Long fileSize;
    private String fileName;
    private String fileUrl;
    private String contentType;
    private FileContent fileContent;

    public FileData(Long fileSize, String fileName, String fileUrl, String contentType) {
        this.fileSize = fileSize;
        this.fileName = fileName;
        this.fileUrl = fileUrl;
        this.contentType = contentType;
    }
}
