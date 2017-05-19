import java.awt.Point;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.Timer;

public class Controller implements KeyListener, ActionListener 
{
	private GUI gui;
	private Player player;
	private Timer timer;
	private Timer jumpTimer;
	private int levelNum;
	private Level level;
	private int jumpMoveCounter;

	public Controller() 
	{
		timer = new Timer(250, this);
		jumpTimer = new Timer(250, this);
		int width = 10; int height = 10;
		player = new Player((int)Level.start.getX(), (int)Level.start.getY(), width, height);
		level.storePlayerSize(player);
		levelNum++;
		level = new Level(levelNum);
		jumpMoveCounter = 0;
		gui = new GUI();
	}

	// constructs Grid with correct set of obstacles and platforms, and updates
	// GUI's display
	public void initialize() 
	{
		level = new Level(levelNum);
		player.setLocation(Level.start);
		gui.display();
	}
	
	public void nextLevel()
	{
		levelNum++;
		initialize();
	}

	//checks if either right or left arrow key is pressed
	public void keyPressed(KeyEvent event) 
	{
		if (event.getKeyCode() == KeyEvent.VK_RIGHT) 
			player.setXState(Player.RIGHT);
		else if (event.getKeyCode() == KeyEvent.VK_LEFT)
			player.setXState(Player.LEFT);
		timer.start();
		timer.addActionListener(this);
	}
	
	//checks if user hit the up arrow key. If yes, starts timer and sets player's jumping state to true
	public void keyTyped(KeyEvent event)
	{
		if (player.getYState() == Player.STILL && event.getKeyCode() == KeyEvent.VK_UP) 
		{
			jumpTimer.start();
			jumpTimer.addActionListener(this);
			player.setYState(Player.UP);
		}
	}

	//Stops the player's movement and resets player's state to still
	public void keyReleased(KeyEvent event) 
	{
		timer.stop();
		player.setXState(Player.STILL);
	}

	//Moves player to next location or beginning of game (if player hit an obstacle). Calls GUI to update display of position
	public void processMove(Point nextLoc) 
	{
		if(nextLoc != null)
		{
			if(nextLoc.equals(Level.start))
			{
				//if (player.getLives() > 1)
					initialize();
				/*else
				{
					//game over
				}*/
			}
			else
				player.setLocation(nextLoc);
		 gui.updateScreen(player);
		 if (player.contains(level.getEnd()))
				 nextLevel();
		}
	}

	//returns new location player will move to when jumping
	public Point processJump(Point moveLoc)
	{
		if (player != null)
		{
			if (jumpMoveCounter > 4)
			{
				jumpTimer.stop();
				player.setYState(Player.STILL);
				jumpMoveCounter = 0;
				return moveLoc;
			}
			else
			{
				jumpMoveCounter++;
				if (jumpMoveCounter == 1)
					return new Point((int)moveLoc.getX(), (int)(moveLoc.getY() + 20));
				else if (jumpMoveCounter == 2)
					return new Point((int)moveLoc.getX(), (int)(moveLoc.getY() + 10));
				else if (jumpMoveCounter == 3)
				{
					player.setYState(Player.DOWN);
					return new Point((int)moveLoc.getX(), (int)(moveLoc.getY() - 10));
				}
				else
					return new Point((int)moveLoc.getX(), (int)(moveLoc.getY() - 20));
			}
		}
		return null;
	}

	//when timer goes off, calls player move method
	public void actionPerformed(ActionEvent e)
	{
		Point moveLoc = player.getLocation();
		if (e.getSource() == timer)
		{
			if (player.getXState() == Player.LEFT)
				moveLoc = new Point((int)moveLoc.getX()-10, (int)moveLoc.getY());
			else if (player.getXState() == Player.RIGHT)
				moveLoc = new Point((int)moveLoc.getX()+10, (int)moveLoc.getY());
		}
		else if (e.getSource() == jumpTimer)
		{
			if (player.getYState() != 0)
				moveLoc = processJump(moveLoc);
		}
		processMove(level.checkNextLoc(moveLoc, levelNum, player.getXState(), player.getYState()));
	}
}
