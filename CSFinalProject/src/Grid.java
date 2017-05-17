import java.util.ArrayList;


public class Grid {
	
	//Role: Keeps track of all objects and their locations, gets next move location, signals end of game

	private Location[][] grid;
	private Player player;
	private ArrayList<Obstacle> obstacles;
	private ArrayList<Platform> platforms;
	static Location end;
	private int numRows = 10;
	private int numCols = 20;
	private Controller control;
	
	public Grid()
	{
		Location[][] grid = null;
		player = null;
		obstacles = new ArrayList<Obstacle>();
		platforms = new ArrayList<Platform>();
		end = new Location(0, 0);
		control = new Controller(); //(???)
	}
	
	//initializes positions of all items in the grid
	public Grid(ArrayList<Obstacle> obst, ArrayList<Platform> plat, Location endLoc)
	{
		Location[][] grid = new Location[numRows][numCols];
		obstacles = obst;
		platforms = plat;
		end = endLoc;
		control = new Controller(); //(???)
	}

	// checks if location to move to is empty and not an obstacle, returns Location or (if obstacle) null
	public Location checkNextLoc(Location loc)
	{
		if (isValid(loc))
		{
			Object obj = getObj(loc);
			if (obj != null)
			{
				if (obj instanceof Obstacle)
				{
					return new Location(0, 0); //NOT SURE IF THIS PART WORKS SINCE IT'S A NEW CONTROLLER	
				}
				return loc;
			}
		}
		return null;
	}
	
	//returns the object at the specified location, or null if empty
	//Precondition: location is valid in grid
	public Object getObj(Location loc)
	{
		Object obj = grid[loc.getYPos()][loc.getXPos()];
		if (obj != null)
			return (Object)obj;
		else
			return null;
	}
	
	//checks if location is valid in grid
	public boolean isValid(Location loc)
	{
		if (grid != null && loc.getXPos() < numRows && loc.getYPos() < numCols)
			return true;
		else
			return false;
	}

}
