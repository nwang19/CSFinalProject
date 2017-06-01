import java.awt.Rectangle; 

public class Obstacle extends Rectangle
{
	static final int WIDTH = 30;
	static final int HEIGHT = 30;
	
	public Obstacle(int x, int y)
	{
		super(x, y, WIDTH, HEIGHT);
	}
	
}
