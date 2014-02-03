import java.util.ArrayList;
import java.util.HashMap;


public class Location {
	LocationFactory lf = new LocationFactory();
	public HashMap<String,ArrayList<String>> places = new HashMap<>();
	public String origin = "kelowna, bc";
	public String destination;
	public double tempInCelcius;
	public String weatherDescription;
	public double distanceFromOrigin;
	public double distanceFromOriginDriving;
	public double distanceFromOriginFlying;
	
	public Location(String destination){
		this.destination = destination;
		lf.build(this);
	}
	public Location(String origin, String destination){
		this.origin = origin;
		this.destination = destination;
		lf.build(this);
	}
		
	public void setOrigin(String origin){
		this.origin = origin;
	}
	public void setDestination(String destination){
		this.destination=destination;
	}
	
	public String estimateTravelCost(){
		return "Flight to " + this.destination + ", from " + this.origin + " would cost approximately $" + lf.round(this.distanceFromOrigin/2.02,2);
	}
	public String estimateTravelCost(String methodOfTravel){
		if (methodOfTravel.equalsIgnoreCase("flying"))
			return estimateTravelCost();
		else if (methodOfTravel.equalsIgnoreCase("driving"))
			return "Driving to " + this.destination + " from " + this.origin + " would cost approximately $" + lf.round((this.distanceFromOrigin/10)*1.2, 2);
			
		return null;
	}
	
	public String weatherDescription(){
		return "It is currently " + tempInCelcius + " degrees in " + destination + " with " + weatherDescription;
	}
	
	public String getPlaces(String keyword){
		if (!places.containsKey(keyword)){
			if (!lf.getPlaces(this, keyword))
				return "I could not find any places for " + keyword;
		} 
		ArrayList<String> pl = places.get(keyword);
		String returnString = "";
		for (String s : pl)
			returnString += s + "\n";
			return returnString;
	}
}
