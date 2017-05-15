import java.awt.Image;


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
	
	public void setLoc(Location loc)
	{
		myLoc = loc;
	}
	
	public Image getImg(Image img)
	{
		return myImg;
	}
	
	public void setImg(Image img)
	{
		myImg = img;
	}
	
}
