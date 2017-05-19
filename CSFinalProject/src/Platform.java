import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Rectangle;

public class Platform extends Rectangle
{

	private Image blockImg;
	static int width = 10;
	static int height =10;
	
	public Platform(int x, int y, int w, int h)
	{
		super(x, y, width, height);
	}
	
	public Image getImg()
	{
		return blockImg;
	}
	
	//how do we add images?? can be do something like this, or do we have to load it into the gui?
	public void setImg(String imgName)
	{
		ImageIcon blockIcon = new ImageIcon(imgName);
		blockImg = blockIcon.getImage();
		//int load = fishIcon.getImageLoadStatus(); System.out.println("fish load " + load);
		//System.out.println("fish load " + load);
	}
}
