import java.util.ArrayList;
import java.util.Iterator;


public class TestLocation {

    /**
     * @param args
     */
    public static void main(String[] args) {
    	Location loc = new Location("Calgary");
    	LocationFactory lf = new LocationFactory();
    	lf.locationMaker(loc);

        System.out.println(loc.distanceFromOrigin);
        System.out.println(loc.origin);
        System.out.println(loc.destination);
        System.out.println(loc.tempInCelcius);
        System.out.println(loc.getPlaces("lodging"));
        


    }

}
