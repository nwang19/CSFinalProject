import java.awt.Point;
import java.util.ArrayList;

public class Level {
	static final Point start = new Point(10, 10);
	private int width, height;
	private int pWidth, pHeight;
	private Point end;
	private ArrayList<Obstacle> obstacles;
	private ArrayList<Platform> platforms;
	private int levelNum;
	
	public Level(int level)
	{
		if (level == 1)
		{
			levelNum = level;
			end = new Point(500,10);
			obstacles = new ArrayList<Obstacle>();
			//fill ArrayList with obstacles at locations
			platforms = new ArrayList<Platform>();
			for (int x = (int)end.getX(); x > 0; x /= 10)
			{
				platforms.add(new Platform(x, 5));
			}
			width = 500;
			height = 100;
		}
		//else if (level == 2) and so on... (use switch cases??)
	}
	
	
	public ArrayList<Obstacle> getObstacles()
	{
		return obstacles;
	}
	
	
	public ArrayList<Platform> getPlatforms()
	{
		return platforms;
	}
	
	
	public Point getEnd()
	{
		return end;
	}
	
	
	public int getLevelNum()
	{
		return levelNum;
	}
	
	
	//checks if location is within bound of screen
	public boolean isValid(Point loc)
	{
		if (levelNum >= 0)
		{
			if (loc.getX() >= 0 && loc.getX() <= width && loc.getY() >= 0 && loc.getY() <= height)
				return true;
		}
		return false;
	}
	
	
	//returns the obstacle that contains the specified point, or null if no such obstacle
	public Obstacle getObstacle(Point loc)
	{
		if (levelNum != 0 && isValid(loc))
		{
			for (Obstacle obst : obstacles)
			{
				if (obst.contains(loc))
					return obst;
			}
		}
		return null;
	}
	
	
	//returns the obstacle that contains the specified point, or null if no such platform
	public Platform getPlatform(Point loc)
	{
		if (levelNum != 0 && isValid(loc))
		{
			for (Platform block : platforms)
			{
				if (block.contains(loc))
					return block;
			}
		}
		return null;
	}
	
	
	//Stores the width and height of the player
	public void storePlayerSize(Player player)
	{
		pHeight = (int) player.getHeight();
		pWidth = (int) player.getWidth();
	}
	
	
	// checks if location to move to, returns location to move to
	// or null, if the location is outside of the grid bounds
	public Point checkNextLoc(Point loc, int levelNum, int state, boolean up)
	{
		if (isValid(loc))
		{
			Point point;
			Obstacle obst = getObstacle(loc);
			if (obst != null)
				point = Level.start;
			else
			{
				Platform plat = getPlatform(loc);
				point = plat.getLocation();
				if (plat != null)
				{
					point = plat.getLocation();	
					if (state == Player.LEFT)
						point = new Point((int)(point.getX() - pWidth), (int)point.getY());
					else if (state == Player.RIGHT)
						point = new Point((int)point.getX(), (int)point.getY());
					if (up == true)
						point = new Point((int)point.getX(), (int)(point.getY() - pHeight));
				}
				return point;
			}
		}
		return null;
	}
	
	/*
	 * Instruction Description (b/c i'm bored & avoiding my responsibilities):
	 * You are a student at Westview. Use the arrow keys to moe left, right, and up to navigate
	 * your way through beige brick and palm trees to reach the end of each level, or year. Make sure
	 * to avoid sea gulls, books, other zombified students (rip), and most of all, toxic AP culture-- they
	 * will all make you lose a life. Good luck, and don't forget to enjoy the ride!
	 * 
	 * */
		
}
