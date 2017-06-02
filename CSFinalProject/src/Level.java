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
			/*obstacles.add(new Seagull(415, baseline - 245));
			obstacles.add(new Book(275, baseline-230));
			obstacles.add(new Book(800, baseline-15));
			obstacles.add(new ToxicAP(200, baseline-408));
			obstacles.add(new ToxicAP(200-Obstacle.WIDTH, baseline-408));*/
			platforms = new ArrayList<Platform>();
			for (int x = 0; x < (int)end.getX(); x += 300)
				platforms.add(new Platform(x, baseline, 300, 31));
			/*platforms.add(new Platform(0, baseline - 400, 600, 31));
			platforms.add(new Platform(200, baseline - 200, 400, 31));
			platforms.add(new Platform(550, baseline - 280, 50, 80));
			platforms.add(new MovingObject(640, baseline - 280, 150, 31, Player.LEFT, 590, 825));
			platforms.add(new Platform(850, baseline - 280, 1000, 31));*/
		}
		else if (level == 2)
		{
			start = new Point(0, 0);
			end = new Point(1000, 690);
			obstacles = new ArrayList<Obstacle>();
			for(int i = 1; i < 8; i++)
			{
				obstacles.add(new ToxicAP(625 -(i *Obstacle.WIDTH), baseline-419));
			}
			for(int i = 1; i < 20; i++)
			{
				obstacles.add(new ToxicAP(1000 - (i *Obstacle.WIDTH), baseline-190));
			}
				
			obstacles.add(new Book(200, baseline-200));
			obstacles.add(new Book(350, baseline-200));
			obstacles.add(new Seagull(150, baseline-50));
			obstacles.add(new Seagull(445, baseline-50));
			obstacles.add(new Seagull(700, baseline-50));
			obstacles.add(new Seagull(760, baseline-480)); 
			
			platforms = new ArrayList<Platform>();
			platforms.add(new Platform(0, baseline - 450, 150, 31));
			platforms.add(new Platform(120, baseline - 419, 300, 31));
			platforms.add(new Platform(460, baseline - 470, 30, 31));
			platforms.add(new Platform(500, baseline - 470, 30, 31));
			platforms.add(new Platform(580, baseline - 470, 30, 31));
			platforms.add(new Platform(700, baseline - 419, 90, 31));
			platforms.add(new MovingObject(705, baseline - 270, 150, 31, Player.LEFT, 420, 1000));
			platforms.add(new Platform(120, baseline - 190, 300, 31));
			platforms.add(new Platform(0, baseline, 80, 31));
			platforms.add(new MovingObject(100, baseline, 200, 31, Player.LEFT, 80, 840));
			platforms.add(new Platform(840, baseline, 160, 31));
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
