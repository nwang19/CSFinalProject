import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.Timer;

public class Controller implements KeyListener, ActionListener 
{
	private Grid area;
	private GUI gui;
	private ArrayList<Obstacle> obstacles;
	private ArrayList<Platform> platforms;
	private Player player;
	private Boolean upKey, rightKey, leftKey;
	private Timer timer;
	private Point end;

	public Controller() 
	{
		upKey = false;
		rightKey = false;
		leftKey = false;
		timer = new Timer(250, this);
		obstacles = new ArrayList<Obstacle>();
		platforms = new ArrayList<Platform>();
		player = new Player();
		end = new Point(50, 10);
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
		
		if (event.getKeyCode() == KeyEvent.VK_RIGHT) 
		{
			rightKey = true;
			processRight();
		} 
		else if (event.getKeyCode() == KeyEvent.VK_LEFT) 
		{
			leftKey = true;
			processLeft();
		}
		timer.start();

	}
	
	public void keyTyped(KeyEvent event)
	{
		if (event.getKeyCode() == KeyEvent.VK_UP) 
		{
			upKey = true;
			processUp();
		}
	}

	public void keyReleased(KeyEvent event) 
	{
		timer.stop();
		//do stuff

	}

	public void processLeft() 
	{
		Point loc = player.getLocation();
		Point left = new Point((int)loc.getX()-1, (int)loc.getY());
		Point nextLoc = area.checkNextLoc(left);
		if(nextLoc != null)
		{
			if(nextLoc.getX() == 0 && nextLoc.getY() == 0)
			{
				resetPos();
			}
			else
			{
				player.setLocation(left);
			}
		 gui.updateScreen(player, obstacles, platforms);

		}
	}

	public void processRight() 
	{
		Point loc = player.getLocation();
		Point right = new Point((int)loc.getX()+1, (int)loc.getY());
		Point nextLoc = area.checkNextLoc(right);
		if(nextLoc != null)
		{
			if(nextLoc.getX() == 0 && nextLoc.getY() == 0)
			{
				resetPos();
			}
			else
			{
				player.setLocation(right);
			}
			gui.updateScreen(player, obstacles, platforms);
		}
	}

	public void processUp() 
	{
		Point loc = player.getLocation();
		Point up = new Point((int)loc.getX(), (int)loc.getY()+1);
		Point nextLoc = area.checkNextLoc(up);
		if(nextLoc != null)
		{
			if(nextLoc.getX() == 0 && nextLoc.getY() == 0)
			{
				resetPos();
			}
			else
			{
				player.setLocation(up);
			}
			gui.updateScreen(player, obstacles, platforms);
		}
	}

	// moves player to the beginning if dead
	public void resetPos() 
	{
		player.setLocation(new Point(0, 0));
		int num = player.getLives();
		player.setLives(num--);
		gui.updateLifeImg();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	


}
