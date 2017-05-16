import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.Timer;

public class GUI implements KeyListener, ActionListener{

	private Controller control;
	private Timer timer;
	private JFrame screen;
	//private JFrame gameScreen;
	private JButton playButton;
	private JButton instrButton;
	private JTextArea instructions;
	private Container container;

	public GUI()
	{
		timer = new Timer(1000, this);
		JFrame screen = new JFrame("Name of Game HERE");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton playButton = new JButton("Play");
		JButton instrButton = new JButton("Instructions");
		JTextArea instructions = new JTextArea("");
		screen.getContentPane().setLayout(new FlowLayout());
		container = screen.getContentPane();
		container.add(playButton);
		container.add(instrButton);
	}
	
	public void init()
	{
		//should we have this method to initialize different screens?
		//i.e. startScreen, instrScreen, level1, level2, etc.
	}
	
	//displays game
	public void display()
	{
		screen.setVisible(true);
	}

	//changes player’s position. Note: should repaint at the end
	public void updateScreen(Player player, ArrayList<Obstacle> obstacles, ArrayList<Platform> platforms)
	{
		
		repaint();
	}

	//shows new life count
	public void updateLifeImg()
	{
		
	}

	//superclass methods
	//public void paint(Graphics g)
	//public void repaint()

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

	public void keyTyped(KeyEvent event) {
		// TODO Auto-generated method stub
		
	}

	public void actionPerformed(ActionEvent event)
	{
		if (event.getSource() == playButton)
		{
			container.removeAll();
			//add game (not sure how to do this?? h e l p )
			//set game to visible
		}
		else if (event.getSource() == instrButton)
		{
			container.remove(instrButton);
			instructions.setText("Instructions HERE");
			instructions.setVisible(true);
		}
		
	}
	
	//Use classloader when reading in images (in GuiTest samples)

}
