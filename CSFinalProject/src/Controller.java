import java.awt.Point;
import java.awt.Rectangle;

public class Controller {
	private GUI gui;
	private Player player;
	private int levelNum;
	private Level level;
	private int jumpMoveCounter;
	private int yInit;
	private int yPos;
	static int pWidth = 45, pHeight = 60;

	public Controller() {
		levelNum = 1;
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

	// constructs Grid with correct set of obstacles and platforms, and updates GUI's display
	public void initialize() {
		level = new Level(levelNum);
		player.setLocation(level.getStart());
		player.setXState(Player.STILL);
		player.setYState(Player.DOWN);
		player.setPlatState(false);
		gui.display();
		yInit = Level.baseline;
		jumpMoveCounter = 0;
	}

	public void nextLevel() {
		levelNum++;
		if (levelNum <= 4)
		{
			//levelNum++; COME BACK AND CHECK THIS
			initialize();
		}
	}

	// Moves player to next location or beginning of game (if player hit an obstacle). Calls GUI to update display of position
	public void processMove(Point nextLoc) {

		if (nextLoc != null)
		{
			if (nextLoc.equals(level.getStart()))
				initialize();
			else
				player.setLocation(nextLoc);
			if (player.contains(level.getEnd()))
				nextLevel();
			gui.repaint();
		}
	}

	// returns new location player will move to when jumping
	public Point processJump(Point moveLoc)
	{
		if (jumpMoveCounter == 0)
			yInit = (int) player.getLocation().getY();
		jumpMoveCounter++;
		if (player != null)
		{
			if (player.getYState() == Player.UP)
			{
				yPos = (int) moveLoc.getY();
				yPos -= 2;
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
				yPos = (int) moveLoc.getY();
				yPos += 2;
					for (Platform plat : level.getPlatforms())
					{
						if (plat.intersects(new Rectangle((int) player.getX()+10, (int)player.getY(), pWidth-10, pHeight + 2)))
						{
							player.setYState(Player.DOWN);
							yPos = (int) plat.getY() - pHeight;
							jumpMoveCounter = 0;
							player.setPlatState(true);
							 if (plat instanceof MovingObject)
								 moveLoc = new Point((int)moveLoc.getX() + ((MovingObject)plat).getXVelocity(),(int)moveLoc.getY());
						}
					}
			}
			return new Point((int) moveLoc.getX(), yPos);
		}
		return null;
	}
}
