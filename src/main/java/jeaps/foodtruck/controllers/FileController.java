package jeaps.foodtruck.controllers;


import jeaps.foodtruck.common.file.FileContentStore;
import jeaps.foodtruck.common.file.FileRepository;
import jeaps.foodtruck.common.file.UserFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

@RestController
public class FileController {

    @Autowired private FileRepository filesRepo;
    @Autowired private FileContentStore contentStore;

    @RequestMapping(value="/files/{fileId}", method = RequestMethod.PUT)
    public ResponseEntity<?> setContent(HttpServletRequest req, @PathVariable("fileId") Long id, @RequestParam("file") MultipartFile file)
            throws IOException {

        //Optional<UserFile> f = filesRepo.findById(id);
        UserFile f = new UserFile();

        f.setMimeType(file.getContentType());

        contentStore.setContent(f, file.getInputStream());

        // save updated content-related info
        filesRepo.save(f);



        return new ResponseEntity<Object>(HttpStatus.OK);


    }

    @RequestMapping(value="/files/{fileId}", method = RequestMethod.GET)
    public ResponseEntity<?> getContent(@PathVariable("fileId") Long id) {

        Optional<UserFile> f = filesRepo.findById(id);
        if (f.isPresent()) {

            InputStreamResource inputStreamResource = new InputStreamResource(contentStore.getContent(f.get()));

            HttpHeaders headers = new HttpHeaders();
            headers.setContentLength(f.get().getContentLength());
            headers.set("Content-Type", f.get().getMimeType());
            return new ResponseEntity<Object>(inputStreamResource, headers, HttpStatus.OK);
        }
        return null;
    }

   /*@PostMapping(path="/savefile")
   public ResponseEntity<?> savefile(HttpServletRequest request, @RequestParam String filename) throws IOException {
       HashMap<String, Object> str = new HashMap<>();

       str.put("file", userFileDAO.saveUserFile(request, filename));
       return ResponseEntity.created(URI.create("/login/done")).body(str);
   }*/



   /* @PostMapping(path="/savefileOld")
    public ResponseEntity<?> setContent(@RequestParam String filename) throws IOException {

        File file = new File(filename);
        FileInputStream input = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile("file",
                    file.getName(), "text/plain", IOUtils.toByteArray(input));

        //Optional<UserFile> f = filesRepo.findById(id);
        UserFile f = new UserFile();

        f.setMimeType(multipartFile.getContentType());

        try {
            contentStore.setContent(f, multipartFile.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException("Issue with file");
        }


        filesRepo.save(f);

        return ResponseEntity.ok("Successfully Image");


    }*/

   /* @RequestMapping(value="/files/{fileId}", method = RequestMethod.GET)
    public ResponseEntity<?> getContent(@PathVariable("fileId") Integer id) {

        Optional<UserFile> f = filesRepo.findById(id);
        if (f.isPresent()) {
            InputStreamResource inputStreamResource = new InputStreamResource(contentStore.getContent(f.get()));
            HttpHeaders headers = new HttpHeaders();
            headers.setContentLength(f.get().getFileData().getSize());

            headers.set("Content-Type", "text/plain");
            return new ResponseEntity<Object>(inputStreamResource, headers, HttpStatus.OK);
        }
        return null;
    }*/
}
