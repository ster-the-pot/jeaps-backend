package jeaps.foodtruck.controllers;

import jeaps.foodtruck.Token.TokenService;
import jeaps.foodtruck.Token.UserLoginToken;
import jeaps.foodtruck.common.image.Image;
import jeaps.foodtruck.common.image.ImageDAO;
import jeaps.foodtruck.common.image.ImageDTO;
import jeaps.foodtruck.common.truck.TruckDAO;
import jeaps.foodtruck.common.truck.rate.Rate;
import jeaps.foodtruck.common.truck.rate.RateDAO;
import jeaps.foodtruck.common.truck.rate.RateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path="/rate")
@ResponseBody
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RatingsController {
    @Autowired
    RateDAO rateDAO;
    @Autowired
    TokenService tokenService;
    @Autowired
    ImageDAO imageDAO;

    Map<String, Object> map = new HashMap<>();


    @UserLoginToken
    @PostMapping(path="/customer/addRating")
    public ResponseEntity<?> addRating(@RequestBody RateDTO rate, @RequestParam Integer truckid, /*@RequestParam String username*/HttpServletRequest req) {
        rateDAO.addRate(rate, truckid, tokenService.getUsername(req));
        //rateDAO.addRate(rate, truckid, username);
        return ResponseEntity.ok("Rating successfully added");
    }

    @UserLoginToken
    @PostMapping(path="/customer/editRating")
    public ResponseEntity<?> editRating(@RequestBody RateDTO rate, @RequestParam Integer truckid, /*@RequestParam String username*/HttpServletRequest req) {
        rateDAO.editRate(rate, truckid, tokenService.getUsername(req));
        //rateDAO.editRate(rate, truckid, username);
        return ResponseEntity.ok("Rating successfully edited");
    }

    @UserLoginToken
    @PostMapping(path="/customer/deleteRating")
    public ResponseEntity<?> editRating(@RequestParam Integer truckid, /*@RequestParam String username*/HttpServletRequest req) {
        rateDAO.removeRate(truckid, tokenService.getUsername(req));
        //rateDAO.removeRate(truckid, username);
        return ResponseEntity.ok("Rating successfully deleted");
    }

    @RequestMapping(path="/getAverageRating", method = RequestMethod.GET)
    public ResponseEntity<?> getAverageRating(@RequestParam Integer truckid){
        map.clear();
        Double avg = rateDAO.getAvgRating(truckid);

        map.put("Ratings", avg );

        return ResponseEntity.created(URI.create("/getAverageRating/done")).body(map);
    }

    @RequestMapping(path="/getFullTruckRatings", method = RequestMethod.GET)
    public ResponseEntity<?> getFullTruckRatings(@RequestParam Integer truckid) {
        List<Rate> rate = rateDAO.getRatingsObject(truckid);

        map.clear();
        map.put("Ratings", rate);
        return ResponseEntity.created(URI.create("/getFullTruckRatings/done")).body(map);
    }

    @RequestMapping(path="/getTruckRatings", method = RequestMethod.GET)
    public ResponseEntity<?> getTruckRatings(@RequestParam Integer truckid){

        List<Integer> rate = rateDAO.getRatings(truckid);
        map.clear();
        map.put("Ratings", rate);
        return ResponseEntity.created(URI.create("/getTruckRatings/done")).body(map);
    }


    //Editing works here too
    @PostMapping(path="/addPictures")
    public ResponseEntity<?> addPictures(@RequestParam MultipartFile[] file, @RequestParam Integer truckId, @RequestParam String username) {
        if(file == null) {
            throw new RuntimeException("No files given");
        }
        Map<String,Object> str = new HashMap<>();
        for(MultipartFile f: file) {
            Image i = this.rateDAO.savePicture(truckId, username, f);
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/downloadFile/")
                    .path(i.getId())
                    .toUriString();
            str.put("Image", new ImageDTO(i.getId(), i.getFileName(), fileDownloadUri,
                    f.getContentType(), f.getSize()));
        }
        return ResponseEntity.created(URI.create("/addPictures/done")).body(str);
    }

    @PostMapping(path="/deletePicture")
    public ResponseEntity<?> deletePicture(@RequestParam Integer truckId, @RequestParam String imageId, @RequestParam String username) {
        this.rateDAO.deletePicture(imageId, truckId, username);
        return ResponseEntity.ok("Picture successfully deleted");
    }
    @PostMapping(path="/deleteAllPicture")
    public ResponseEntity<?> deleteAllPicture(@RequestParam Integer truckId, @RequestParam String username) {
        this.rateDAO.deleteAllPicture(truckId, username);
        return ResponseEntity.ok("Pictures successfully deleted");
    }

    @RequestMapping(value="/getPictureIds", method = RequestMethod.GET)
    public ResponseEntity<?> getPictures(@RequestParam Integer truckId, @RequestParam String username) throws IOException {
        Map<String,Object> str = new HashMap<>();
        str.put("Ids", this.rateDAO.getPictureIds(truckId, username));
        return ResponseEntity.created(URI.create("/addPictures/done")).body(str);
    }
    @RequestMapping(value="/getImage", method = RequestMethod.GET)
    public ResponseEntity<?> getImage(@RequestParam String fileId) throws IOException {
        Image i = this.imageDAO.getFile(fileId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(i.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + i.getFileName() + "\"")
                .body(new ByteArrayResource(i.getData()));
    }
}
