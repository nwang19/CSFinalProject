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
	private int yPos;
	private int yVelocity;
	static int pWidth = 45, pHeight = 100;
	
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
		yPos = 0;
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
		jumpMoveCounter = 0;
	}
	
	public void nextLevel()
	{
		levelNum++;
		if (levelNum <= 4)
			initialize();
		else
		{
			//display end screen
		}
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
			{
				player.setLocation(nextLoc);
			}
			gui.updateScreen(player);
			if (player.contains(level.getEnd()))
			{
				 nextLevel();
				 System.out.println("next level");
			}
		}
	}

	//returns new location player will move to when jumping
	public Point processJump(Point moveLoc)
	{
		//should edit bc choppy af but idc at this point
		if (player != null && player.getYState() != Player.STILL)
		{
			//jumpMoveCounter++;
			if (player.getYState() == Player.UP)
			{
				yPos = (int)moveLoc.getY();
				yPos -= 3;
				if (yPos < 450)
					player.setYState(Player.DOWN);
			}
			else if (player.getYState() == Player.DOWN)
			{
				yPos += 3;
				if (yPos > 600)
				{
					player.setYState(Player.STILL);
					yPos = 600;
				}
			}
			
			System.out.println(new Point((int)moveLoc.getX(), yPos));
			return new Point((int)moveLoc.getX(), yPos);
			
			/*if (jumpMoveCounter == 0)
			yInit = (int)moveLoc.getY();
			jumpMoveCounter++;
			int yPos = yInit;
			if (player.getYState() == Player.UP)
			{
				yPos = yInit + (int)(7*jumpMoveCounter + 5*Math.pow(jumpMoveCounter, 2));
				if (moveLoc.getY() <= yInit - 50)
					player.setYState(Player.DOWN);
			}
			else if (player.getYState() == Player.DOWN)
			yPos = (yInit - 50) + (int)(7*jumpMoveCounter + 5*Math.pow(jumpMoveCounter, 2));
			if (yPos > yInit)
			{
				player.setYState(Player.STILL);
				jumpMoveCounter = 0;
				yPos = yInit;
			}
			System.out.println(new Point ((int)moveLoc.getX(), yPos));
			return new Point((int)moveLoc.getX(), yPos);*/
			
			
			/*
			int yPos = (int)moveLoc.getY();
			Point move = moveLoc;
			if (jumpMoveCounter == 50)
			{
				player.setYState(Player.DOWN);
				jumpMoveCounter = 0;
			}
			if (player.getYState() == Player.UP)
				move = new Point((int)move.getX(), (int)(yPos - .05*jumpMoveCounter));
			else
				move = new Point((int)move.getX(), (int)(yPos + .05*jumpMoveCounter));
			System.out.println(move);
			if ((int)move.getY() >= 600)
			{
				player.setYState(Player.STILL);
				jumpMoveCounter = 0;
			}
			return move;*/
			
			
				
			/*if (jumpMoveCounter == 1)
				return new Point((int)moveLoc.getX(), (int)(moveLoc.getY() - 25));
			else if (jumpMoveCounter == 2)
				return new Point((int)moveLoc.getX(), (int)(moveLoc.getY() - 10));
			else if (jumpMoveCounter == 3)
			{
				//player.setYState(Player.DOWN);
				return new Point((int)moveLoc.getX(), (int)(moveLoc.getY() - 5));
			}
			else if (jumpMoveCounter == 4)
			{
				/*Point platLoc = new Point((int)player.getX() + pWidth + 1, (int)player.getY() + pHeight + 1);
				if (level.getPlatform(platLoc) != null)
				{*/
				//	return new Point((int)moveLoc.getX(), (int)(moveLoc.getY() + 5));
					
					//return moveLoc;
				/*}
				else
				{
					System.out.println(new Point((int)moveLoc.getX(), (int)(moveLoc.getY() - 20)));
					return new Point((int)moveLoc.getX(), (int)(moveLoc.getY() - 20));
				}
			}
			else if (jumpMoveCounter == 5)
				return new Point((int)moveLoc.getX(), (int)(moveLoc.getY() + 10));
			else if (jumpMoveCounter == 6)
			{
				player.setYState(Player.STILL);
				jumpMoveCounter = 0;
				return new Point((int)moveLoc.getX(), (int)(moveLoc.getY() + 25));
			}*/
		}
		return null;
	}
}
