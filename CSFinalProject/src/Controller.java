import java.awt.Point;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.Timer;

public class Controller implements KeyListener, ActionListener 
{
	private Grid area;
	private GUI gui;
	private Player player;
	private Timer timer;
	private int levelNum;
	private Level level;

	public Controller() 
	{
		timer = new Timer(250, this);
		player = new Player();
		levelNum++;
		level = new Level(levelNum);
	}

	// constructs Grid with correct set of obstacles and platforms, and update’s
	// GUI’s display
	public void initialize() 
	{
		// add stuff to obstacles and platforms
		gui = new GUI();
		area = new Grid(level.getObstacles(), level.getPlatforms(), level.getEnd());
	}

	public void keyPressed(KeyEvent event) 
	{
		if (event.getKeyCode() == KeyEvent.VK_RIGHT) 
			player.setState(Player.RIGHT);
		else if (event.getKeyCode() == KeyEvent.VK_LEFT)
			player.setState(Player.LEFT);
		timer.start();
		timer.addActionListener(this);
	}
	
	public void keyTyped(KeyEvent event)
	{
		if (event.getKeyCode() == KeyEvent.VK_UP) 
		{
			player.setState(Player.UP);
			processUp();
		}
	}

	public void keyReleased(KeyEvent event) 
	{
		timer.stop();
		player.setState(Player.STILL);
	}

	public void processMove(Point nextLoc) 
	{
		if(nextLoc != null)
		{
			if(nextLoc.equals(Level.start))
			{
				if (player.getLives() > 1)
					resetLevel();
				else
				{
					//game over
				}
			}
			else
				player.setLocation(left);
		 gui.updateScreen(player, obstacles, platforms);
		}
	}

	/*public void processRight() 
	{
		Point loc = player.getLocation();
		Point left = new Point((int)loc.getX()+10, (int)loc.getY());
		Point nextLoc = area.checkNextLoc(left, levelNum);
		if(nextLoc != null)
		{
			if(nextLoc.equals(Level.start))
			{
				if (player.getLives() > 1)
					resetLevel();
				else
				{
					//game over
				}
			}
			else
			{
				player.setLocation(left);
			}
		 gui.updateScreen(player, obstacles, platforms);

		}
	} */

	public void processJump(Point moveLoc)
	{
		if (counter > 4)
		{
			player.setYState(false);
			counter = 0;
		}
		//else... all new move locs accounting gravity for counter = 0-4 
	}

	//moves player back to beginning of level and decrements lives
	public void resetLevel() 
	{
		player.setLocation(Level.start);
		player.setLives(player.getLives() - 1);
		gui.updateLifeImg(player.getLives());
	}

	public void actionPerformed(ActionEvent e)
	{
		Point loc = player.getLocation();
		Point moveLoc = loc;
		if (player.getState() == Player.LEFT)
			moveLoc = new Point((int)loc.getX()-10, (int)loc.getY());
		else if (player.getState() == Player.RIGHT)
			moveLoc = new Point((int)loc.getX()+10, (int)loc.getY());
		if (player.getYstate() == true)
			moveLoc = processJump(moveLoc);
		processMove(area.checkNextLoc(moveLoc, levelNum, player.getState(), player.getYState()));
	}

	

}
