package jeaps.foodtruck.common.truck.route;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Day {
    SUNDAY(),
    MONDAY(),
    TUESDAY(),
    WEDNESDAY(),
    THURSDAY(),
    FRIDAY(),
    SATURDAY();

    //NOTE: this forces the marshalling of spring to send enum as integer to frontend
    @JsonValue
    public int getValue(){
        return this.ordinal();
    }
}
