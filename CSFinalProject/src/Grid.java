import java.awt.Point;
import java.util.ArrayList;


public class Grid {
	
	//Role: Keeps track of all objects and their locations, gets next move location, signals end of game

	private Location[][] grid;
	private Player player;
	private ArrayList<Obstacle> obstacles;
	private ArrayList<Platform> platforms;
	//static Location end;
	private int numRows = 10;
	private int numCols = 20;
	private Controller control;
	
	public Grid()
	{
		Location[][] grid = null;
		player = null;
		obstacles = new ArrayList<Obstacle>();
		platforms = new ArrayList<Platform>();
		//end = new Location(0, 0);
		control = new Controller(); //(???)
	}
	
	//initializes positions of all items in the grid
	public Grid(ArrayList<Obstacle> obst, ArrayList<Platform> plat, Location endLoc, Player player)
	{
		Location[][] grid = new Location[numRows][numCols];
		obstacles = obst;
		platforms = plat;
		end = endLoc;
		control = new Controller(); //(???)
	}

	// checks if location to move to, returns location to move to
	// or null, if the location is outside of the grid bounds
	public Point checkNextLoc(Point loc, int levelNum, int state, boolean up)
	{
		if (isValid(loc))
		{
			Level level = new Level(levelNum);
			ArrayList<Obstacle> obstacles = level.getObstacles();
			for (Obstacle obst : obstacles)
			{
				if (obst.contains(loc))
					return Level.start;
			}
			ArrayList<Platform> platforms = level.getPlatforms();
			for (Platform block : platforms)
			{
				if (block.contains(loc))
				{
					if (state == Player.LEFT)
						//** how to get player width :/
						loc = new Point((int)(block.getX() - player.getWidth()), (int)loc.getY());
					else if (state == Player.RIGHT)
						loc = new Point((int)block.getX(), (int)loc.getY());
					if (up == true)
						loc = new Point((int)loc.getX(), (int)(block.getY() - block.getHeight()));
					return loc;
				}
			}
		}
		return null;
	}
	
	//returns the object at the specified location, or null if empty
	//Precondition: location is valid in grid
	public Object getObj(Point loc)
	{
		Object obj = grid[(int)loc.getY()][(int)loc.getX()];
		if (obj != null)
			return (Object)obj;
		else
			return null;
	}
	
	//checks if location is within bound of grid/screen
	public boolean isValid(Point loc)
	{
		if (grid != null && loc.getX() < numRows && loc.getY() < numCols)
			return true;
		else
			return false;
	}

}
