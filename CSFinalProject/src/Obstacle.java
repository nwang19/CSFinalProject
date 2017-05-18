import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle; 

public class Obstacle extends Rectangle
{
	private Point myPoint;
	private Image myImg;
	
	public Obstacle(int x, int y, int width, int height, Image img)
	{
		super(x, y, width, height);
		myPoint = new Point(x, y-(height/2));
		myImg = img;
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
	
	public Image getImg()
	{
		return myImg;
	}
	
	public void setImg(Image img)
	{
		myImg = img;
	}
	
}
