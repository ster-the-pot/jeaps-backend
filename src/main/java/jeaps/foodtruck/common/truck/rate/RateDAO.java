package jeaps.foodtruck.common.truck.rate;

import jeaps.foodtruck.common.image.Image;
import jeaps.foodtruck.common.image.ImageDAO;
import jeaps.foodtruck.common.truck.Truck;
import jeaps.foodtruck.common.truck.TruckDAO;
import jeaps.foodtruck.common.user.customer.Customer;
import jeaps.foodtruck.common.user.customer.CustomerDAO;
import jeaps.foodtruck.common.user.owner.Owner;
import jeaps.foodtruck.common.user.owner.OwnerDAO;
import jeaps.foodtruck.common.user.user.User;
import jeaps.foodtruck.common.user.user.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class RateDAO {
    @Autowired
    RateRepository rateRepo;

    @Autowired
    TruckDAO truckDAO;
    @Autowired
    CustomerDAO customerDAO;
    @Autowired
    UserDAO userDAO;
    @Autowired
    OwnerDAO ownerDAO;
    @Autowired
    ImageDAO imageDAO;

    public List<Rate> getRatingsObject(Integer truck_id) {

        Optional<Truck> truck = truckDAO.findById(truck_id);

        if(truck.isPresent()) {
            List<Rate> rate = truck.get().getRate();
            rate.forEach(r -> r.setPictures(null));
            return rate;
        }
        return new ArrayList<Rate>();
    }

    public List<Integer> getRatings(Integer truck_id) {
        Optional<Truck> truck = truckDAO.findById(truck_id);
        List<Integer> rates = new ArrayList<>();

        if(truck.isPresent()) {
            for(Rate r: truck.get().getRate()) {
                rates.add(r.getRate());
            }
        }
        return rates;
    }

    public void addRate(RateDTO r, Integer truck_id, String username) {
        Integer customer_id = this.userDAO.findByUsername(username).getId();
        Rate rate = new Rate();
        Optional<Customer> c = customerDAO.findByID(customer_id);
        Optional<Truck> truck = truckDAO.findById(truck_id);
        rate.setSender(username);
        rate.setTruck(truck.get());
        rate.setBody(r.getBody());
        rate.setRate(r.getRate());
        rate.setSubject(r.getSubject());

        //If a truck/customer rating does not yet exist then it should be added
        if(this.findByTruckAndCustomer(truck_id, username) == null) {


            List<Rate> rates = truck.get().getRate();
            rates.add(rate);
            truck.get().setRate(rates);
            //Set the new average rating for the truck

            this.calculateAvg(truck.get());


            truckDAO.save(truck.get());
        } else {
            throw new RuntimeException("Rating between customer and truck already exists");
        }
    }
    public void editRate(RateDTO r, Integer truck_id, String username) {

        Integer customer_id = this.userDAO.findByUsername(username).getId();

        Optional<Customer> c = customerDAO.findByID(customer_id);
        Optional<Truck> truck = truckDAO.findById(truck_id);


        //If a truck/customer rating does not yet exist then it should be added
        Rate rate;
        if((rate = this.findByTruckAndCustomer(truck_id, username)) != null) {
            List<Rate> rates = truck.get().getRate();
            rates.remove(rate);

            rate.setSender(username);
            rate.setTruck(truck.get());
            rate.setBody(r.getBody());
            rate.setRate(r.getRate());
            rate.setSubject(r.getSubject());


            rates.add(rate);
            truck.get().setRate(rates);

            this.calculateAvg(truck.get());

            truckDAO.save(truck.get());
        } else {
            throw new RuntimeException("Rating between customer and truck does not exist");
        }
    }

    public void removeRate(Integer truck_id, String username) {
        Integer customer_id = this.userDAO.findByUsername(username).getId();
        Rate rate = this.findByTruckAndCustomer(truck_id, username);
        if (rate == null) {
            throw new RuntimeException("Rating between customer and truck does not exist");
        }
        Optional<Customer> c = customerDAO.findByID(customer_id);
        Optional<Truck> truck = truckDAO.findById(truck_id);
        if(truck.isPresent()) {

            Owner owner = truck.get().getOwner();
            List<Truck> trucks = owner.getTrucks();
            trucks.remove(truck.get());

            List<Rate> rates = truck.get().getRate();
            rates.remove(rate);
            truck.get().setRate(rates);
            this.calculateAvg(truck.get());

            trucks.add(truck.get());
            owner.setTrucks(trucks);
            ownerDAO.save(owner);

            this.rateRepo.delete(rate);
        }
    }

    public Double getAvgRating(Integer id) {
        Optional<Truck> truck = truckDAO.findById(id);
        if(truck.isPresent() && truck.get().getAvgRating() != null){
            return truck.get().getAvgRating();
        }
        return null;
    }



    public Rate findByTruckAndCustomer(Integer truck_id, String username) {
        List<Rate> rates = rateRepo.findByTruck_id(truck_id);
        rates.forEach(r -> r.setPictures(null));
        for(Rate r: rates) {
            if (r.getSender().equals(username)) {
                return r;
            }
        }
        return null;
    }

    private void calculateAvg(Truck truck) {
        Integer tempTotal = null;
        for(Rate tempRate: truck.getRate()) {
            if(tempTotal == null) {
                tempTotal = tempRate.getRate();
            } else {
                tempTotal = tempTotal + tempRate.getRate();
            }
        }
        if(tempTotal != null) {
            truck.setAvgRating((tempTotal / ((double) truck.getRate().size())));
        } else {
            truck.setAvgRating(null);
        }

    }


    public Image savePicture(Integer truckID, String username, MultipartFile file) {

        Rate rate = this.findByTruckAndCustomer(truckID, username);
        if (rate != null) {
            Image i = this.imageDAO.saveFile(file);
            List<Image> pictures = rate.getPictures();
            pictures.add(i);
            rate.setPictures(pictures);
            this.rateRepo.save(rate);
            return i;
        }
        throw new RuntimeException("Failed to save Picture");
    }

    public List<Image> saveAllPicture(Integer truckID, String username, MultipartFile[] file) {
        Rate rate = this.findByTruckAndCustomer(truckID, username);
        List<Image> newImages = new ArrayList<>();
        if (rate != null) {
            if(!rate.getPictures().isEmpty()) {
                this.deleteAllPicture(truckID, username);
            }
            for(MultipartFile f: file) {
                Image i = this.imageDAO.saveFile(f);
                newImages.add(i);
            }

            rate.setPictures(newImages);
            this.rateRepo.save(rate);
        }

        throw new RuntimeException("Failed to save Picture");
    }

    /*public List<Image> getPictures(Integer truckID) {
        Optional<Truck> truck = this.truckRepo.findById(truckID);
        if(truck.isPresent()) {
            return truck.get().getPictures();
        }
        throw new RuntimeException("Failed to get Pictures");
    }*/

    public List<String> getPictureIds(Integer truckID, String username) {
        //Optional<Truck> truck = this.truckRepo.findById(truckID);
        Rate rate = this.findByTruckAndCustomer(truckID, username);

        if(rate != null) {
            List<String> str = new ArrayList<>();
            for(Image i: rate.getPictures()) {
                str.add(i.getId());
            }
            return str;
        }
        throw new RuntimeException("Failed to get Picture ids");
    }

    public void deletePicture(String imageID, Integer truckID, String username) {
        Rate rate = this.findByTruckAndCustomer(truckID, username);
        Image oldImage = this.imageDAO.getFile(imageID);
        if(rate != null) {
            List<Image> i = rate.getPictures();
            i.remove(oldImage);

            rate.setPictures(i);
            this.rateRepo.save(rate);
            this.imageDAO.deleteFile(imageID);
        }
    }

    public void deleteAllPicture(Integer truckID, String username) {

        Rate rate = this.findByTruckAndCustomer(truckID, username);
        if(rate != null) {
            List<Image> i = rate.getPictures();
            for(Image old: i) {
                this.imageDAO.deleteFile(old.getId());
            }

            rate.setPictures(new ArrayList<>());
            this.rateRepo.save(rate);
        }
    }
}
