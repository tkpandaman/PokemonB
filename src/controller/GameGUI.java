package controller;

import javax.swing.JFrame;

import model.Game;
import view.MapView;

public class GameGUI extends JFrame {
	
	public static void main(String[] args){
		GameGUI gui = new GameGUI();
		gui.setVisible(true);
	}
	
	public GameGUI(){
		this.setTitle("Pokemon");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1024, 768);
		
		Game game = new Game();
		
		MapView mapView = new MapView(game);
		
		game.addObserver(mapView);
		
		this.add(mapView);
		
	}
	
}
