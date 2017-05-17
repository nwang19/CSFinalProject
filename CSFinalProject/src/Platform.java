import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Rectangle;

public class Platform extends Rectangle
{

	private Image blockImg;
	
	public Platform()
	{
		super();
	}
	
	public Platform(int x, int y, int width, int height)
	{
		super(x, y, width, height);
	}
	
	public Image getImg()
	{
		return blockImg;
	}
	
	public void setImg(String imgName)
	{
		ImageIcon blockIcon = new ImageIcon(imgName);
		blockImg = blockIcon.getImage();
		//int load = fishIcon.getImageLoadStatus(); System.out.println("fish load " + load);
		//System.out.println("fish load " + load);
	}
}
