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
			obstacles.add(new Seagull(415, baseline - 245));
			obstacles.add(new Book(275, baseline-230));
			obstacles.add(new Book(800, baseline-15));
			obstacles.add(new ToxicAP(200, baseline-408));
			obstacles.add(new ToxicAP(200-Obstacle.WIDTH, baseline-408));
			platforms = new ArrayList<Platform>();
			for (int x = 0; x < (int)end.getX(); x += 300)
				platforms.add(new Platform(x, baseline, 300, 31));
			platforms.add(new Platform(0, baseline - 400, 600, 31));
			platforms.add(new Platform(200, baseline - 200, 400, 31));
			platforms.add(new Platform(550, baseline - 280, 50, 80));
			platforms.add(new MovingObject(640, baseline - 280, 150, 31, Player.LEFT, 590, 825));
			platforms.add(new Platform(850, baseline - 280, 1000, 31));
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
				obstacles.add(new ToxicAP(1000 - (i *Obstacle.WIDTH), baseline-240));
			}
			
			for(int i = 1; i < 35; i++)
			{
			   obstacles.add(new ToxicAP(1000 - (i *Obstacle.WIDTH), baseline+50));
			}
				
			obstacles.add(new Book(200, baseline-260));
			obstacles.add(new Book(320, baseline-260));
			obstacles.add(new Seagull(400, baseline-65));
			obstacles.add(new Seagull(700, baseline-65));
			obstacles.add(new Seagull(760, baseline-480)); 
			
			platforms = new ArrayList<Platform>();
			platforms.add(new Platform(0, baseline - 450, 150, 31));
			platforms.add(new Platform(120, baseline - 419, 300, 31));
			platforms.add(new Platform(460, baseline - 470, 30, 31));
			platforms.add(new Platform(500, baseline - 470, 30, 31));
			platforms.add(new Platform(580, baseline - 470, 30, 31));
			platforms.add(new Platform(700, baseline - 419, 90, 31));
			platforms.add(new MovingObject(705, baseline - 300, 150, 31, Player.LEFT, 420, 1000));
			platforms.add(new Platform(120, baseline - 240, 300, 31));
			platforms.add(new Platform(0, baseline-30, 80, 31));
			platforms.add(new MovingObject(100, baseline-30, 200, 31, Player.LEFT, 80, 840));
			platforms.add(new Platform(840, baseline-30, 160, 31));
		}
		else if (level == 3)
		{
			start = new Point(0, 0);
			obstacles = new ArrayList<Obstacle>();
			

			obstacles.add(new Book(300, baseline-70));	
			obstacles.add(new Book(505, baseline-70));
			obstacles.add(new Book(620, baseline-70));
			
			obstacles.add(new Seagull(600, baseline-435));			
			obstacles.add(new Seagull(700, baseline-435));
			for(int i = 1; i < 200; i++)
			{
				obstacles.add(new ToxicAP(900 - (i *Obstacle.WIDTH), baseline-350));
			}
			for(int i = 1; i < 35; i++)
			{
			   obstacles.add(new ToxicAP(1000 - (i *Obstacle.WIDTH), baseline+30));
			}
			end = new Point(0, 690);
			
			platforms = new ArrayList<Platform>();
			platforms.add(new Platform(0, baseline - 450, 320, 31));
			platforms.add(new Platform(290, baseline - 420, 31, 100));
			platforms.add(new Platform(900, baseline - 350, 50, 30));
			
			platforms.add(new Platform(400, baseline - 400, 100, 31));
			platforms.add(new Platform(530, baseline - 390, 150, 31));
			platforms.add(new Platform(760, baseline - 425, 120, 31));
			
			platforms.add(new Platform(0, baseline - 30, 80, 31));
			platforms.add(new MovingObject(180, baseline-30, 200, 31, Player.LEFT, 80, 490));
			platforms.add(new MovingObject(550, baseline-30, 200, 31, Player.LEFT, 520, 800));
			platforms.add(new Platform(800, baseline - 30, 200, 31));
			
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
			obstacles.add(new Seagull(900, baseline-290));
			
			platforms = new ArrayList<Platform>();
			for (int x = 0; x < (int)end.getX(); x += 300)
				platforms.add(new Platform(x, baseline, 300, 31));
			platforms.add(new Platform(0, baseline - 400, 180, 31));
			platforms.add(new Platform(275, baseline - 300, 250, 31));
			platforms.add(new Platform(590, baseline - 375, 200, 31));
			platforms.add(new MovingObject(765, baseline-250, 125, 31, Player.LEFT, 700, 860));
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
