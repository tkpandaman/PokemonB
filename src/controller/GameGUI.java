package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

import model.Battle;
import model.Game;
import model.Map;
import view.BattleView;
import model.State;
import view.MapView;

public class GameGUI extends JFrame {

	private Game game;
	private Battle battle;
	//private BattleView battleView;
	private Map map;
	private ArrayList<Map> maps;
	private MapView mapView;
	private BattleView battleView;

	public static void main(String[] args){
		GameGUI gui = new GameGUI();
		gui.setVisible(true);
	}

	public GameGUI(){
		this.setTitle("Pokemon");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1024, 768);

		this.addWindowListener(new SaveAndLoad());

		loadMaps();

		game = new Game(maps);

		mapView = new MapView(game);
		battleView = new BattleView(game);
		
		game.addObserver(mapView);

		this.add(mapView);
		//this.add(battleView);
		
		this.addKeyListener(new ArrowKeyListener());
		
		this.repaint();
		mapView.repaint();
		battleView.repaint();

	}

	private void loadMaps(){

		this.maps = new ArrayList<Map>();

		ArrayList<File> mapFiles = new ArrayList<File>();
		mapFiles.add(new File("levels/viridian-forest"));
		mapFiles.add(new File("levels/emerald-test"));

		for(int i = 0; i < mapFiles.size(); i++){
			try{
				FileInputStream fileIn = new FileInputStream(mapFiles.get(i));
				ObjectInputStream in = new ObjectInputStream(fileIn);

				//read objects from saved data
				maps.add((Map) in.readObject());

				in.close();
				fileIn.close();
			} catch(Exception ee){
			}
		}

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
			
			if (event.getKeyCode() == KeyEvent.VK_Z)
				game.select();
			

			if(game.getState() == State.BATTLE){
				GameGUI.this.remove(mapView);
				GameGUI.this.add(battleView);
				GameGUI.this.revalidate();
				GameGUI.this.repaint();
			} else {
				GameGUI.this.remove(battleView);
				GameGUI.this.add(mapView);
				GameGUI.this.revalidate();
				GameGUI.this.repaint();
			}

			//System.out.println(game.getPlayerX() + ", " + game.getPlayerY());
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
		}

	}

	private class SaveAndLoad extends WindowAdapter
	{
		private static final String SAVED_COLLECTION_LOCATION = "pokemonSave";

		@Override
		public void windowOpened( WindowEvent e )
		{
			int selectedChoice = JOptionPane.showConfirmDialog( null, "Start with previous saved game?", "Select an option", JOptionPane.YES_NO_CANCEL_OPTION );
			if( selectedChoice == JOptionPane.NO_OPTION )
			{
				// load defaults if we do not want to restore our data
			};
			if( selectedChoice == JOptionPane.YES_OPTION )
			{
				try
				{
					FileInputStream fis = new FileInputStream( SAVED_COLLECTION_LOCATION );
					ObjectInputStream ois = new ObjectInputStream( fis );
					// load our saved data
					game = (Game)ois.readObject();
					ois.close();
					fis.close();

				} catch (Exception exception) {
					exception.printStackTrace();
				}

				game.addObserver(mapView);
				game.getBattleMenu().addObserver(battleView);
				game.update();
			};
			// change GUI after data loaded
		};
		@Override
		public void windowClosing( WindowEvent e )
		{
			int selectedChoice = JOptionPane.showConfirmDialog( null, "Save data?", "Select an option", JOptionPane.YES_NO_CANCEL_OPTION );
			if( selectedChoice == JOptionPane.NO_OPTION )
			{
				System.exit( 0 );
			};
			if( selectedChoice == JOptionPane.YES_OPTION )
			{
				try
				{
					// save current state of pokemon game (Trainer, pokemon, items, backpack, etc.)
					FileOutputStream fos = new FileOutputStream(SAVED_COLLECTION_LOCATION);
					ObjectOutputStream oos = new ObjectOutputStream(fos);
					// save all data we need to a file
					oos.writeObject(game);
					oos.close();
					fos.close();
				} catch (Exception exception) {
					exception.printStackTrace();
				}
			};
		};
	};
	

}
