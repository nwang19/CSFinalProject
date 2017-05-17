import java.awt.Point;
import java.awt.Rectangle;

public class Player extends Rectangle
{
	//Job: stores lives, gets location to move to next, and updates its record of its position.

	private int lives; 
	private Point loc; 
	private Controller control; 
	private Grid gr; 
	
	public Player()
	{
		lives = 3;
		control = new Controller(gr);
		gr = new Grid();
		
	}
	
	public Player(int x, int y, int width, int height)
	{
		super(x, y, width, height);
		lives = 3;
		control = new Controller(gr);
		gr = new Grid();
		
	}

	public int getLives()
	{
		return lives;
	}
	
	public void setLives(int numLives)
	{
		lives = numLives;
	}

	/*public void setLocation(Location newLoc)
	{
		loc = newLoc;
	}
	public Location getLocation()
	{
		return loc;
	}*/

}
