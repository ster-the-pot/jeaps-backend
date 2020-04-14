package jeaps.foodtruck.controllers;


import jeaps.foodtruck.common.image.Image;
import jeaps.foodtruck.common.image.ImageDTO;
import jeaps.foodtruck.common.user.user.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path="/user")
@ResponseBody
public class UserController {
    @Autowired
    UserDAO userDAO;

    //Editing works here too
    @PostMapping(path="/addProfile")
    public ResponseEntity<?> addProfile(@RequestParam MultipartFile file, @RequestParam String username) {

        Image i = this.userDAO.saveProfile(username, file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(i.getId())
                .toUriString();

        Map<String,Object> str = new HashMap<>();
        str.put("Image", new ImageDTO(i.getId(), i.getFileName(), fileDownloadUri,
                file.getContentType(), file.getSize()));

        return ResponseEntity.created(URI.create("/addProfile/done")).body(str);
    }

    @PostMapping(path="/deleteProfile")
    public ResponseEntity<?> deleteProfile(@RequestParam String username) {
        this.userDAO.deleteProfile(username);
        return ResponseEntity.ok("Profile picture successfully deleted");
    }


    @RequestMapping(value="/getProfile", method = RequestMethod.GET)
    public ResponseEntity<?> getProfile(@RequestParam String username) {

        Image i = this.userDAO.getProfile(username);
        if(i == null) {
            return ResponseEntity.ok("No Profile picture");
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(i.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + i.getFileName() + "\"")
                .body(new ByteArrayResource(i.getData()));

    }
}
