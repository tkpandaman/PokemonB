package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import javafx.scene.input.KeyCode;
import model.Game;
import view.MapView;

public class GameGUI extends JFrame {
	
	private Game game;
	
	public static void main(String[] args){
		GameGUI gui = new GameGUI();
		gui.setVisible(true);
	}
	
	public GameGUI(){
		this.setTitle("Pokemon");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1024, 768);
		
		game = new Game();
		
		MapView mapView = new MapView(game);
		
		game.addObserver(mapView);
		
		this.add(mapView);
		
		this.addKeyListener(new ArrowKeyListener());
		
	}
	
	private class ArrowKeyListener implements KeyListener{

		@Override
		public void keyPressed(KeyEvent event) {
			if (event.getKeyCode() == KeyEvent.VK_UP)
				game.moveUp();
			if (event.getKeyCode() == KeyEvent.VK_DOWN)
				game.moveDown();
			if (event.getKeyCode() == KeyEvent.VK_LEFT)
				game.moveLeft();
			if (event.getKeyCode() == KeyEvent.VK_RIGHT)
				game.moveRight();
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
		}
		
	}
	
}
