import java.awt.Point;
//import java.awt.event.*;

import javax.swing.Timer;

public class Controller 
{
	private GUI gui;
	private Player player;
	private int levelNum;
	private Level level;
	private int jumpMoveCounter;
	static int pWidth = 100, pHeight = 200;
	
	public static void main(String[] args)
    {
        Controller c = new Controller();
        GUI gui = new GUI(c);
        c.setGui(gui);
        c.initialize();
    }
	
	public Controller() 
	{
		player = new Player((int)Level.start.getX(), (int)Level.start.getY(), pWidth, pHeight);
		levelNum = 1; //++
		level = new Level(levelNum);
		jumpMoveCounter = 0;
	}
	
	public void setGui(GUI g)
	{
		gui = g;
	}

	
	public Level getLevel()
	{
		return level;
	}
	
	
	public Player getPlayer()
	{
		return player;
	}
	
	
	public int getLevNum()
	{
		return levelNum;
	}
	
	
	// constructs Grid with correct set of obstacles and platforms, and updates
	// GUI's display
	public void initialize() 
	{
		//timer.stop()
		level = new Level(levelNum);
		player.setLocation(Level.start);
		player.setXState(Player.STILL);
		player.setYState(Player.STILL);
		gui.display();
	}
	
	public void nextLevel()
	{
		levelNum++;
		initialize();
	}

	//Moves player to next location or beginning of game (if player hit an obstacle). Calls GUI to update display of position
	public void processMove(Point nextLoc) 
	{
		
		if(nextLoc != null)
		{
			if(nextLoc.equals(Level.start))
			{
				//if (player.getLives() > 1)
				System.out.println(nextLoc);
				initialize();
				/*else
				{
					//game over
				}*/
			}
			else
			{
				player.setLocation(nextLoc);
			}
			gui.updateScreen(player);
			if (player.contains(level.getEnd()))
				 nextLevel();
		}
	}

	//returns new location player will move to when jumping
	public Point processJump(Point moveLoc, Timer jTimer)
	{
		if (player != null && player.getYState() != Player.STILL && jumpMoveCounter <= 4)
		{
			jumpMoveCounter++;
			if (jumpMoveCounter == 1)
				return new Point((int)moveLoc.getX(), (int)(moveLoc.getY() - 20));
			else if (jumpMoveCounter == 2)
				return new Point((int)moveLoc.getX(), (int)(moveLoc.getY() - 10));
			else if (jumpMoveCounter == 3)
			{
				player.setYState(Player.DOWN);
				return new Point((int)moveLoc.getX(), (int)(moveLoc.getY() + 10));
			}
			else
			{
				/*Point platLoc = new Point((int)player.getX() + pWidth + 1, (int)player.getY() + pHeight + 1);
				if (level.getPlatform(platLoc) != null)
				{*/	
					jumpMoveCounter = 0;
					player.setYState(Player.STILL);
					return new Point((int)moveLoc.getX(), (int)(moveLoc.getY() + 20));
					//return moveLoc;
				/*}
				else
				{
					System.out.println(new Point((int)moveLoc.getX(), (int)(moveLoc.getY() - 20)));
					return new Point((int)moveLoc.getX(), (int)(moveLoc.getY() - 20));
				}*/
			}
		}
		return null;
	}
}
