package jeaps.foodtruck.controllers;

import java.util.Base64;

import jeaps.foodtruck.common.image.ImageDAO;
import org.apache.commons.lang3.ArrayUtils;
import jeaps.foodtruck.common.image.Image;
import jeaps.foodtruck.common.image.ImageDTO;
import jeaps.foodtruck.common.truck.Truck;
import jeaps.foodtruck.common.user.owner.OwnerDAO;
import jeaps.foodtruck.common.truck.TruckDAO;
import jeaps.foodtruck.common.truck.TruckDTO;
import jeaps.foodtruck.common.truck.route.RouteDAO;
import jeaps.foodtruck.common.truck.route.RouteDTO;
import jeaps.foodtruck.common.user.user.User;
import jeaps.foodtruck.common.user.user.UserDAO;
import jeaps.foodtruck.common.user.user.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.*;


@RestController
@RequestMapping(path="/owner")
@ResponseBody
public class OwnerController {
    @Autowired
    UserDAO userDAO;
    @Autowired
    TruckDAO truckDAO;
    @Autowired
    RouteDAO routeDAO;
    @Autowired
    OwnerDAO ownerDAO;
    @Autowired
    ImageDAO imageDAO;

    @RequestMapping(path="/details", method = RequestMethod.GET)
    public User getUserDetails(@RequestParam String username){
        return userDAO.findByUsername(username);
    }

    @PostMapping(path="/manage")
    public Object manageUserDetails(@RequestBody UserDTO user) {
        this.userDAO.update(user);
        return "Successfully updated Owner info";
    }

    @RequestMapping(path="/getSubscribers")
    public List<Object> getSubscribers(@RequestParam Integer truckID){
        return this.truckDAO.getSubscribers(truckID);
    }


    @RequestMapping(path="/getNumSubscribers")
    public Integer getNumSubscribers(@RequestParam Integer truckID){
        return this.truckDAO.getNumSubscribers(truckID);
    }

    @RequestMapping(path="/getOwnerSubscribers")
    public List<String> getOwnerSubscribers(@RequestParam String username){
        return this.ownerDAO.getSubscribers(username);
    }

    @RequestMapping(path="/getNumOwnerSubscribers")
    public Integer getNumOwnerSubscribers(@RequestParam String username){
        return this.ownerDAO.getNumSubscribers(username);
    }

    /************************************************************************
     * Image
     ************************************************************************/
    //Editing works here too
    @PostMapping(path="/addMenu")
    public ResponseEntity<?> addMenu(@RequestParam MultipartFile file, @RequestParam Integer truckId) {

        Image i = this.truckDAO.saveMenu(truckId, file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(i.getId())
                .toUriString();

        Map<String,Object> str = new HashMap<>();
        str.put("Image", new ImageDTO(i.getId(), i.getFileName(), fileDownloadUri,
                file.getContentType(), file.getSize()));

        return ResponseEntity.created(URI.create("/addMenu/done")).body(str);
    }

    @PostMapping(path="/deleteMenu")
    public ResponseEntity<?> deleteMenu(@RequestParam Integer truckID) {
        this.truckDAO.deleteMenu(truckID);
        return ResponseEntity.ok("Menu successfully deleted");
    }


    @RequestMapping(value="/getMenu", method = RequestMethod.GET)
    public ResponseEntity<?> getMenu(@RequestParam Integer truckID) {

        Image i = this.truckDAO.getMenu(truckID);
        if(i == null) {
            return ResponseEntity.status(404).body("no image");
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(i.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + i.getFileName() + "\"")
                .body(new ByteArrayResource(i.getData()));

    }


    //Editing works here too
    @PostMapping(path="/addPictures")
    public ResponseEntity<?> addPictures(@RequestParam MultipartFile[] file, @RequestParam Integer truckId) {
        if(file == null) {
            throw new RuntimeException("No files given");
        }
        Map<String,Object> str = new HashMap<>();
        for(MultipartFile f: file) {
            Image i = this.truckDAO.savePicture(truckId, f);
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
    public ResponseEntity<?> deletePicture(@RequestParam Integer truckId, @RequestParam String imageId) {
        this.truckDAO.deletePicture(imageId, truckId);
        return ResponseEntity.ok("Picture successfully deleted");
    }
    @PostMapping(path="/deleteAllPicture")
    public ResponseEntity<?> deleteAllPicture(@RequestParam Integer truckId) {
        this.truckDAO.deleteAllPicture(truckId);
        return ResponseEntity.ok("Pictures successfully deleted");
    }

    @RequestMapping(value="/getPictureIds", method = RequestMethod.GET)
    public ResponseEntity<?> getPictures(@RequestParam Integer truckId) throws IOException {
        Map<String,Object> str = new HashMap<>();
        str.put("Ids", this.truckDAO.getPictureIds(truckId));
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

    /************************************************************************
     * End Image
     ************************************************************************/
    /*********************************************************
     * Trucks
     *********************************************************/
    @PostMapping(path="/createTruck")
    public Object createTruck(@RequestBody TruckDTO truck, @RequestParam String username) {
        Map<String,Object> ret = new HashMap();
        Truck t = this.ownerDAO.saveTruck(truck, username);
        ret.put("truck", t);

        return ret;
    }
    @PostMapping(path="/deleteTruck")
    public Object deleteTruck(@RequestParam Integer truckID) {
        this.ownerDAO.deleteTruck(truckID);
        return "Successfully deleted truck";
    }

    @RequestMapping(path="/myTrucks",method=RequestMethod.GET)
    public List<Truck> findTruck(@RequestParam String username){
        return this.truckDAO.findByOwner(username);
    }


    @PostMapping(path="/editTruck")
    public Object editTruck(@RequestBody TruckDTO truck, @RequestParam Integer truckID) {
        this.ownerDAO.editTruck(truck, truckID);
        return "Successfully updated truck";
    }

    /*********************************************************
     * Routes
     *********************************************************/

    @PostMapping(path="/createRoute")
    public Object createRoute(@RequestBody RouteDTO route, @RequestParam Integer truckID,
                              @RequestParam String username) {

        return this.routeDAO.saveRoute(route, truckID, username);

    }

    @PostMapping(path="/modifyRoute")
    public Object createEditRoute(@RequestBody RouteDTO route, @RequestParam Integer truckID,
                              @RequestParam String username) {
        if(route.getId() != null && this.routeDAO.findByID(route.getId()).isPresent()) {
            return this.routeDAO.editRoute(route);
        }
        return this.routeDAO.saveRoute(route, truckID, username);

    }

    @RequestMapping(path="/getRoutes")
    public List<RouteDTO> getRoutes(@RequestParam Integer truckID){
        return this.routeDAO.findByTruck(truckID);
    }

    @PostMapping(path="/deleteRoute")
    public Object deleteRoute(@RequestParam Integer routeID) {
        this.routeDAO.deleteRoute(routeID);
        return "Successfully deleted route";
    }

    @PostMapping(path="/deleteAllRoute")
    public Object deleteAllRoute(@RequestParam List<Integer> routeID) {
        this.routeDAO.deleteAllRoute(routeID);
        return "Successfully deleted route";
    }

    @PostMapping(path="/editRoute")
    public Object editRoute(@RequestBody RouteDTO route) {
        this.routeDAO.editRoute(route);
        return "Successfully updated truck";
    }


}
