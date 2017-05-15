
public class Obstacle {

	private Location myLoc;
	private Image myImg;
	
	public Obstacle(Location loc, Image img)
	{
		myLoc = loc;
		myImg = img;
	}
	
	public Location getLoc()
	{
		return myLoc;
	}
	
	public Location setLoc(Location loc)
	{
		myLoc = loc;
	}
	
	public getImg(Image img)
	{
		return myImg;
	}
	
	public setImg(Image img)
	{
		myImg = img;
	}
	
}
