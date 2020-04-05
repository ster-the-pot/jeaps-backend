package jeaps.foodtruck.common.truck;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Prices {
    LOW(0),CHEAP(1), MEDIUM(2), HIGH(3), GOLD_ENCRUSTED(4);

    private Integer floor;

    private Prices(Integer f){this.floor = f;}

    @JsonValue
    public Integer getFloor(){return this.floor;};

    public static Prices convert(Integer cost){
        if(cost >= GOLD_ENCRUSTED.getFloor()){
            return GOLD_ENCRUSTED;
        }
        if(cost >= HIGH.getFloor()){
            return HIGH;
        }
        if(cost >= MEDIUM.getFloor()){
            return MEDIUM;
        }
        if(cost >= CHEAP.getFloor()){
            return CHEAP;
        }
        return LOW;
    }
}
