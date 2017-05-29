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
    	ImageIcon startIcon = new ImageIcon(cldr.getResource("StartScreen.png"));
    	startScreenImg = startIcon.getImage();
    	ImageIcon instructIcon = new ImageIcon(cldr.getResource("instructions.png"));
    	instructionsImg = instructIcon.getImage();
        control = cont;
        addKeyListener(this);
		timer = new Timer(10, this);
        timer.addActionListener(this);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
    }

    //initializes each level with components from the desired level (indicated by levelNum in parameter)
    public void init(int levelNum)
    {
        repaint();
    }

    //displays game
    public void display()
    {
        this.setSize(1000, 1000);
        this.setVisible(true);
        repaint();
    }

 
   //changes player’s position. Note: should repaint at the end
     public void updateScreen(Player player)
     {
         repaint();
     }

  //checks if either right or left arrow key is pressed
      public void keyPressed(KeyEvent event) 
      {
    	  if (event.getKeyCode() == KeyEvent.VK_D) 
              control.getPlayer().setXState(Player.RIGHT);
          else if (event.getKeyCode() == KeyEvent.VK_A)
              control.getPlayer().setXState(Player.LEFT);
    	  timer.start();
      }
      
      //checks if user hit the up arrow key. If yes, starts timer and sets player's jumping state to true
      public void keyTyped(KeyEvent event)
      {
    	  if (event.getKeyChar() == 'w' || event.getKeyChar() == 'W') 
          {
              control.getPlayer().setYState(Player.UP);
              timer.start();
          }
    	  else if((event.getKeyChar() == 'p' || event.getKeyChar() == 'P') || (event.getKeyChar() == 'i' || event.getKeyChar() == 'I') && playScreen == true)
    	  {
    		  startScreen = false;
    		  playScreen = true;
    		  instructScreen = false;
    	  }
    	  else if (event.getKeyChar() == 'i' || event.getKeyChar() == 'I') 
          {
              startScreen = false;
              playScreen = false;
    		  instructScreen = true;
    		  repaint();
          }
    	  else if(event.getKeyChar() == 'p' || event.getKeyChar() == 'P')
    	  {
    		  startScreen = false;
    		  playScreen = true;
    		  instructScreen = false;
    		  repaint();
    	  }
    	  
      }

      //Stops the player's movement and resets player's state to still
     public void keyReleased(KeyEvent event) 
     {
         //DONT FORGET TO HAVE A SEPARTE PLACE THAT STOPS THE JUMP MOTION
    	 if(event.getKeyCode() ==  KeyEvent.VK_D || event.getKeyCode() ==  KeyEvent.VK_A)
         {
    	  control.getPlayer().setXState(Player.STILL);
         }
     }

    
    public void paint(Graphics g)
    {
    	Image offImage = createImage(1000, 1000);
    	Graphics buffer = offImage.getGraphics();
   		paintOffScreen(buffer);        
   		g.drawImage(offImage, 0, 0, null);      
    }

    public void paintOffScreen(Graphics g)
    {
            if(startScreen == true)
            {
            	g.drawImage(startScreenImg, 0, 0, null);
            }
            else if(instructScreen == true)
            {
            	g.drawImage(instructionsImg, 0, 0, null);
            }
            else if(playScreen == true)
            {
 			g.clearRect((int)control.getPlayer().getX(), (int)control.getPlayer().getY(), Controller.pWidth, Controller.pHeight);
 			Level lev = control.getLevel();
 			for(Platform plat: lev.getPlatforms())
 			{
 				g.setColor(Color.black);
 				g.drawRect((int)plat.getX(), (int)plat.getY(), Platform.width, Platform.height);
 				g.fillRect((int)plat.getX(), (int)plat.getY(), Platform.width, Platform.height);    
 			}
 			
 			for(Obstacle obs: lev.getObstacles())
 			{
 				g.setColor(Color.red);
 				g.drawRect((int)obs.getX(), (int)obs.getY(), Obstacle.width, Obstacle.height);
 				g.fillRect((int)obs.getX(), (int)obs.getY(), Obstacle.width, Obstacle.height);    
 			}
 			
        
 			g.drawImage(playerImage, (int)control.getPlayer().getX(), (int)control.getPlayer().getY(), null);
            }
        }
    
    
    public void actionPerformed(ActionEvent event)
    {
        if (event.getSource() == timer)
        {
            if (control.getPlayer().getXState() == Player.STILL && control.getPlayer().getYState() == Player.STILL)	
            	timer.stop();
            {
            	Point moveLoc = control.getPlayer().getLocation();
            	if (control.getPlayer().getXState() == Player.LEFT)
                    moveLoc = new Point((int)moveLoc.getX()-1, (int)moveLoc.getY());
            	else if (control.getPlayer().getXState() == Player.RIGHT)
                    moveLoc = new Point((int)moveLoc.getX()+1, (int)moveLoc.getY());
            	if (control.getPlayer().getYState() != Player.STILL)
                    moveLoc = control.processJump(moveLoc);
            	
            	control.processMove(control.getLevel().checkNextLoc(moveLoc, control.getLevNum(), control.getPlayer().getXState(), control.getPlayer().getYState()));
            	updateScreen(control.getPlayer());
            }
        }
    }
	}

