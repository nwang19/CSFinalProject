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
    private int number;
    public GUI(Controller cont)
    {
        number = 0;
        timer = new Timer(250, this);
        jumpTimer = new Timer(250, this);
        ClassLoader cldr = this.getClass().getClassLoader();
        control = cont;
        //we'll have to add gif files or something to Eclipse make the images insertable into the program
       /* gameName = new JLabel("Westview Life");
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
        container.add(gameName, BorderLayout.NORTH); */
        addKeyListener(this); 
        this.setSize(500, 500);
        this.setVisible(true);
        
    }
    public static void main(String[] args)
    {
        Controller c = new Controller();
        GUI gui = new GUI(c);
        gui.display();
    }

    public void init(int levelNum)
    {
        repaint();
        
        
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
     public void updateScreen(Player player)
     {
         repaint();
   
     }

  //checks if either right or left arrow key is pressed
      public void keyPressed(KeyEvent event) 
      {
          if (event.getKeyCode() == KeyEvent.VK_RIGHT) 
              control.getPlayer().setXState(Player.RIGHT);
          else if (event.getKeyCode() == KeyEvent.VK_LEFT)
              control.getPlayer().setXState(Player.LEFT);
          timer.addActionListener(this);
          timer.start();
      }
      
      //checks if user hit the up arrow key. If yes, starts timer and sets player's jumping state to true
      public void keyTyped(KeyEvent event)
      {
          if (control.getPlayer().getYState() == Player.STILL && event.getKeyCode() == KeyEvent.VK_UP) 
          {
              jumpTimer.addActionListener(this);
              jumpTimer.start();
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
        paintOffScreen(buffer);        
        g.drawImage(offImage, 0, 0, null);    
    }

    public void paintOffScreen(Graphics g)
    {
        
        if(number == 0)
        {
        Level lev = control.getLevel();
        for(Platform plat: lev.getPlatforms())
        {
            g.setColor(Color.black);
            g.drawRect(plat.x, plat.y, Platform.width, Platform.height);
            g.fillRect(plat.x, plat.y, Platform.width, Platform.height);    
        }
        
        for(Obstacle obs: lev.getObstacles())
        {
            g.setColor(Color.black);
            g.drawRect(obs.x, obs.y, obs.width, obs.height);
            g.fillRect(obs.x, obs.y, obs.width, obs.height);    
        }
        
        // sometimes helpful to do this first to clear things:
         g.clearRect(0, 0, 20, 20);
        }
        
        g.setColor(Color.blue);
        g.drawRect(control.getPlayer().x, control.getPlayer().y, control.getPlayer().width, control.getPlayer().height);
        g.fillRect(control.getPlayer().x, control.getPlayer().y, control.getPlayer().width, control.getPlayer().height);    
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
            updateScreen(control.getPlayer());
        } 
    }

    //Use classloader when reading in images (in GuiTest samples)
}
