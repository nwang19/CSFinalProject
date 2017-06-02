import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Level {
	private Point start;
	private int screenWidth, screenHeight;
	private Point end;
	private ArrayList<Obstacle> obstacles;
	private ArrayList<Platform> platforms;
	static int baseline;
	private int levelNum;
	
	public Level(int level)
	{
		levelNum = level;
		screenWidth = 1000;
		screenHeight = 750;
		baseline = 700;
		if (level == 1)
		{
			start = new Point(0, 0);
			end = new Point(1000, 690);
			obstacles = new ArrayList<Obstacle>();
			obstacles.add(new Seagull(180, baseline - 180));
			obstacles.add(new Book(400, baseline-15));
			obstacles.add(new Book(750, baseline-15));
			obstacles.add(new ToxicAP(200, baseline-408));
			obstacles.add(new ToxicAP(200-Obstacle.WIDTH, baseline-408));
			platforms = new ArrayList<Platform>();
			for (int x = 0; x < (int)end.getX(); x += 300)
				platforms.add(new Platform(x, baseline, 300, 31));
			platforms.add(new Platform(0, baseline - 400, 300, 31));
			platforms.add(new Platform(350, baseline - 350, 250, 31));
			platforms.add(new Platform(550, baseline - 200, 120, 31));
			platforms.add(new Platform(180, baseline - 125, 180, 31));
			platforms.add(new MovingObject(70, baseline - 50, 300, 31, Player.LEFT, 25, 500));
		}
		else if (level == 2)
		{
			start = new Point(0, 0);
			end = new Point(1000, 690);
			obstacles = new ArrayList<Obstacle>();
			obstacles.add(new ToxicAP(600, baseline-7));
			obstacles.add(new ToxicAP(600-Obstacle.WIDTH, baseline-7));
			obstacles.add(new ToxicAP(220, baseline-210));
			obstacles.add(new ToxicAP(220-Obstacle.WIDTH, baseline-210));
			obstacles.add(new Book(200, baseline-225));
			obstacles.add(new Book(350, baseline-225));
			obstacles.add(new Book(760, baseline-30));
			obstacles.add(new Seagull(150, baseline-250));
			obstacles.add(new Seagull(580, baseline-470));
			obstacles.add(new Seagull(630, baseline-470));
			obstacles.add(new Seagull(770, baseline-326)); 
			
			platforms = new ArrayList<Platform>();
			for (int x = 0; x < (int)end.getX(); x += 300)
				platforms.add(new Platform(x, baseline,300, 31));
			platforms.add(new Platform(0, baseline - 400, 150, 31));
			platforms.add(new Platform(180, baseline - 200, 100, 31));
			platforms.add(new Platform(250, baseline - 350, 200, 31));
			platforms.add(new Platform(550, baseline - 420, 120, 31));
			platforms.add(new Platform(700, baseline - 280, 100, 31));
		}
		else if (level == 3)
		{
			start = new Point(0, 0);
			obstacles = new ArrayList<Obstacle>();
			obstacles.add(new ToxicAP(200, baseline-408));
			obstacles.add(new ToxicAP(200-Obstacle.WIDTH, baseline-408));
			obstacles.add(new ToxicAP(300, baseline-7));
			obstacles.add(new ToxicAP(300-Obstacle.WIDTH, baseline-7));
			obstacles.add(new ToxicAP(345, baseline-288));
			obstacles.add(new ToxicAP(345-Obstacle.WIDTH, baseline-288));
			obstacles.add(new ToxicAP(800, baseline-7));
			
			obstacles.add(new Seagull(335, baseline-435));
			obstacles.add(new Seagull(335, baseline-380));
			obstacles.add(new Seagull(550, baseline-40));
			obstacles.add(new Seagull(755, baseline-40));	
			obstacles.add(new Seagull(575, baseline-158));
			obstacles.add(new Seagull(650, baseline-300));
			
			obstacles.add(new Book(560, baseline-400));
			obstacles.add(new Book(560, baseline-420));			
			obstacles.add(new Book(680, baseline-265));
			obstacles.add(new Book(260, baseline-15));
			obstacles.add(new Book(260, baseline-30));
			end = new Point(1000, 690);
			
			platforms = new ArrayList<Platform>();
			for (int x = 0; x < (int)end.getX(); x += 300)
				platforms.add(new Platform(x, baseline, 300, 31));
			platforms.add(new Platform(0, baseline - 400, 320, 31));
			platforms.add(new Platform(300, baseline - 280, 100, 31));
			platforms.add(new Platform(415, baseline - 375, 200, 31));
			platforms.add(new Platform(600, baseline - 250, 120, 31));
			platforms.add(new Platform(500, baseline - 120, 100, 31));
			
		}
		else if (level == 4)
		{
			start = new Point(0, 0);
			end = new Point(1000, 690);
			obstacles = new ArrayList<Obstacle>();
			obstacles.add(new Book(180, baseline-30));
			obstacles.add(new Book(210, baseline-30));
			obstacles.add(new Book(890, baseline-30));
			obstacles.add(new Book(700, baseline-410));
			obstacles.add(new Book(725, baseline-410));
			
			obstacles.add(new ToxicAP(400, baseline-308));
			
			obstacles.add(new Seagull(550, baseline-50));
			obstacles.add(new Seagull(660, baseline-50));
			obstacles.add(new Seagull(200, baseline-350));
			obstacles.add(new Seagull(800, baseline-290));
			
			platforms = new ArrayList<Platform>();
			for (int x = 0; x < (int)end.getX(); x += 300)
				platforms.add(new Platform(x, baseline, 300, 31));
			platforms.add(new Platform(0, baseline - 400, 180, 31));
			platforms.add(new Platform(275, baseline - 300, 250, 31));
			platforms.add(new Platform(590, baseline - 375, 200, 31));
			platforms.add(new Platform(765, baseline - 250, 100, 31));
		}
	}
	
	//Returns an ArrayList of obstacles in current level
	public ArrayList<Obstacle> getObstacles()
	{
		return obstacles;
	}
	
	//Returns an ArrayList of platforms in current level
	public ArrayList<Platform> getPlatforms()
	{
		return platforms;
	}
	
	//Returns the start point of current level
	public Point getStart()
	{
		return start;
	}
	
	//Returns the end point of current level
	public Point getEnd()
	{
		return end;
	}
		
	//Returns level number
	public int getLevelNum()
	{
		return levelNum;
	}
	
	
	//checks if location is within bounds of the screen
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
	public Point checkNextLoc(Point loc, Controller ctrl, int levelNum, int xState, int yState)
	{
		if (isValid(loc))
		{
			Point point = loc;
			Rectangle next = new Rectangle((int)loc.getX()+10, (int)loc.getY(), Controller.pWidth-20, Controller.pHeight);
			for (Obstacle obst : obstacles)
			{
				if (obst != null && obst.intersects(next))
					return start;
			}
			for (Platform plat : platforms)
			{
				if (plat != null && plat.intersects(next))
				{
					if (xState == Player.LEFT || xState == Player.RIGHT)
						point = new Point ((int)ctrl.getPlayer().getLocation().getX(), (int)point.getY());
					return point;
				
				}
			}
			if (yState == Player.STILL)
			{
				if (ctrl.getPlayer().isOnPlat() == true)
					ctrl.getPlayer().setPlatState(false);
			}
			return point;
		}
		return null;
	}
}
