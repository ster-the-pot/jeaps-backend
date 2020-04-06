package jeaps.foodtruck.common.truck.route.times;

import jeaps.foodtruck.common.truck.route.Day;

import java.util.Date;

public class TimeDTO {
    private Integer id;

    Date startTime;
    Date endTime;

    public TimeDTO(){};
    public TimeDTO(Time t) {
        startTime = t.getStartTime();
        endTime = t.getEndTime();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
