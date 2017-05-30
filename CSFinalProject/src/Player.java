import java.awt.Rectangle;

public class Player extends Rectangle
{
	//Job: stores lives, gets location to move to next, and updates its record of its position.

	//private int lives; 
	private int xState;
	private int yState;
	private boolean onPlat;
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
	
	public boolean isOnPlat()
	{
		return onPlat;
	}
	
	public void setPlatState(boolean state)
	{
		onPlat = state;
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
