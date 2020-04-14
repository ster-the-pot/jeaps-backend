package jeaps.foodtruck.controllers;


import jeaps.foodtruck.common.image.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.net.URI;

@RestController
public class FileController {

    @Autowired private ImageDAO imageDAO;

    @PostMapping(path="/files/save")
    public ResponseEntity<?> setContent(@RequestParam MultipartFile file)
            throws IOException {
        
        Image i = this.imageDAO.saveFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(i.getId())
                .toUriString();

        Map<String,Object> str = new HashMap<>();
        str.put("Image", new ImageDTO(i.getId(), i.getFileName(), fileDownloadUri,
                file.getContentType(), file.getSize()));

        return ResponseEntity.created(URI.create("/files/done")).body(str);



    }

    @RequestMapping(value="/files/getFile", method = RequestMethod.GET)
    public ResponseEntity<?> getContent(@RequestParam String fileId) {

        Image i = this.imageDAO.getFile(fileId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(i.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + i.getFileName() + "\"")
                .body(new ByteArrayResource(i.getData()));

    }

}
