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
	private Timer jumpTimer;
	private int levelNum;
	private Level level;
	private int counter;

	public Controller() 
	{
		timer = new Timer(250, this);
		jumpTimer = new Timer(250, this);
		player = new Player();
		levelNum++;
		level = new Level(levelNum);
		counter = 0;
	}

	// constructs Grid with correct set of obstacles and platforms, and update’s
	// GUI’s display
	public void initialize() 
	{
		gui = new GUI();
		area = new Grid(level.getObstacles(), level.getPlatforms(), level.getEnd());
	}

	//checks if either right or left arrow key is pressed
	public void keyPressed(KeyEvent event) 
	{
		if (event.getKeyCode() == KeyEvent.VK_RIGHT) 
			player.setState(Player.RIGHT);
		else if (event.getKeyCode() == KeyEvent.VK_LEFT)
			player.setState(Player.LEFT);
		timer.start();
		timer.addActionListener(this);
	}
	
	//checks if user hit the up arrow key. If yes, starts timer and sets player's jumping state to true
	public void keyTyped(KeyEvent event)
	{
		if (event.getKeyCode() == KeyEvent.VK_UP) 
		{
			jumpTimer.start();
			jumpTimer.addActionListener(this);
			player.setYState(true);
		}
	}

	//Stops the player's movement and resets player's state to still
	public void keyReleased(KeyEvent event) 
	{
		timer.stop();
		player.setState(Player.STILL);
	}

	//Moves player to next location or beginning of game (if player hit an obstacle). Calls GUI to update display of position
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

	//returns new location player will move to when jumping
	public Point processJump(Point moveLoc)
	{
		if (player != null)
		{
			if (counter > 4)
			{
				jumpTimer.stop();
				player.setYState(false);
				counter = 0;
				return moveLoc;
			}
			else
			{
				counter++;
				if (counter == 1)
					return new Point((int)moveLoc.getX(), (int)(moveLoc.getY() + 20));
				else if (counter == 2)
					return new Point((int)moveLoc.getX(), (int)(moveLoc.getY() + 10));
				else if (counter == 3)
					return new Point((int)moveLoc.getX(), (int)(moveLoc.getY() - 10));
				else
					return new Point((int)moveLoc.getX(), (int)(moveLoc.getY() - 20));
			}
		}
		return null;
	}

	//moves player back to beginning of level and decrements lives
	public void resetLevel() 
	{
		player.setLocation(Level.start);
		//player.setLives(player.getLives() - 1);
		//gui.updateLifeImg(player.getLives());
	}

	//when timer goes off, calls player move method
	public void actionPerformed(ActionEvent e)
	{
		Point moveLoc = player.getLocation();
		if (e.getSource() == timer)
		{
			if (player.getState() == Player.LEFT)
				moveLoc = new Point((int)moveLoc.getX()-10, (int)moveLoc.getY());
			else if (player.getState() == Player.RIGHT)
				moveLoc = new Point((int)moveLoc.getX()+10, (int)moveLoc.getY());
		}
		else if (e.getSource() == jumpTimer)
		{
			if (player.getYState() == true)
				moveLoc = processJump(moveLoc);
		}
		processMove(area.checkNextLoc(moveLoc, levelNum, player.getState(), player.getYState()));
	}
}
