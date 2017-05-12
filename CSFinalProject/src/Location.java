
public class Location {
	//Role: stores elements’ positions in an easily checkable way
	
	private int xPos;
	private int yPos;
	
	public Location(int x, int y)
	{
		xPos = x;
		yPos = y;
	}
	
	public int getXPos()
	{
		return xPos;
	}
	
	public int getYPos()
	{
		return yPos;
	}
	
	public void setXPos(int x)
	{
		xPos = x;
	}
	
	public void setYPos(int y)
	{
		yPos = y;
	}

}
