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
	private Timer jumpTimer;
    public GUI()
    {
        timer = new Timer(250, this);
        jumpTimer = new Timer(250, this);
    	ClassLoader cldr = this.getClass().getClassLoader();
        control = new Controller();
        //we'll have to add gif files or something to Eclipse make the images insertable into the program
        gameName = new JLabel("Westview Life");
        gameName.setFont(new Font("Serif", Font.PLAIN, 50));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        playButton = new JButton("Play");
        instrButton = new JButton("Instructions");
        playButton.addActionListener(this);
        instrButton.addActionListener(this);
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(100, 200));
        container = getContentPane();
        panel.add(playButton);
        panel.add(instrButton);
        container.add(panel, BorderLayout.SOUTH);
        container.add(gameName, BorderLayout.NORTH); 
        addKeyListener(this); 
        
    }
    public static void main(String[] args)
    {
    	GUI gui = new GUI();
        gui.display();
    }

    public void init(int levelNum)
    {
        //I'm thinking of having this method to initialize different screens?
        //i.e. startScreen, instrScreen, level1, level2, etc.
        //We could have a big level class (and levels extending from it) to hold each
        //level's platforms and obstacles
    }

    //displays game
    public void display()
    {
        this.setSize(500, 500);
        this.setVisible(true);
    }

    //changes player’s position. Note: should repaint at the end
   // public void updateScreen(Player player, ArrayList<Obstacle> obstacles, ArrayList<Platform> platforms)
   // {

    //  repaint();
   // }

  //checks if either right or left arrow key is pressed
  	public void keyPressed(KeyEvent event) 
  	{
  		if (event.getKeyCode() == KeyEvent.VK_RIGHT) 
  			control.getPlayer().setXState(Player.RIGHT);
  		else if (event.getKeyCode() == KeyEvent.VK_LEFT)
  			control.getPlayer().setXState(Player.LEFT);
  		timer.start();
  		timer.addActionListener(this);
  	}
  	
  	//checks if user hit the up arrow key. If yes, starts timer and sets player's jumping state to true
  	public void keyTyped(KeyEvent event)
  	{
  		if (control.getPlayer().getYState() == Player.STILL && event.getKeyCode() == KeyEvent.VK_UP) 
  		{
  			jumpTimer.start();
  			jumpTimer.addActionListener(this);
  			control.getPlayer().setYState(Player.UP);
  		}
  	}

  	//Stops the player's movement and resets player's state to still
  	public void keyReleased(KeyEvent event) 
  	{
  		timer.stop();
  		control.getPlayer().setXState(Player.STILL);
  	}
 
    //public void repaint()
    
    public void paint(Graphics g)
    {
    	super.paint(g);
    	Image offImage = createImage(20, 20);
    	Graphics buffer = offImage.getGraphics();
    	paintOffScreen(buffer);		// your own method
    	g.drawImage(offImage, 0, 0, null);	
    }

    public void paintOffScreen(Graphics g)
    {
    	// sometimes helpful to do this first to clear things:
    	 g.clearRect(0, 0, 20, 20);
    	 g.setColor(Color.black);
         g.drawRect(0, 400, 500, 500);
         g.fillRect(0, 400, 500, 500);
    	// use ‘g.drawImage()’ as needed to draw all the
    	// images on the second buffer
    }
    
    public void actionPerformed(ActionEvent event)
    {
        if (event.getSource() == playButton)
        {
            container.removeAll();
            init(1); //should display game at level specified in parameter
        }
        else if (event.getSource() == instrButton)
        {
            JOptionPane.showMessageDialog(this, "Use the up, left, and right arrow keys to move and to jump over obstacles" );
        }
        else
        {
        	Point moveLoc = control.getPlayer().getLocation();
    		if (event.getSource() == timer)
    		{
    			if (control.getPlayer().getXState() == Player.LEFT)
    				moveLoc = new Point((int)moveLoc.getX()-10, (int)moveLoc.getY());
    			else if (control.getPlayer().getXState() == Player.RIGHT)
    				moveLoc = new Point((int)moveLoc.getX()+10, (int)moveLoc.getY());
    		}
    		else if (event.getSource() == jumpTimer)
    		{
    			if (control.getPlayer().getYState() != 0)
    				moveLoc = control.processJump(moveLoc, jumpTimer);
    		}
    		control.processMove(control.getLevel().checkNextLoc(moveLoc, control.getLevNum(), control.getPlayer().getXState(), control.getPlayer().getYState()));
        }
    }

    //Use classloader when reading in images (in GuiTest samples)
}
