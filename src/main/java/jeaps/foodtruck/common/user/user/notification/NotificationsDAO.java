package jeaps.foodtruck.common.user.user.notification;

import jeaps.foodtruck.common.truck.Truck;
import jeaps.foodtruck.common.truck.TruckDAO;
import jeaps.foodtruck.common.user.customer.Customer;
import jeaps.foodtruck.common.user.customer.CustomerDAO;
import jeaps.foodtruck.common.user.owner.Owner;
import jeaps.foodtruck.common.user.owner.OwnerDAO;
import jeaps.foodtruck.common.user.user.User;
import jeaps.foodtruck.common.user.user.UserDAO;
import jeaps.foodtruck.common.user.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class NotificationsDAO {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private OwnerDAO ownerDAO;
    @Autowired
    private CustomerDAO customerDAO;
    @Autowired
    private TruckDAO truckDAO;
    @Autowired
    private NotificationsRepository notificationsRepo;


    public void save(Notifications n) { this.notificationsRepo.save(n); }

    public void save(NotificationsDTO nDTO) {
        Notifications n = new Notifications();
        n.setBody(nDTO.getBody());
        if(nDTO.getId() != null) {
            n.setId(nDTO.getId());
        }
        n.setSubject(nDTO.getSubject());
        n.setType(nDTO.getType());
        this.notificationsRepo.save(n);
    }

    public void sendAll(Notifications n) {
        Iterable<User> user = this.userDAO.findAll();
        for(User u: user) {
            List<Notifications> notify = u.getNotifications();
            notify.add(n);
            u.setNotifications(notify);
            this.userDAO.save(u);
        }

    }
    public void sendAllOwner(Notifications n) {
        Iterable<Owner> owners = this.ownerDAO.findAll();
        for(Owner o: owners) {
            Optional<User> u = this.userDAO.findById(o.getId());
            List<Notifications> notify = u.get().getNotifications();
            notify.add(n);
            u.get().setNotifications(notify);
            this.userDAO.save(u.get());

        }
    }

    public void sendAllCustomer(Notifications n) {
        Iterable<Customer> customers = this.customerDAO.findAll();
        for(Customer c: customers) {
            Optional<User> u = this.userDAO.findById(c.getId());
            List<Notifications> notify = u.get().getNotifications();
            notify.add(n);
            u.get().setNotifications(notify);
            this.userDAO.save(u.get());
        }
    }

    public void sendAllSubscribers(Notifications n, String username) {

    }

    public void sendAllTruckSubscribers(Notifications n, String username, Integer truckID) {
       User user = this.userDAO.findByUsername(username);
       Optional<Owner> o = this.ownerDAO.findById(user.getId());
       if(o.isPresent()) {
           Optional<Truck> t = this.truckDAO.findById(truckID);
           List<Truck> check = o.get().getTrucks();
           if(check.contains(t)) {
               List<Customer> customers = t.get().getCustomers();
               for(Customer c: customers) {
                   Optional<User> u = this.userDAO.findById(c.getId());
                   List<Notifications> notify = u.get().getNotifications();
                   notify.add(n);
                   u.get().setNotifications(notify);
                   this.userDAO.save(u.get());
               }

           }


       }

    }

    public void sendToOwner(Notifications n, String username) {

    }
}
