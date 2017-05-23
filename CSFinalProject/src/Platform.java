import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Rectangle;

public class Platform extends Rectangle
{

	private Image blockImg;
	static int width = 300;
	static int height =200;
	
	public Platform(int x, int y, int w, int h)
	{
		super(x, y, width, height);
	}
}
