import java.awt.Point;
import java.awt.Rectangle;

public class Player extends Rectangle
{
	//Job: stores lives, gets location to move to next, and updates its record of its position.

	private int lives; 
	private Point loc;
	private int state;
	public static final int STILL = 0;
	public static final int LEFT = 1;
	public static final int RIGHT = 2;
	private boolean yState;
	
	public Player()
	{
		lives = 3;
		state = 0;
		yState = false;
	}
	
	public Player(int x, int y, int width, int height)
	{
		super(x, y, width, height);
		lives = 3;
		state = 0;
		yState = false;
	}

	public int getLives()
	{
		return lives;
	}
	
	public void setLives(int numLives)
	{
		lives = numLives;
	}

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
