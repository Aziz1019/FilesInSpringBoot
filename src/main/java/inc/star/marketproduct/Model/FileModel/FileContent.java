package inc.star.marketproduct.Model.FileModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class FileContent {
    private int id;
    private byte[] content;
}
