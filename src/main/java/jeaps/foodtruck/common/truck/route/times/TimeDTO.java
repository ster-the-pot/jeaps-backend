package jeaps.foodtruck.common.truck.route.times;

import jeaps.foodtruck.common.truck.route.Day;

import java.util.Date;

public class TimeDTO {
    private Integer id;
    Day day;

    Date startTime;
    Date endTime;

    public TimeDTO(){};
    public TimeDTO(Time t) {
        id = t.getId();
        day = t.getDay();
        startTime = t.getStartTime();
        endTime = t.getEndTime();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
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
