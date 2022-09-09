package inc.star.marketproduct.Controller.FileController;

import inc.star.marketproduct.Model.ResMessage;
import inc.star.marketproduct.Service.impl.FileServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/upload")
@RequiredArgsConstructor
public class FileUploadController {

    private final FileServiceImpl fileService;
    @PostMapping
    public ResMessage uploadFile(@RequestParam MultipartFile file){
        long size = file.getSize();
        fileService.save(file);
        return new ResMessage(0,"ok",size);
    }
    @GetMapping("/all")
    public ResMessage getAll(){
        return fileService.loadAll();
    }
    @GetMapping("{path}")
    public ResponseEntity<Resource> getFile(@PathVariable String path) throws IOException {
        Resource resource = fileService.load(path);
//        new InputStreamReader()
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(new InputStreamResource(resource.getInputStream()));
    }
}
