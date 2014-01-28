import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;


public class LocationFactory {
	
	public Location locationMaker(String destination){
		Location loc = new Location();
		loc.place = destination;
		loc.tempInCelcius = weatherInCelcius(destination);
		loc.distanceFromOrigin = getDistance(loc.origin,destination);
		return loc;	
	}
	/**
	 * @param args
	 * @throws IOException 
	 * @throws MalformedURLException 
	 */
	/*
	 * Return values could be to any decimal place, we only want two.
	 */
	private static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();
	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
	
	private static double getDistance(String origin, String destination) {
		/*
		 * Base URL for distance query. Takes an origin and destination parameter to constructing the String 'url'.
		 * URLEncoder is used to deal with spaces and such.
		 */
		String url = "http://maps.googleapis.com/maps/api/distancematrix/json?origins=" + URLEncoder.encode(origin) + "&destinations=" + URLEncoder.encode(destination) + "&mode=driving&sensor=false&language=en-EN";
		
		/*
		 *  We call the openStream() method from the URL class, and read the input line by line with the Scanner class.
		 *  On error return 0
		 */
		try {
		Scanner scan = new Scanner(new URL(url).openStream());
		 String str = new String();
		 while (scan.hasNext()){
			str += scan.nextLine() + "\n";
		 } 
		 /*
		  *  org.Json library. A JSON object is created from the above String.
		  *  
		  *  Example of the JSON object return can be seen by navigating to the following URL.
		  *  http://maps.googleapis.com/maps/api/distancematrix/json?origins=Kelowna%2C+BC&destinations=Vernon%2C+BC&mode=driving&sensor=false&language=en-EN
		  */
		 JSONObject json = new JSONObject(str);
		 JSONObject j1 = json.getJSONArray("rows").getJSONObject(0).getJSONArray("elements").getJSONObject(0);
		 JSONObject distance = j1.getJSONObject("distance");
	     double distanceInMeters = distance.getDouble("value");
		/*
		 * Convert to KM before returning.
		 */
		return round((distanceInMeters/1000),2);
		} catch (MalformedURLException e){} catch (IOException e) {}
		
		//error case, return 0
		return 0;
	}
	
	private static double weatherInCelcius(String s) {
		/*
		 * Construct URL from paramters, open the stream, read it, and create a JSON object from it.
		 */
		String url = "http://api.openweathermap.org/data/2.5/weather?q=" + URLEncoder.encode(s);
	
		try {
		Scanner scan = new Scanner(new URL(url).openStream());
		 String str = new String();
		 while (scan.hasNext()){
			str += scan.nextLine() + "\n";
		 } 
		 /* Navigate JSON to find temperature and description.
		  * 
		  * Sample URL
		  * http://api.openweathermap.org/data/2.5/weather?q=kelowna,bc
		  */
		 JSONObject json = new JSONObject(str);
		 double tempInKelvin = json.getJSONObject("main").getDouble("temp");
		 return round((tempInKelvin - 273.15),2);
		} catch (MalformedURLException e){} catch (IOException e) {}
	 
		/*
		 * Return 0 in error case;
		 */
		return 0;
	}
	
	private static double[] geocode(String s) throws IOException{
		String geocodeUrl = "http://maps.googleapis.com/maps/api/geocode/json?address=";
    	geocodeUrl += URLEncoder.encode(s) + "&sensor=true";
    	System.out.println(geocodeUrl);
		 Scanner scan = new Scanner(new URL(geocodeUrl).openStream());
		 String str = new String();
		 while (scan.hasNext()){
			str += scan.nextLine() + "\n";
			
		 }
		 JSONObject jsonObject = new JSONObject(str);
		 if  (jsonObject.getString("status").equals("OK")){	 
		 //geocode address.
		 JSONArray routes = jsonObject.getJSONArray("results");
		 JSONObject geometry = routes.getJSONObject(0).getJSONObject("geometry");
		 JSONObject location = geometry.getJSONObject("location");
		 double lat = location.getDouble("lat");
		 double lng = location.getDouble("lng");
		 double[] loc = {lat,lng};
		 return loc;
		 }
		return new double[]{0,0}; 
	}
	
	private static HashMap getPlaces(String place, String keyword) throws IOException{
		double[] geo = geocode(place);
		String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?" +
					 "location=" + geo[0] + "," + geo[1] + "&types=" + keyword + 
					 "&rankby=prominence&radius=100&sensor=false&key=AIzaSyD-GnR8Af9fm57GuOz9kdLTzjPMjfPeXiQ";
		 Scanner scan = new Scanner(new URL(url).openStream());
		 String str = new String();
		 while (scan.hasNext()){
			str += scan.nextLine() + "\n";
			
		 }
		 JSONArray json = new JSONObject(str).getJSONArray("results");
		 
		 for(int i=0;i<20;i++){
			JSONObject tmp = json.getJSONObject(i); 
			System.out.println(tmp.getString("name"));
		 }
		return null;
	}

}
