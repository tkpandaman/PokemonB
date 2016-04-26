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
import model.RunningShoes;
import view.BattleView;
import view.ItemSelector;
import model.State;
import model.WalkingShoes;
import view.MapView;
import view.Menu;
import view.PokemonView;

public class GameGUI extends JFrame {

	private Game game;
	private HashMap<String, Map> maps;
	private MapView mapView;
	private BattleView battleView;
	private Menu menu;
	private static final String SAVED_COLLECTION_LOCATION = "pokemonSave";
	private boolean selectingItem;
	private boolean pokemonList;
	private ItemSelector items;
	private PokemonView pokemon;
	public static void main(String[] args){
		GameGUI gui = new GameGUI();
		gui.setVisible(true);
	}

	public GameGUI(){
		this.setTitle("Pokemon");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1024, 768);

		this.addWindowListener(new SaveAndLoad());

		maps = loadMaps();

		game = new Game(maps, maps.get("viridian-forest"));
		mapView = new MapView(game);
		battleView = new BattleView(game);
		selectingItem = false;
        pokemonList = false;
		//shoes.use( game.getTrainer() );
		//System.out.println( game.getTrainer().getSpeed() );
		game.addObserver(mapView);

		this.add(mapView);
		//this.add(battleView);

		this.addKeyListener(new ArrowKeyListener());

		//this.repaint();
		mapView.repaint();
		battleView.repaint();
	}

	private HashMap<String, Map> loadMaps(){

		this.maps = new HashMap<String, Map>();

		for(File mapFile : mapFiles()){
			try{
				FileInputStream fileIn = new FileInputStream(mapFile);
				ObjectInputStream in = new ObjectInputStream(fileIn);

				//read objects from saved data
				maps.put(mapFile.getName(), (Map) in.readObject());

				in.close();
				fileIn.close();
			} catch(Exception ee){
				ee.printStackTrace();
			}
		}
		return maps;

	}
	
	/**
	 * This method returns the files in the levels folder.
	 * @return ArrayList of Files as map files
	 */
	public static ArrayList<File> mapFiles(){ 

		ArrayList<File> mapFiles = new ArrayList<File>();

		File dir = new File("levels");
		File[] directoryListing = dir.listFiles();
		if (directoryListing != null) {
			for (File child : directoryListing) {
				mapFiles.add(child);
			}
		} else {
			System.err.println("No Directory found");
		}
		
		return mapFiles;

	}

	private class ArrowKeyListener implements KeyListener{

		@Override
		public void keyPressed(KeyEvent event) {
			if(event.getKeyCode() == KeyEvent.VK_ESCAPE){
				if( game.getState() == State.NORMAL || game.getState() == State.MENU )
				{
				    if( !pokemonList && !selectingItem )
				    {    
				        game.chooseMenu();
				        if( game.getState() == State.MENU )
	                    {
	                        menu = new Menu( game );
	                        mapView.add(menu);
	                    }
				    }
					if( pokemonList )
                    {
					    pokemonList = false;
                        mapView.remove( pokemon );
                    }
					if( selectingItem )
					{
					    selectingItem = false;
					    mapView.remove( items );
					}
					mapView.repaint();
				}
			}
			if (event.getKeyCode() == KeyEvent.VK_UP){
				if( ( game.getState() == State.NORMAL && !mapView.animating ) || game.getState() == State.BATTLE )
				{
					game.moveUp();
				}
				if( game.getState() == State.MENU )
				{
				    if( !pokemonList && !selectingItem )
				    {
				        menu.moveUp();
				    }
				    if( selectingItem )
				    {
				        items.moveUp();
				    }
				}
			}
			if (event.getKeyCode() == KeyEvent.VK_DOWN){
				if( ( game.getState() == State.NORMAL && !mapView.animating ) || game.getState() == State.BATTLE )
				{
					game.moveDown();
				}
				if( game.getState() == State.MENU )
				{
				    if( !pokemonList && !selectingItem )
				    {
				        menu.moveDown();
				    }
				    if( selectingItem )
				    {
				        items.moveDown();
				    }
				}
			}
			if (event.getKeyCode() == KeyEvent.VK_LEFT ){
				if( ( game.getState() == State.NORMAL && !mapView.animating ) || game.getState() == State.BATTLE )
				{
					game.moveLeft();
				}
			}
			if (event.getKeyCode() == KeyEvent.VK_RIGHT ){
				if( ( game.getState() == State.NORMAL && !mapView.animating ) || game.getState() == State.BATTLE )
				{
					game.moveRight();
				}
			}
			if (event.getKeyCode() == KeyEvent.VK_Z)
				game.select();
			if( event.getKeyCode() == KeyEvent.VK_ENTER )
			{
				if( game.getState() == State.MENU )
				{
				    if( selectingItem )
                    {
				        if( game.getTrainer().openPack().getItems().size() > 0 )
				        {
				            game.getTrainer().openPack().getItems().get( items.getSelected() ).use( game.getTrainer() );
				            selectingItem = false;
				            mapView.remove( items );
				            mapView.repaint();
				        };
                    }
				    else if( !pokemonList && ! selectingItem )
				    {
				        if( menu.getSelected() == 0 )
				        {
				            selectingItem = true;
				            items = new ItemSelector( game );
				            items.setLocation( 10, 10 );
				            items.setVisible( true );
				            mapView.add( items );
				            mapView.repaint();
				        }
				        if( menu.getSelected() == 1 )
				        {
				            pokemonList = true;
				            pokemon = new PokemonView( game );
				            pokemon.setLocation( 10, 10 );
				            pokemon.setVisible( true );
				            mapView.add( pokemon );
				            mapView.repaint();
				        }
				        if( menu.getSelected() == 2 )
				        {
				            game.chooseMenu();
				            mapView.remove( menu );
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
				        }
				        if( menu.getSelected() == 3 )
	                    {
	                        game.chooseMenu();
	                        mapView.remove( menu );
	                    }
					}
				}
			}

			if( !mapView.animating && game.getState() == State.BATTLE){
				GameGUI.this.remove(mapView);
				GameGUI.this.add(battleView);
				GameGUI.this.revalidate();
				GameGUI.this.repaint();
			} else if( game.getState() != State.MENU ){
				GameGUI.this.remove(battleView);
				GameGUI.this.add(mapView);
				GameGUI.this.revalidate();
				GameGUI.this.repaint();
			}
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
			mapView.initial = true;
			// change GUI after data loaded
		};
		@Override
		public void windowClosing( WindowEvent e )
		{
		};
	};


}
