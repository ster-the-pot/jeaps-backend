package jeaps.foodtruck.controllers;

import jeaps.foodtruck.common.user.customer.preferences.Preferences;
import jeaps.foodtruck.common.user.user.notification.Notifications;
import jeaps.foodtruck.common.user.user.notification.NotificationsDAO;
import jeaps.foodtruck.common.user.user.notification.NotificationsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/notifications")
@ResponseBody
public class NotificationsController {
    @Autowired
    NotificationsDAO notificationsDAO;


    @PostMapping(path="/sendAll")
    public Object sendAll(@RequestBody Notifications n) {
        notificationsDAO.sendAll(n);
        return "Successfully sent message to all";
    }

    @PostMapping(path="/sendAllOwners")
    public Object sendAllOwners(@RequestBody Notifications n) {
        notificationsDAO.sendAllOwner(n);
        return "Successfully sent message to all Owners";
    }

    @PostMapping(path="/sendAllCustomers")
    public Object sendAllCustomers(@RequestBody Notifications n) {
        notificationsDAO.sendAllCustomer(n);
        return "Successfully sent message to all Customers";
    }

    //DUPLICATION WILL OCCUR
    @PostMapping(path="/sendAllSubscribers")
    public Object sendAllSubscribers(@RequestBody Notifications n, @RequestParam String username) {
        notificationsDAO.sendAllSubscribers(n, username);
        return "Successfully sent message to all Subscribers";
    }

    @PostMapping(path="/sendAllTruckSubscribers")
    public Object sendAllTruckSubscribers(@RequestBody Notifications n, @RequestParam String username, @RequestParam Integer truckID) {
        notificationsDAO.sendAllTruckSubscribers(n, username, truckID);
        return "Successfully sent message to all Truck Subscribers";
    }

    @PostMapping(path="/sendUser")
    public Object sendUser(@RequestBody Notifications n, @RequestParam String username) {
        notificationsDAO.sendToUser(n, username);
        return "Successfully sent message to single User";
    }

    @PostMapping(path="/clearNotification")
    public Object removeNotification(@RequestParam Integer notifyID) {
        this.notificationsDAO.removeNotification(notifyID);
        return "Successfully removed notification";
    }

    @PostMapping(path="/clearAllUser")
    public Object removeNotification(@RequestParam String username) {
        this.notificationsDAO.removeUserNotifications(username);

        return "Successfully removed all users notifications";
    }


    @RequestMapping(path="/getNotifications")
    public List<Object> getNotifications(@RequestParam String username){
        return this.notificationsDAO.getNotifications(username);
    }
}
