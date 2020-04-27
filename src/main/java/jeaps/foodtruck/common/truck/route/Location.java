package jeaps.foodtruck.common.truck.route;

import javax.persistence.Embeddable;

@Embeddable
public class Location {
    private Double longitude;
    private Double latitude;

    public Location(Double longitude, Double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    // gets the distance between two locations
    public static double getDistance(Location L1, Location L2) {

        double temp1 = (L1.latitude - L2.latitude) * (L1.latitude - L2.latitude);
        double temp2 = (L1.longitude - L2.longitude) * (L1.longitude - L2.longitude);

        return java.lang.Math.sqrt(temp1 + temp2);
    }
}
