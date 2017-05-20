import java.awt.Image;

import javax.swing.ImageIcon;
import java.awt.Rectangle;
import java.net.URL;

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
		ClassLoader cldr = this.getClass().getClassLoader();	
		URL platform   = cldr.getResource("platform.png");	
		ImageIcon blockIcon = new ImageIcon(platform);
		blockImg = blockIcon.getImage();
		return blockImg;
	}
	
	//how do we add images?? can be do something like this, or do we have to load it into the gui?
	public void setImg(String imgName)
	{
		
		//int load = fishIcon.getImageLoadStatus(); System.out.println("fish load " + load);
		//System.out.println("fish load " + load);
	}
}
