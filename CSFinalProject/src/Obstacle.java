import java.awt.Image;
import java.awt.Point; 

public class Obstacle 
{
	private Point myPoint;
	private Image myImg;
	
	public Obstacle(Point p, Image img)
	{
		myPoint = p;
		myImg = img;
	}
	
	public Point getPoint()
	{
		return myPoint;
	}
	
	public void setPoint(Point p)
	{
		myPoint = p;
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
