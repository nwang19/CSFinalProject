import java.awt.Point;
import java.util.ArrayList;

public class Level {
	static Point start = new Point(10, 10);
	private Point end;
	private ArrayList<Obstacle> obstacles;
	private ArrayList<Platform> platforms;
	private int levelNum;
	
	public Level(int level)
	{
		if (level ==1)
		{
			levelNum = level;
			end = new Point(10,10);
			obstacles = new ArrayList<Obstacle>();
			//fill ArrayList with obstacles at locations
			platforms = new ArrayList<Platform>();
			//fill ArrayList with platforms
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
	
	public Point getEnd(=)
	{
		return end;
	}
	
	public int getLevel()
	{
		return levelNum;
	}
}
