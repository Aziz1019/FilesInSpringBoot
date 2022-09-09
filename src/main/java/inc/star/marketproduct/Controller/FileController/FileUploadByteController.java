package inc.star.marketproduct.Controller.FileController;

import inc.star.marketproduct.Model.FileModel.FileData;
import inc.star.marketproduct.Model.ResMessage;
import inc.star.marketproduct.Service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/fileByte")
@RequiredArgsConstructor

public class FileUploadByteController {
    private final FileService fileService;

    @PostMapping
    public ResMessage addFile(@RequestParam MultipartFile file){
        return fileService.saveFileToDB(file);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getFile(@PathVariable Integer id){
        FileData file = fileService.getFile(id);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(file.getFileContent().getContent());
    }

}
