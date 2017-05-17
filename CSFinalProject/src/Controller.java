import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.Timer;

public class Controller implements ActionListener, KeyListener 
{
	private Grid area;
	private GUI gui;
	private ArrayList<Obstacle> obstacles;
	private ArrayList<Platform> platforms;
	private Player player;
	private Boolean upKey, rightKey, leftKey;
	private Timer timer;
	private Location end;

	public Controller() 
	{
		upKey = false;
		rightKey = false;
		leftKey = false;
		timer = new Timer(250, this);
		obstacles = new ArrayList<Obstacle>();
		platforms = new ArrayList<Platform>();
		player = new Player();
		end = new Location(50, 10);
	}

	// constructs Grid with correct set of obstacles and platforms, and update’s
	// GUI’s display
	public void initialize() 
	{
		// add stuff to obstacles and platforms
		gui = new GUI();
		area = new Grid(obstacles, platforms, end);
	}

	public void keyPressed(KeyEvent event) 
	{
		if (event.getKeyCode() == KeyEvent.VK_UP) 
		{
			upKey = true;
			processUp();
		} 
		else if (event.getKeyCode() == KeyEvent.VK_RIGHT) 
		{
			rightKey = true;
			processRight()
		} 
		else if (event.getKeyCode() == KeyEvent.VK_LEFT) 
		{
			leftKey = true;
			processLeft();
		}
		timer.start();

	}

	public void keyReleased(KeyEvent event) 
	{
		// TODO Auto-generated method stub
		timer.stop();

	}

	public void processLeft() 
	{
		Location loc = player.getLocation();
		Location left = new Location(loc.getRow()-1, loc.getCol());
		Location nextLoc = area.checkNextLoc(left);
		if(nextLoc != null)
		{
			if(nextLoc.getRow() == 0 && nextLoc.getCol() == 0)
			{
				resetPos();
			}
			else
			{
				player.setLocation(left);
			}
		}
	}

	public void processRight() 
	{
		Location loc = player.getLocation();
		Location right = new Location(loc.getRow()+1, loc.getCol());
		Location nextLoc = area.checkNextLoc(right);
		if(nextLoc != null)
		{
			if(nextLoc.getRow() == 0 && nextLoc.getCol() == 0)
			{
				resetPos();
			}
			else
			{
				player.setLocation(right);
			}
		}
	}

	public void processUp() 
	{
		Location loc = player.getLocation();
		Location up = new Location(loc.getRow()-1, loc.getCol());
		Location nextLoc = area.checkNextLoc(up);
		if(nextLoc != null)
		{
			if(nextLoc.getRow() == 0 && nextLoc.getCol() == 0)
			{
				resetPos();
			}
			else
			{
				player.setLocation(up);
			}
		}
	}

	// moves player to the beginning if dead
	public void resetPos() 
	{
		player.setLocation(new Location(0, 0));
		int num = player.getLives()
		player.setLives(num--);
	}

}
