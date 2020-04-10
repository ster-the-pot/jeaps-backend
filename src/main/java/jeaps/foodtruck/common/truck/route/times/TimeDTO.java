package jeaps.foodtruck.common.truck.route.times;

import jeaps.foodtruck.common.truck.route.Day;

import javax.persistence.Id;
import java.util.Date;

public class TimeDTO {


    private Day day;

    private Date startTime;
    private Date endTime;

    public TimeDTO(){};
    public TimeDTO(Time t) {

        day = t.getDay();
        startTime = t.getStartTime();
        endTime = t.getEndTime();

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
    /*private Integer id;

    private Date sundayStartTime;
    private Date mondayStartTime;
    private Date tuesdayStartTime;
    private Date wednesdayStartTime;
    private Date thursdayStartTime;
    private Date fridayStartTime;
    private Date saturdayStartTime;

    private Date sundayEndTime;
    private Date mondayEndTime;
    private Date tuesdayEndTime;
    private Date wednesdayEndTime;
    private Date thursdayEndTime;
    private Date fridayEndTime;
    private Date saturdayEndTime;



    public TimeDTO(){};



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Date getSundayStartTime() {
        return sundayStartTime;
    }

    public void setSundayStartTime(Date sundayStartTime) {
        this.sundayStartTime = sundayStartTime;
    }

    public Date getMondayStartTime() {
        return mondayStartTime;
    }

    public void setMondayStartTime(Date mondayStartTime) {
        this.mondayStartTime = mondayStartTime;
    }

    public Date getTuesdayStartTime() {
        return tuesdayStartTime;
    }

    public void setTuesdayStartTime(Date tuesdayStartTime) {
        this.tuesdayStartTime = tuesdayStartTime;
    }

    public Date getWednesdayStartTime() {
        return wednesdayStartTime;
    }

    public void setWednesdayStartTime(Date wednesdayStartTime) {
        this.wednesdayStartTime = wednesdayStartTime;
    }

    public Date getThursdayStartTime() {
        return thursdayStartTime;
    }

    public void setThursdayStartTime(Date thursdayStartTime) {
        this.thursdayStartTime = thursdayStartTime;
    }

    public Date getFridayStartTime() {
        return fridayStartTime;
    }

    public void setFridayStartTime(Date fridayStartTime) {
        this.fridayStartTime = fridayStartTime;
    }

    public Date getSaturdayStartTime() {
        return saturdayStartTime;
    }

    public void setSaturdayStartTime(Date saturdayStartTime) {
        this.saturdayStartTime = saturdayStartTime;
    }

    public Date getSundayEndTime() {
        return sundayEndTime;
    }

    public void setSundayEndTime(Date sundayEndTime) {
        this.sundayEndTime = sundayEndTime;
    }

    public Date getMondayEndTime() {
        return mondayEndTime;
    }

    public void setMondayEndTime(Date mondayEndTime) {
        this.mondayEndTime = mondayEndTime;
    }

    public Date getTuesdayEndTime() {
        return tuesdayEndTime;
    }

    public void setTuesdayEndTime(Date tuesdayEndTime) {
        this.tuesdayEndTime = tuesdayEndTime;
    }

    public Date getWednesdayEndTime() {
        return wednesdayEndTime;
    }

    public void setWednesdayEndTime(Date wednesdayEndTime) {
        this.wednesdayEndTime = wednesdayEndTime;
    }

    public Date getThursdayEndTime() {
        return thursdayEndTime;
    }

    public void setThursdayEndTime(Date thursdayEndTime) {
        this.thursdayEndTime = thursdayEndTime;
    }

    public Date getFridayEndTime() {
        return fridayEndTime;
    }

    public void setFridayEndTime(Date fridayEndTime) {
        this.fridayEndTime = fridayEndTime;
    }

    public Date getSaturdayEndTime() {
        return saturdayEndTime;
    }

    public void setSaturdayEndTime(Date saturdayEndTime) {
        this.saturdayEndTime = saturdayEndTime;
    }*/
}
