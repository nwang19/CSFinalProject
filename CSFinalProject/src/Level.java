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
		levelNum = level;
		screenWidth = 1000;
		screenHeight = 750;
		baseline = 700;
		if (level == 1)
		{
			end = new Point(1000, 690);
			obstacles = new ArrayList<Obstacle>();
			for (int x = 150; x <= 350; x += 150)
			//	obstacles.add(new Seagull(x, baseline - Obstacle.height - 65));
			obstacles.add(new ToxicAP(500, baseline-7));
			obstacles.add(new ToxicAP(500-Obstacle.width, baseline-7));
			obstacles.add(new ToxicAP(500-(2*Obstacle.width), baseline-7));
			obstacles.add(new ToxicAP(700, baseline-7));
			obstacles.add(new ToxicAP(700-Obstacle.width, baseline-7));
			obstacles.add(new ToxicAP(700-(2*Obstacle.width), baseline-7));
			obstacles.add(new Book(900, baseline-15));
			platforms = new ArrayList<Platform>();
			for (int x = 0; x < (int)end.getX(); x += Platform.width)
				platforms.add(new Platform(x, baseline, Obstacle.width, Obstacle.height));
			//platforms.add(new Platform(70, baseline - 50, 300, 20));
			platforms.add(new MovingObject(50, baseline - 50, 20, 300, Player.RIGHT, 25, 300));
		}
		else if (level == 2)
		{
			end = new Point(1000, 690);
			obstacles = new ArrayList<Obstacle>();
			obstacles.add(new ToxicAP(200, baseline-7));
			obstacles.add(new ToxicAP(200-Obstacle.width, baseline-7));
			obstacles.add(new ToxicAP(200-(2*Obstacle.width), baseline-7));
			obstacles.add(new Seagull(300, baseline-80));
			obstacles.add(new Seagull(500, baseline-80));
			obstacles.add(new Book(700, baseline-30));
			obstacles.add(new Seagull(900, baseline-60)); 
			
			platforms = new ArrayList<Platform>();
			for (int x = 0; x < (int)end.getX(); x += Platform.width)
				platforms.add(new Platform(x, baseline, 10, 10));
		}
		else if (level == 3)
		{
			obstacles = new ArrayList<Obstacle>();
			obstacles.add(new ToxicAP(300, baseline-7));
			obstacles.add(new ToxicAP(300-Obstacle.width, baseline-7));
			obstacles.add(new ToxicAP(300-(2*Obstacle.width), baseline-7));
			obstacles.add(new ToxicAP(500, baseline-7));
			obstacles.add(new ToxicAP(500-Obstacle.width, baseline-7));
			obstacles.add(new ToxicAP(500-(2*Obstacle.width), baseline-7));
			obstacles.add(new Seagull(600, baseline-90));
			obstacles.add(new Seagull(700, baseline-90));
			obstacles.add(new Book(650, baseline-20));
			obstacles.add(new Book(800, baseline-15));
			obstacles.add(new Book(850, baseline-20));
			end = new Point(1000, 690);
			
			platforms = new ArrayList<Platform>();
			for (int x = 0; x < (int)end.getX(); x += Platform.width)
				platforms.add(new Platform(x, baseline, 10, 10));
			
		}
		else if (level == 4)
		{
			end = new Point(1000, 690);
			obstacles = new ArrayList<Obstacle>();
			obstacles.add(new Book(200, baseline-30));
			obstacles.add(new Book(350, baseline-50));
			obstacles.add(new Book(350, baseline-30));
			obstacles.add(new Seagull(450, baseline-50));
			obstacles.add(new Seagull(550, baseline-50));
			obstacles.add(new Seagull(650, baseline-80));
			obstacles.add(new Seagull(750, baseline-90));
			obstacles.add(new Seagull(860, baseline-50));
			
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
	public Point checkNextLoc(Point loc, Controller ctrl, int levelNum, int xState, int yState)
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
