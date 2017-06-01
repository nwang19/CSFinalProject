import java.awt.Point;
import java.awt.Rectangle;

//import java.awt.event.*;
//import javax.swing.Timer;

public class Controller {
	private GUI gui;
	private Player player;
	private int levelNum;
	private Level level;
	private int jumpMoveCounter;
	private int yInit;
	private int yPos;
	static int pWidth = 45, pHeight = 75;

	public static void main(String[] args) {
		Controller c = new Controller();
		GUI gui = new GUI(c);
		c.setGui(gui);
		c.initialize();
	}

	public Controller() {
		levelNum = 1; // ++
		level = new Level(levelNum);
		player = new Player((int) level.getStart().getX(), (int) level.getStart().getY(),
				pWidth, pHeight);
		yPos = 0;
		yInit = Level.baseline;
		jumpMoveCounter = 0;
	}

	public void setGui(GUI g) {
		gui = g;
	}

	public Level getLevel() {
		return level;
	}

	public Player getPlayer() {
		return player;
	}

	public int getLevNum() {
		return levelNum;
	}

	public void setLevNum(int n) {
		levelNum = n;
	}

	// constructs Grid with correct set of obstacles and platforms, and updates
	// GUI's display
	public void initialize() {
		// timer.stop()
		level = new Level(levelNum);
		player.setLocation(level.getStart());
		player.setXState(Player.STILL);
		player.setYState(Player.DOWN);
		player.setPlatState(true);
		gui.display();
		yInit = Level.baseline;
		jumpMoveCounter = 0;
	}

	public void nextLevel() {
		levelNum++;
		if (levelNum <= 4)
			initialize();
		else {
			// display end screen
		}
	}

	// Moves player to next location or beginning of game (if player hit an
	// obstacle). Calls GUI to update display of position
	public void processMove(Point nextLoc) {

		if (nextLoc != null) {
			if (nextLoc.equals(level.getStart())) {
				// if (player.getLives() > 1)
				initialize();
				/*
				 * else { //game over }
				 */
			} else {
				player.setLocation(nextLoc);
			}
			if (player.contains(level.getEnd())) {
				nextLevel();
				System.out.println("next level");
			}
			gui.updateScreen(player);
		}
	}

	// returns new location player will move to when jumping
	// always going down though... kind of bad but it works so
	public Point processJump(Point moveLoc)
	{
		if (jumpMoveCounter == 0)
			yInit = (int) player.getLocation().getY();
		jumpMoveCounter++;
		if (player != null /* && player.getYState() != Player.STILL */)
		{
			if (player.getYState() == Player.UP)
			{
				yPos = (int) moveLoc.getY();
				yPos -= 3;
				for (Platform plat : level.getPlatforms())
				{
					if (plat.intersects(new Rectangle((int) player.getX(), (int) player.getY() - 1, pWidth, 1)))
					{
						player.setYState(Player.DOWN);
						yPos = (int)player.getLocation().getY() + 2;
					}
				}
				if (yPos < yInit - 150)
					player.setYState(Player.DOWN);
			}
			else if (player.getYState() == Player.DOWN)
			{
				
				yPos += 3;
				/*if (player.getY() >= Level.baseline - pHeight)
				{
					// player.setYState(Player.STILL);
					jumpMoveCounter = 0;
					player.setPlatState(true);
					yPos = Level.baseline - pHeight;
				}
				else*/
				{
					for (Platform plat : level.getPlatforms())
					{
						if (plat.intersects(new Rectangle((int) player.getX()+10, (int)player.getY(), pWidth-10, pHeight + 2)))
						{
							player.setYState(Player.DOWN);
							yPos = (int) plat.getY() - pHeight;
							jumpMoveCounter = 0;
							player.setPlatState(true);
							// sort of moves the player on the moving platform?? but too much rip
							 if (plat instanceof MovingObject)
							 {
								 moveLoc = new Point((int)moveLoc.getX() + ((MovingObject)plat).getXVelocity(),(int)moveLoc.getY());
							 }
						}
					}
					/*
					 * if (plat.intersects(new Rectangle((int)player.getX(),
					 * (int)player.getY(), pWidth, pHeight + 3))) {
					 * player.setPlatState(true); jumpMoveCounter = 0; }
					 */
				}
			}
			// System.out.println(new Point((int) moveLoc.getX(), yPos));
			return new Point((int) moveLoc.getX(), yPos);

			/*
			 * if (jumpMoveCounter == 0) yInit = (int)moveLoc.getY();
			 * jumpMoveCounter++; int yPos = yInit; if (player.getYState() ==
			 * Player.UP) { yPos = yInit + (int)(7*jumpMoveCounter +
			 * 5*Math.pow(jumpMoveCounter, 2)); if (moveLoc.getY() <= yInit -
			 * 50) player.setYState(Player.DOWN); } else if (player.getYState()
			 * == Player.DOWN) yPos = (yInit - 50) + (int)(7*jumpMoveCounter +
			 * 5*Math.pow(jumpMoveCounter, 2)); if (yPos > yInit) {
			 * player.setYState(Player.STILL); jumpMoveCounter = 0; yPos =
			 * yInit; } System.out.println(new Point ((int)moveLoc.getX(),
			 * yPos)); return new Point((int)moveLoc.getX(), yPos);
			 */

			/*
			 * int yPos = (int)moveLoc.getY(); Point move = moveLoc; if
			 * (jumpMoveCounter == 50) { player.setYState(Player.DOWN);
			 * jumpMoveCounter = 0; } if (player.getYState() == Player.UP) move
			 * = new Point((int)move.getX(), (int)(yPos - .05*jumpMoveCounter));
			 * else move = new Point((int)move.getX(), (int)(yPos +
			 * .05*jumpMoveCounter)); System.out.println(move); if
			 * ((int)move.getY() >= 600) { player.setYState(Player.STILL);
			 * jumpMoveCounter = 0; } return move;
			 */

			/*
			 * if (jumpMoveCounter == 1) return new Point((int)moveLoc.getX(),
			 * (int)(moveLoc.getY() - 25)); else if (jumpMoveCounter == 2)
			 * return new Point((int)moveLoc.getX(), (int)(moveLoc.getY() -
			 * 10)); else if (jumpMoveCounter == 3) {
			 * //player.setYState(Player.DOWN); return new
			 * Point((int)moveLoc.getX(), (int)(moveLoc.getY() - 5)); } else if
			 * (jumpMoveCounter == 4) { /*Point platLoc = new
			 * Point((int)player.getX() + pWidth + 1, (int)player.getY() +
			 * pHeight + 1); if (level.getPlatform(platLoc) != null) {
			 */
			// return new Point((int)moveLoc.getX(), (int)(moveLoc.getY() + 5));

			// return moveLoc;
			/*
			 * } else { System.out.println(new Point((int)moveLoc.getX(),
			 * (int)(moveLoc.getY() - 20))); return new
			 * Point((int)moveLoc.getX(), (int)(moveLoc.getY() - 20)); } } else
			 * if (jumpMoveCounter == 5) return new Point((int)moveLoc.getX(),
			 * (int)(moveLoc.getY() + 10)); else if (jumpMoveCounter == 6) {
			 * player.setYState(Player.STILL); jumpMoveCounter = 0; return new
			 * Point((int)moveLoc.getX(), (int)(moveLoc.getY() + 25)); }
			 */
		}
		return null;
	}
}
