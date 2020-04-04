package jeaps.foodtruck.common.truck.route;

public enum Day {
    SUNDAY(1,"Sunday"),
    MONDAY(2, "Monday"),
    TUESDAY(3, "Tuesday"),
    WEDNESDAY(4, "Wednesday"),
    THURSDAY(5,"Thursday"),
    FRIDAY(6,"Friday"),
    SATURDAY(7,"Saturday");



    private int day;
    private String messaage;
    private Day(int day, String messaage) {
        this.day = day;
        this.messaage = messaage;
    }


    public String getMessaage() {
        return messaage;
    }

    public void setMessaage(String messaage) {
        this.messaage = messaage;
    }

    public int getDay() {
        return day;
    }

}
