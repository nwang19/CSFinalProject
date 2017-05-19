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
	private int state;
	private boolean yState;
	private Image person;
	public static final int STILL = 0;
	public static final int LEFT = 1;
	public static final int RIGHT = 2;
	
	public Player()
	{
		//lives = 3;
		state = 0;
		yState = false;
	}
	
	public Player(int x, int y, int width, int height)
	{
		super(x, y, width, height);
		//lives = 3;
		PictLoader test = new PictLoader();// when using IDE
		//JarPictLoader test = new JarPictLoader();// when using JAR file
		ImageIcon personIcon = test.getperson();
		person = personIcon.getImage();
		state = 0;
		yState = false;
	}

	/*public int getLives()
	{
		return lives;
	}
	
	public void setLives(int numLives)
	{
		lives = numLives;
	}*/

	public void setState(int status)
	{
		state = status;
	}
	
	public int getState()
	{
		return state;
	}
	
	public boolean getYState()
	{
		return yState;
	}
	
	public void setYState(boolean y)
	{
		yState = y;
	}

}
