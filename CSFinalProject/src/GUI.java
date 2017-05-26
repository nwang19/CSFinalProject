import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class GUI extends JFrame implements ActionListener, KeyListener
{
    private Controller control;
    private JButton playButton;
    private JButton instrButton;
    private Container container;
    private JPanel panel; 
    private JLabel gameName;
    private Timer timer;
    private Image playerImage;
    //private boolean startScreen;
    
    public GUI(Controller cont)
    {
        ClassLoader cldr = this.getClass().getClassLoader();
    	ImageIcon playerIcon = new ImageIcon(cldr.getResource("PlayerImg2.png"));
    	playerImage = playerIcon.getImage();
        control = cont;
        //startScreen = true;
        //we'll have to add gif files or something to Eclipse make the images insertable into the program
        /*gameName = new JLabel("Stressedview");
        gameName.setFont(new Font("Serif", Font.PLAIN, 50));
        gameName.setHorizontalAlignment(SwingConstants.CENTER);
        gameName.setForeground(Color.white);
        playButton = new JButton("Play");
        instrButton = new JButton("Instructions"); 
        playButton.addActionListener(this);
        instrButton.addActionListener(this);
        panel = new JPanel();
        panel.setSize(100, 100);
        panel.setPreferredSize(new Dimension(100, 100));
        container = getContentPane();
        container.setBackground(Color.black);
        panel.setBackground(Color.black);
        panel.add(playButton);
        panel.add(instrButton);
        container.add(panel, BorderLayout.CENTER);
        container.add(gameName, BorderLayout.NORTH);*/
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	addKeyListener(this); 
    	timer = new Timer(10, this);
        timer.addActionListener(this);
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
    	//if (startScreen == false)
        {
 			g.clearRect((int)control.getPlayer().getX(), (int)control.getPlayer().getY(), Controller.pWidth, Controller.pHeight);
 			Level lev = control.getLevel();
 			//Platform plat1 = lev.getPlatforms().get(0);
 			//System.out.println("platform info: "+ plat1.getX() + " " + plat1.getY() + " " + Platform.width + " " + Platform.height);
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
    	if (event.getSource() == playButton)
        {
    		container.removeAll();
            //startScreen = false;
            revalidate();
    		init(1); //should display game at level specified in parameter
           
        }
        else if (event.getSource() == instrButton)
        {
        	JOptionPane.showMessageDialog(this, "Use the up, left, and right arrow keys to move and to jump over obstacles" );
        }
        else if (event.getSource() == timer)
        {
            if (control.getPlayer().getXState() == Player.STILL && control.getPlayer().getYState() == Player.STILL)	
            	timer.stop();
            //else
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
