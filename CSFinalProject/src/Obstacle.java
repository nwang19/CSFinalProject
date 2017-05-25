import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle; 

public class Obstacle extends Rectangle
{
	private Point myPoint;
	static int width = 50;
	static int height = 50;
	
	public Obstacle(int x, int y)
	{
		super(x, y, width, height);
		myPoint = new Point(x, y-(height/2));
	}
	
	public Point getPoint()
	{
		return myPoint;
	}
	
	public void setPoint(Point p)
	{
		myPoint = p;
		setLocation((int)p.getX(), (int)p.getY() + (height/2));
	}
	
}
