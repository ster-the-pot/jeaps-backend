package jeaps.foodtruck.common.truck.rate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jeaps.foodtruck.common.image.Image;
import jeaps.foodtruck.common.truck.Truck;
import jeaps.foodtruck.common.user.customer.Customer;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer rate;
    private String subject;
    private String body;
    @CreationTimestamp
    private Date createDateTime;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL)
    private List<Image> pictures = new ArrayList<>();


    @JsonBackReference
    @ManyToOne()
    private Truck truck;

    private String sender;

    public List<Image> getPictures() {
        return pictures;
    }

    public void setPictures(List<Image> pictures) {
        this.pictures = pictures;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }
}
