
public class Player 
{
	//Job: stores lives, gets location to move to next, and updates its record of its position

	private int lives; 
	private Location loc; 
	private Boolean upKey, rightKey, leftKey;
	private Controller control; 
	private Grid gr; 
	
	public Player()
	{
		lives = 3;
		loc = new Location(0,0);
		upKey = false;
		rightKey = false;
		leftKey = false;
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

	//passes location to Controller to check if it can move there by calling Grid’s checkNextLoc
	public Location checkLocation()
	{
		
		
	}

	public void setLocation(Location newLoc)
	{
		loc = newLoc;
	}
	public Location getLocation()
	{
		return loc;
	}

}
