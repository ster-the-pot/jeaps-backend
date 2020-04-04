package jeaps.foodtruck.common.truck.route.times;

import jeaps.foodtruck.common.truck.route.Day;

public class TimeDTO {
    private Integer id;
    Day day;

    String times;

    public TimeDTO(){};
    public TimeDTO(Time t) {
        id = t.getId();
        day = t.getDay();
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

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }
}
