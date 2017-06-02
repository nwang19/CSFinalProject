import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class GUI extends JFrame implements ActionListener, KeyListener
{
	 private Controller control;
	    private Timer timer;
	    private Image playerImage;
	    private Image startScreenImg;
	    private Image instructionsImg;
	    private Image endScreenImg;
	    private Image seagullImg;
	    private Image bookImg;
	    private Image toxicImg;
	    private Image palmTreesBG;
	    private Boolean startScreen;
	    private Boolean instructScreen;
	    private Boolean playScreen;

	    public GUI(Controller cont) 
	    {
	        startScreen = true;
	        instructScreen = false;
	        playScreen = false;
	        ClassLoader cldr = this.getClass().getClassLoader();
	        ImageIcon playerIcon = new ImageIcon(cldr.getResource("PlayerImg2.png"));
	        playerImage = playerIcon.getImage();
	        ImageIcon startIcon = new ImageIcon(cldr.getResource("StartGame.jpeg"));
	        startScreenImg = startIcon.getImage();
	        ImageIcon instructIcon = new ImageIcon(cldr.getResource("Instructions.jpeg"));
	        instructionsImg = instructIcon.getImage();
	        ImageIcon endIcon = new ImageIcon(cldr.getResource("EndGame.jpeg"));
	        endScreenImg = endIcon.getImage();
	        ImageIcon seagullIcon = new ImageIcon(cldr.getResource("Seagull.gif"));
	        seagullImg = seagullIcon.getImage();
	        ImageIcon bookIcon = new ImageIcon(cldr.getResource("Books.png"));
	        bookImg = bookIcon.getImage();
	        ImageIcon toxicIcon = new ImageIcon(cldr.getResource("ToxicAPCulture.png"));
	        toxicImg = toxicIcon.getImage();
	        ImageIcon backgroundIcon = new ImageIcon(cldr.getResource("PalmTreeBG.jpeg"));
	        palmTreesBG = backgroundIcon.getImage();
	        control = cont;
	        addKeyListener(this);
	        timer = new Timer(7, this);
	        timer.addActionListener(this);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    }

	    // displays game
	    public void display() 
	    {
	        this.setSize(1000, 750);
	        this.setVisible(true);
	        repaint();
	    }

	    // checks if either right or left arrow key is pressed
	    public void keyPressed(KeyEvent event) 
	    {
	        if (event.getKeyCode() == KeyEvent.VK_D)
	            control.getPlayer().setXState(Player.RIGHT);
	        else if (event.getKeyCode() == KeyEvent.VK_A)
	            control.getPlayer().setXState(Player.LEFT);
	        timer.start();
	    }

	    // checks if user hit the up arrow key. If yes, starts timer and sets
	    // player's jumping state to true
	    public void keyTyped(KeyEvent event) 
	    {
	    	if ((event.getKeyChar() == ' ')|| (event.getKeyChar() == 'i' || event.getKeyChar() == 'I') && playScreen == true) 
	        {
	            startScreen = false;
	            playScreen = true;
	            instructScreen = false;
	        }
	    	else if (event.getKeyChar() == ' ') 
	        {            
	            if(control.getLevNum() > 4)
	            {
	                control.setLevNum(0);
	            }
	            startScreen = false;
	            playScreen = true;
	            instructScreen = false;
	            repaint();
	        }
	        else if (event.getKeyChar() == 'i' || event.getKeyChar() == 'I') 
	        {
	            startScreen = false;
	            playScreen = false;
	            instructScreen = true;
	            repaint();
	        } 
	        else if ((event.getKeyChar() == 'w' || event.getKeyChar() == 'W') && control.getPlayer().isOnPlat()) 
	        {
	        	control.getPlayer().setYState(Player.UP);
	            control.getPlayer().setPlatState(false);
	            timer.start();
	        } 

	    }

	    // Stops the player's x-movement and resets player's state to still
	    public void keyReleased(KeyEvent event) 
	    {
	        if (event.getKeyCode() == KeyEvent.VK_D || event.getKeyCode() == KeyEvent.VK_A) 
	            control.getPlayer().setXState(Player.STILL);
	    }

	    //Paint and paintOffScreen make images with double buffering
	    public void paint(Graphics g) 
	    {
	        Image offImage = createImage(1000, 750);
	        Graphics buffer = offImage.getGraphics();
	        paintOffScreen(buffer);
	        g.drawImage(offImage, 0, 0, null);
	    }

	    public void paintOffScreen(Graphics g) 
	    {
	        if (control.getLevNum() > 4) 
	        {
	            playScreen = false;
	            g.drawImage(endScreenImg, 0, 0, null);
	        } 
	        else if (startScreen == true) 
	        {
	            g.drawImage(startScreenImg, 0, 0, null);
	        } 
	        else if (instructScreen == true) 
	        {
	            g.drawImage(instructionsImg, 0, 0, null);
	        } 
	        else if (playScreen == true) 
	        {
	            g.drawImage(palmTreesBG, 0, 0, null);
	        	g.setColor(Color.black);
	            g.clearRect((int) control.getPlayer().getX(), (int) control.getPlayer().getY(), Controller.pWidth,
	                    Controller.pHeight);
	            Level lev = control.getLevel();
	            for (Platform plat : lev.getPlatforms()) 
	            {
	                g.setColor(new Color(224, 205, 158));
	                g.drawRect((int) plat.getX(), (int) plat.getY(),(int) plat.getWidth(), (int)plat.getHeight());
	                g.fillRect((int) plat.getX(), (int) plat.getY(), (int) plat.getWidth(), (int) plat.getHeight());
	            }

	            for (Obstacle obs : lev.getObstacles()) 
	            {
	            	if(obs instanceof Book)
	            		g.drawImage(bookImg, (int) obs.getX(), (int) obs.getY(), null);
	            	else if(obs instanceof ToxicAP)
	            		g.drawImage(toxicImg, (int) obs.getX(), (int) obs.getY(), null);
	            	else if(obs instanceof Seagull)
	            		g.drawImage(seagullImg, (int) obs.getX(), (int) obs.getY(), null);
	            }
	            g.drawImage(playerImage, (int) control.getPlayer().getX(), (int) control.getPlayer().getY(), null);
	        }
	    }
	    
	    public void actionPerformed(ActionEvent event) 
		{
	    	if (event.getSource() == timer) 
			{
	    		Point moveLoc = control.getPlayer().getLocation();
	    		for (Platform plat : control.getLevel().getPlatforms())
				{
	    			if (plat instanceof MovingObject)
	    				((MovingObject)plat).move();
				}
	    		if (control.getPlayer().isOnPlat() == false && control.getPlayer().getYState() == Player.STILL)
					control.getPlayer().setYState(Player.DOWN);
				else
				{
					if (control.getPlayer().getXState() == Player.LEFT)
						moveLoc = new Point((int) moveLoc.getX() - 1, (int) moveLoc.getY());
					else if (control.getPlayer().getXState() == Player.RIGHT)
						moveLoc = new Point((int) moveLoc.getX() + 1, (int) moveLoc.getY());
					if (control.getPlayer().getYState() != Player.STILL)
						moveLoc = control.processJump(moveLoc);
					control.processMove(control.getLevel().checkNextLoc(moveLoc, control, control.getLevNum(),
							control.getPlayer().getXState(), control.getPlayer().getYState()));
					repaint();
				}
			}
		}
}
