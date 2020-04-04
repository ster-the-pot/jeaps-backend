package jeaps.foodtruck.common.truck.rate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jeaps.foodtruck.common.truck.Truck;
import jeaps.foodtruck.common.user.customer.Customer;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.ManyToOne;
import java.util.Date;

public class RateDTO {
    private Integer id;

    private Integer rate;
    private String subject;
    private String body;
    private Date createDateTime;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
