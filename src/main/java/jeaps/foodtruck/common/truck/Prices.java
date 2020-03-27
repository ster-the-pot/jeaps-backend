package jeaps.foodtruck.common.truck;

public enum Prices {
    LOW(0), MEDIUM(1), HIGH(2), GOLD_ENCRUSTED(3);

    private Integer floor;

    private Prices(Integer f){this.floor = f;}

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
        return LOW;
    }
}
