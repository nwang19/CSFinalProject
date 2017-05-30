import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Level {
	static final Point start = new Point(25, 600);
	private int screenWidth, screenHeight;
	private Point end;
	private ArrayList<Obstacle> obstacles;
	private ArrayList<Platform> platforms;
	static int baseline;
	private int levelNum;
	
	public Level(int level)
	{
		//use switch cases?
		levelNum = level;
		screenWidth = 1000;
		screenHeight = 750;
		baseline = 700;
		if (level == 1)
		{
			end = new Point(1000, 690);
			obstacles = new ArrayList<Obstacle>();
			for (int x = 150; x <= 350; x += 100)
				obstacles.add(new Obstacle(x, baseline - Obstacle.height - 10));
			obstacles.add(new Obstacle(500, baseline-1));
			obstacles.add(new Obstacle(500-Obstacle.width, baseline-1));
			obstacles.add(new Obstacle(500-(2*Obstacle.width), baseline-1));
			obstacles.add(new Obstacle(700, baseline));
			obstacles.add(new Obstacle(900, baseline - Obstacle.height));
			platforms = new ArrayList<Platform>();
			for (int x = 0; x < (int)end.getX(); x += Platform.width)
				platforms.add(new Platform(x, baseline, Obstacle.width, Obstacle.height));
			platforms.add(new Platform(50, baseline - 50, 300, 20));
		}
		else if (level == 2)
		{
			end = new Point(1000, 690);
			obstacles = new ArrayList<Obstacle>();
			for (int x = 300; x < (int)end.getX() - 100; x += 200)
				obstacles.add(new Obstacle(x, 500));		
			obstacles.add(new Obstacle(200, baseline-5));
			platforms = new ArrayList<Platform>();
			for (int x = 0; x < (int)end.getX(); x += Platform.width)
				platforms.add(new Platform(x, baseline, 10, 10));
		}
		else if (level == 3)
		{
			obstacles = new ArrayList<Obstacle>();
			obstacles.add(new Obstacle(200, baseline+20));
			obstacles.add(new Obstacle(350, baseline+20));
			obstacles.add(new Obstacle(450, baseline+20));
			end = new Point(1000, 690);
			
			//obstacles.add(new Obstacle(500, 500));
			for (int x = 600; x < (int)end.getX() - 100; x += 200)
				obstacles.add(new Obstacle(x, 300));
			platforms = new ArrayList<Platform>();
			for (int x = 0; x < (int)end.getX(); x += Platform.width)
				platforms.add(new Platform(x, baseline, 10, 10));
		}
		else if (level == 4)
		{
			end = new Point(1000, 690);
			obstacles = new ArrayList<Obstacle>();
			//obstacles.add(new Obstacle(500, 500));
			for (int x = 100; x < (int)end.getX() - 100; x += 200)
				obstacles.add(new Obstacle(x, 300));
			platforms = new ArrayList<Platform>();
			for (int x = 0; x < (int)end.getX(); x += Platform.width)
				platforms.add(new Platform(x, baseline, 10, 10));
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
			if (loc.getX() >= 0 && loc.getX() <= screenWidth && loc.getY() >= 0 && loc.getY() <= screenHeight)
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
			Point point = loc;
			Rectangle next = new Rectangle((int)loc.getX()+10, (int)loc.getY(), Controller.pWidth-20, Controller.pHeight);
			for (Obstacle obst : obstacles)
			{
				if (obst != null && obst.intersects(next))
					return Level.start;
			}
			for (Platform plat : platforms)
			{
				if (plat != null && plat.intersects(next))
				{
					if (xState == Player.LEFT || xState == Player.RIGHT)
					{
						point = new Point ((int)player.getLocation(), (int)point.getY());
					}
					return point;
				
				}
			}
			if (yState == Player.STILL)
			{
				if (player was on a platform)
					player.setPlatState(false);
			}
			
		}
		return null;
	}
}
