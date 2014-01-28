
public class TestLocation {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Location loc = new LocationFactory().locationMaker("alberta");
		
		System.out.println(loc.distanceFromOrigin);
		System.out.println(loc.origin);
		System.out.println(loc.place);
		System.out.println(loc.tempInCelcius);

	}

}
