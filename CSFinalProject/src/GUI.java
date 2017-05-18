import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI implements ActionListener
{
    private Controller control;
    private JFrame screen;
    private MyDrawPanel drawPanel;
    private JButton playButton;
    private JButton instrButton;
    private Container container;
    private JPanel panel; 
    private JLabel gameName;
    public GUI()
    {
        ClassLoader cldr = this.getClass().getClassLoader();
        //we'll have to add gif files or something to Eclipse make the images insertable into the program
        drawPanel = new MyDrawPanel();
        screen = new JFrame("Westview Life");
        gameName = new JLabel("Westview Life");
        gameName.setFont(new Font("Serif", Font.PLAIN, 50));
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        playButton = new JButton("Play");
        instrButton = new JButton("Instructions");
        playButton.addActionListener(this);
        instrButton.addActionListener(this);
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(100, 200));
        container = screen.getContentPane();
        panel.add(playButton);
        panel.add(instrButton);
        container.add(panel, BorderLayout.SOUTH);
        container.add(gameName, BorderLayout.NORTH); 
        container.setBackground(Color.BLUE);
    }

   public static void main(String[] args)
    {
        GUI gui = new GUI();
        gui.display();
       //gui.paint();
    }

    private class MyDrawPanel extends JPanel
    {  
        public void paintComponent(Graphics g)
        {              
            Image image=new ImageIcon("forest.jpeg").getImage();  
            g.drawImage(image,0,0,this);
        }  
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
        screen.setSize(500, 500);
        screen.setVisible(true);
    }

    //changes player’s position. Note: should repaint at the end
    //public void updateScreen(Player player, ArrayList<Obstacle> obstacles, ArrayList<Platform> platforms)
    //{

    //  repaint();
    //}

    //shows new life count
    public void updateLifeImg()
    {

    }

    //superclass methods
    public void paint(Graphics g)
    {
        super.paint(g);
        g.setColor(Color.black);
        g.drawRect(400, 400, 100, 100);
        g.fillRect(400, 400, 100, 100);
    }
    //public void repaint()

    public void actionPerformed(ActionEvent event)
    {
        if (event.getSource() == playButton)
        {
            container.removeAll();
            init(1); //should display game at level specified in parameter
        }
        else if (event.getSource() == instrButton)
        {
            JOptionPane.showMessageDialog(screen, "Use the up, left, and right arrow keys to move and to jump over obstacles" );
        }
        else
        {
            //do nothing
        }
    }

    //Use classloader when reading in images (in GuiTest samples)
}
