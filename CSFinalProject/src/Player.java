import java.awt.Rectangle;

public class Player extends Rectangle
{
	//Job: gets location to move to next, and updates its record of player's position.

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
		xState = STILL;
		yState = STILL;
	}
	
	public Player(int x, int y, int width, int height)
	{
		super(x, y, width, height);
		xState = STILL;
		yState = STILL;
		onPlat = true;
	}
	
	//Changes direction of player's movement in the x-direction to Player.STILL, Player.LEFT, or Player.RIGHT
	public void setXState(int status)
	{
		xState = status;
	}
	
	//Returns x-direction of player's movement
	public int getXState()
	{
		return xState;
	}
	
	//Returns y-direction of player's movement
	public int getYState()
	{
		return yState;
	}
	
	//Changes direction of player's movement in the y-direction to Player.STILL, Player.UP, or Player.DOWN
	public void setYState(int y)
	{
		yState = y;
	}
	
	//Returns true if player is on a platform, otherwise returns false
	public boolean isOnPlat()
	{
		return onPlat;
	}
	
	//Updates record of whether or not the player is on a platform
	public void setPlatState(boolean state)
	{
		onPlat = state;
	}
}
