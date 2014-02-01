import java.util.ArrayList;
import java.util.HashMap;


public class Location {
	LocationFactory lf = new LocationFactory();
	public HashMap<String,ArrayList> places = new HashMap();
	public String origin = "kelowna, bc";
	public String destination;
	public double tempInCelcius;
	public String weatherDescription;
	public double distanceFromOrigin;
	public double distanceFromOriginDriving;
	public double distanceFromOriginFlying;
	
	public Location(String destination){
		this.destination = destination;
		new LocationFactory().locationMaker(this);
	}
	public Location(String origin, String destination){
		this.origin = origin;
		this.destination = destination;
		new LocationFactory().locationMaker(this);
	}
		
	public void setOrigin(String origin){
		this.origin = origin;
	}
	
	public String estimateTravelCost(){
		
		return null;
	}
	public String estimateTravelCost(String methodOfTravel){
		
		return null;
	}
	public String getDistance(String methodOfTravel){
		
		return null;
	}
	
	public String weatherDescription(){
		return "It is currently " + tempInCelcius + " degrees in " + destination + " with " + weatherDescription;
	}
	
	public String getPlaces(String keyword){
		if (!places.containsKey(keyword)){
			lf.getPlaces(this, keyword);
		} 
		ArrayList<String> pl = places.get(keyword);
		String returnString = "";
		for (String s : pl)
			returnString += s + "\n";
			return returnString;
	}
}
