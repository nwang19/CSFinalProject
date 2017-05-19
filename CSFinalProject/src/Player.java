import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;   

public class Player extends Rectangle
{
	//Job: stores lives, gets location to move to next, and updates its record of its position.

	//private int lives; 
	private int xState;
	private int yState;
	private Image person;
	public static final int STILL = 0;
	public static final int LEFT = 1;
	public static final int RIGHT = 2;
	public static final int UP = 3;
	public static final int DOWN = 4;
	
	public Player()
	{
		//lives = 3;
		xState = STILL;
		yState = STILL;
	}
	
	public Player(int x, int y, int width, int height)
	{
		super(x, y, width, height);
		//lives = 3;
		PictLoader test = new PictLoader();// when using IDE
		//JarPictLoader test = new JarPictLoader();// when using JAR file
		ImageIcon personIcon = test.getperson();
		person = personIcon.getImage();
		xState = STILL;
		yState = STILL;
	}

	public void setXState(int status)
	{
		xState = status;
	}
	
	public int getXState()
	{
		return xState;
	}
	
	public int getYState()
	{
		return yState;
	}
	
	public void setYState(int y)
	{
		yState = y;
	}
	
	/*public int getLives()
	{
		return lives;
	}
	
	public void setLives(int numLives)
	{
		lives = numLives;
	}*/

}
