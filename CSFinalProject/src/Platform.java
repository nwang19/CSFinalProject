
public class Platform {

	//private Image blockImg;
	private Location myLoc;
	
	public Platform()
	{
		myLoc = new Location(0, 0);
	}
	
	public Platform(Location loc)
	{
		myLoc = loc;
	}
	
	public Location getLoc()
	{
		return myLoc;
	}
	
	public void setLoc(Location loc)
	{
		myLoc = loc
	}
}
