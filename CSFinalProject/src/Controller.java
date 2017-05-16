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

	    //constructs Grid with correct set of obstacles and platforms, and update’s GUI’s display
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
