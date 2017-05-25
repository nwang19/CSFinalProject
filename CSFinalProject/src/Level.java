import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Level {
	static final Point start = new Point(50, 500);
	private int width, height;
	private Point end;
	private ArrayList<Obstacle> obstacles;
	private ArrayList<Platform> platforms;
	private int levelNum;
	
	public Level(int level)
	{
		//use switch cases?
		levelNum = level;
		width = 1000;
		height = 1000;
		if (level == 1)
		{
			end = new Point(1000, 700);
			obstacles = new ArrayList<Obstacle>();
			for (int x = 400; x < (int)end.getX() - 100; x += 200)
				obstacles.add(new Obstacle(x, 500));
			platforms = new ArrayList<Platform>();
			for (int x = 0; x < (int)end.getX(); x += Platform.width)
				platforms.add(new Platform(x, 700, 10, 10));
		}
		else if (level == 2)
		{
			end = new Point(1000, 700);
			obstacles = new ArrayList<Obstacle>();
			//obstacles.add(new Obstacle(500, 500));
			for (int x = 400; x < (int)end.getX() - 100; x += 200)
				obstacles.add(new Obstacle(x, 300));
			platforms = new ArrayList<Platform>();
			for (int x = 0; x < (int)end.getX(); x += Platform.width)
				platforms.add(new Platform(x, 700, 10, 10));
		}
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
			//not sure if loop works
			for (Obstacle obst : obstacles)
			{
				if (obst.contains(loc))
					return obst;
			}
		}
		return null;
	}
	
	
	//returns the platform that contains the specified point, or null if no such platform
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
	
	
	// checks if location to move to, returns location to move to
	// or null, if the location is outside of the grid bounds
	public Point checkNextLoc(Point loc, int levelNum, int xState, int yState)
	{
		if (isValid(loc))
		{
			Point point;
			Obstacle obst = getObstacle(new Point((int)(loc.getX() + Controller.pWidth/2), (int)loc.getY()));
			Rectangle next = new Rectangle((int)loc.getX(), (int)loc.getY(), Controller.pWidth, Controller.pHeight);
			if (obst != null && obst.intersects(next))
				point = Level.start;
			else
			{
				Platform plat = getPlatform(loc);
				point = loc;
				if (plat != null)
				{
					point = plat.getLocation();
					if (xState == Player.LEFT)
						point = new Point((int)(point.getX() - Controller.pWidth), (int)point.getY());
					else if (xState == Player.RIGHT)
						point = new Point((int)point.getX(), (int)point.getY());
					if (yState == Player.UP)
						point = new Point((int)point.getX(), (int)(point.getY() - Controller.pHeight));
					else if (yState == Player.DOWN)
						point = new Point((int)point.getX(), (int)(point.getY() + Controller.pHeight));
				}
			}
			return point;
		}
		return null;
	}
	
	/*
	 * Instruction Description (b/c i'm bored & avoiding my responsibilities):
	 * You are a student at Westview. Use the arrow keys to move left, right, and up to navigate
	 * your way through beige brick and palm trees to reach the end of each level, or year. Make sure
	 * to avoid sea gulls, books, and, most of all, toxic AP culture-- they
	 * will all make you redo the level. Good luck, and don't forget to enjoy the ride!
	 * 
	 * */
		
}
