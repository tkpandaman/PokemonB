package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Game;

public class Menu extends JPanel{
	private static final long serialVersionUID = -5028534985506854419L;
	private Game game;
	private int selected;
	private int numMenuItems;
	private Image arrow;
	public enum MenuChoice
	{
		ITEMS,
		POKEMON,
		SAVE,
		EXIT;
	};
	public Menu( Game game )
	{
		this.game = game;
		numMenuItems = 0;
		selected = 0;
		arrow = null;
		layoutPanel();
	}
	private void layoutPanel()
	{
		this.setLayout(null);
		for( MenuChoice item : MenuChoice.values() )
		{
			numMenuItems++;
		}
		this.setSize( 200,30 + ( 30 * numMenuItems ) );
		this.setBorder( BorderFactory.createLineBorder( Color.BLACK ) );
		this.setLocation(700, 100);
		// title label
		JLabel title = new JLabel( "MENU" );
		title.setFont( new Font( "Serif", Font.BOLD, 20 ) );
        title.setSize( 200, 20 );
        title.setLocation( 75, 5 );
        this.add( title );
        int count = 0;
		for( MenuChoice item : MenuChoice.values() )
		{
			JLabel menuItem = new JLabel( item.name() );
			menuItem.setFont( new Font( "Serif", Font.BOLD, 20 ) );
	        menuItem.setSize( 200, 20 );
	        menuItem.setLocation( 35, 30 + (count*30)  );
	        this.add( menuItem );
			count++;
		};
		try
		{
			arrow = ImageIO.read( new File( "images/arrow.png" ) );
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.drawImage( arrow, 10, 35 + ( selected * 30 ), null );
	}
	public void moveUp()
	{
		if( selected > 0 )
		{
			selected--;
			this.repaint();
		}
	}
	
	public void moveDown()
	{
		if( selected < numMenuItems - 1 )
		{
			selected++;
			this.repaint();
		}
	}
	public int getSelected()
	{
		return selected;
	}
}