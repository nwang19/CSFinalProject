import java.awt.Image;
import javax.swing.ImageIcon;


public class Platform {

	private Image blockImg;
	private Location myLoc;
	
	public Platform()
	{
		myLoc = new Location(0, 0);
	}
	
	public Platform(Location loc, String imgName)
	{
		myLoc = loc;
		setImg(imgName);
	}
	
	public Location getLoc()
	{
		return myLoc;
	}
	
	public void setLoc(Location loc)
	{
		myLoc = loc;
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
