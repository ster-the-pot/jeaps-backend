package jeaps.foodtruck.common.truck.rate;

public enum RateEnum {
    ZeroStar(0),
    OneStar(1),
    TwoStar(2),
    ThreeStar(3),
    FourStar(4),
    FiveStar(5);

    int star;
    RateEnum(int star) {
        this.star = star;
    }

    public static RateEnum getStar(int star) {
        if(RateEnum.FiveStar.getStar() < star  || RateEnum.ZeroStar.getStar() > star) {
            return null;
        }
        for (RateEnum e: RateEnum.values()) {
            if(e.star == star) {
                return e;
            }
        }
        return null;
    }

    public int getStar() {
        return star;
    }
}
