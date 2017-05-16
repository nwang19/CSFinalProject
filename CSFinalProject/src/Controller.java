import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.Timer;

	public class Controller implements ActionListener, KeyListener
	{
	private Grid area;
	private GUI gui;
	private ArrayList<Obstacle> obstacles; 
	private ArrayList<Platform> platforms;
	private Player player;
	private Timer timer;

	public Controller()
	{
		timer = new Timer(250, this);
	    obstacles = new ArrayList<Obstacle>();
	    platforms = new ArrayList<Platform>();
	    player = new Player(); 
	}

	    //constructs Grid with correct set of obstacles and platforms, and update’s GUI’s display
	    public void initialize()
	    {
	        //add stuff to obstacles and platforms
	        gui = new GUI();
	        area = new Grid(obstacles, platforms);
	    }

	    public void keyPressed(KeyEvent event)
		{
			if(event.getKeyCode() == KeyEvent.VK_LEFT)
			{
				//
			}
			else if (event.getKeyCode() == KeyEvent.VK_RIGHT)
			{
				//
			}
			else if(event.getKeyCode() == KeyEvent.VK_UP)
			{
				//
			}
			timer.start();
			
		}

		public void keyReleased(KeyEvent event) {
			// TODO Auto-generated method stub
			timer.stop();
			
		}
	    public void play()
	    {
	        //
	    }

	    public void processMove()
	    {
	        
	    }
	    
	    //moves player
	    public void resetPos()
	    {
	        player.setLocation(processMove()); 
	    }


	    

	}
public class Controller 
	{
	private Grid area;
	private GUI gui;
	private ArrayList<Obstacle> obstacles; 
	private ArrayList<Platform> platforms;
	private Player player;

	public Controller()
	{
	    obstacles = new ArrayList<Obstacles>();
	    platforms = new ArrayList<Platforms>();
	    player = new Player(); 
	}

	    //constructs Grid with correct set of obstacles and platforms, and updateâ€™s GUIâ€™s display
	    public void initialize()
	    {
	        //add stuff to obstacles and platforms
	        gui = new GUI();
	        area = new Grid(obstacles, platforms);
	    }

	    
	    public void play()
	    {
	        
	    }

	    public void processLeft()
	    {
	        
	    }
	    
	    public void processRight()
	    {
	        
	    }
	    
	    public void processUp()
	    {
	        
	    }
	    
	     
	    //moves player to the beginning if dead
	    public void resetLevelToBeg()
	    {
	        
	    }


	    

	}
>>>>>>> branch 'master' of https://github.com/nwang19/CSFinalProject
