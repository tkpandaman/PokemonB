package view;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import model.Game;

public class Menu extends JPanel{
	private static final long serialVersionUID = -5028534985506854419L;
	private Game game;
	public Menu( Game game )
	{
		this.game = game;
		layoutPanel();
	}
	private void layoutPanel()
	{
		this.setLayout(null);
		this.setSize(200,400);
		this.setBorder( BorderFactory.createLineBorder( Color.BLACK ) );
		this.setLocation(700, 100);
	}

}