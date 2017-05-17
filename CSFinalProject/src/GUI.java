import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//import java.util.ArrayList;

public class GUI implements ActionListener{
    //private Controller control;
    private JFrame screen;
    private MyDrawPanel drawPanel;
    private JButton playButton;
    private JButton instrButton;
    private JTextArea instructions;
    private Container container;

    public GUI()
    {
        screen = new JFrame("Name of Game HERE");
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        drawPanel = new MyDrawPanel();
        playButton = new JButton("Play");
        instrButton = new JButton("Instructions");
        instructions = new JTextArea(10, 30);
        //screen.getContentPane().setLayout(new FlowLayout());
        screen.getContentPane().add(BorderLayout.CENTER,drawPanel);
        drawPanel.add(playButton, BorderLayout.NORTH);
        drawPanel.add(instrButton, BorderLayout.SOUTH);
        //container = screen.getContentPane();
        //container.add(playButton, BorderLayout.CENTER);
        //container.add(instrButton, BorderLayout.SOUTH);
    }

    public static void main(String[] args)
    {
        GUI gui = new GUI();
        gui.display();
        //gui.paint();
    }

    public class MyDrawPanel extends JPanel
    {  
        public void paintComponent(Graphics g)
        {              
            Image image=new ImageIcon("location of image HERE").getImage();  
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
        paint(g);
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
            container.remove(instrButton);
            instructions.setText("Instructions HERE");
            instructions.setEnabled(false);
            instructions.setVisible(true);
        }
        else
        {
            //do nothing
        }
    }

    //Use classloader when reading in images (in GuiTest samples)

}
