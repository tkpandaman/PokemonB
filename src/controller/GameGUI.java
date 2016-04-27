package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.swing.JOptionPane;

import model.Battle;
import model.Game;
import model.Map;
import model.PokemonItem;
import model.Potion;
import model.RunningShoes;
import view.BattleView;
import view.ItemSelector;
import model.State;
import model.TrainerItem;
import model.WalkingShoes;
import view.MapView;
import view.Menu;
import view.PokemonSelector;
import view.PokemonView;
import view.Stats;

public class GameGUI extends JFrame {

	private Game game;
	private HashMap<String, Map> maps;
	private MapView mapView;
	private BattleView battleView;
	private Menu menu;
	private Stats stats;
	private static final String SAVED_COLLECTION_LOCATION = "pokemonSave";
	private boolean selectingItem;
	private boolean pokemonList;
	private ItemSelector items;
	private PokemonView pokemon;
	private boolean selectingPokemon;
	private PokemonSelector pokemonChoice;
	private boolean pressing;
	private static final String DEFAULT_MAP = "viridian-forest";
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

		game = new Game(maps, maps.get(DEFAULT_MAP), new Random());
		mapView = new MapView(game);
		battleView = new BattleView(game);
		selectingItem = false;
        pokemonList = false;
        selectingPokemon = false;
        pressing = false;
		game.addObserver(mapView);

		this.add(mapView);

		this.addKeyListener(new ArrowKeyListener());

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
	
	/**
	 * This method returns the files in the Items folder.
	 * @return ArrayList of Files as Item image files
	 */
	public static ArrayList<File> itemImageFiles(){ 

		ArrayList<File> imgFiles = new ArrayList<File>();

		File dir = new File("images/items");
		File[] directoryListing = dir.listFiles();
		if (directoryListing != null) {
			for (File child : directoryListing) {
				imgFiles.add(child);
			}
		} else {
			System.err.println("No Directory found");
		}
		
		return imgFiles;

	}
	
	/**
	 * This method returns a HashMap linking Item Images with their name.
	 * @return HashMap of Strings and BufferedImages
	 */
	public static HashMap<String, BufferedImage> loadItemImages(){
		HashMap<String, BufferedImage> itemImages = new HashMap<String, BufferedImage>();
		ArrayList<File> itemFiles = GameGUI.itemImageFiles();
		try{
			for(File f : itemFiles){
				if (f.getName().equals("pokemon_item_safariBall.png")){
					itemImages.put("Pokeball", ImageIO.read(f));
				}
				else if (f.getName().equals("pokemon_item_shoes.png")){
					itemImages.put("Running Shoes", ImageIO.read(f));
				}
				else if (f.getName().equals("pokemon_item_healthPot.png")){
					itemImages.put("Potion", ImageIO.read(f));
				}
			}
		}
		catch(IOException e){
			System.err.println("Couldn't read image file into a BufferedImage");
		}
		return itemImages;

	}
	
	

	private class ArrowKeyListener implements KeyListener{

        @Override
        public void keyPressed(KeyEvent event) {
            /*if( event.getKeyCode() == KeyEvent.VK_J )
            {
                Potion p = new Potion();
                RunningShoes r = new RunningShoes();
                WalkingShoes w = new WalkingShoes();
                game.getTrainer().openPack().addPokemonItem( p );
                game.getTrainer().openPack().addTrainerItem( r );
                game.getTrainer().openPack().addTrainerItem( w );
            }*/
            if(event.getKeyCode() == KeyEvent.VK_ESCAPE ){
                if( ( game.getState() == State.NORMAL && !mapView.animating && !mapView.endAnimation) || game.getState() == State.MENU )
                {
                    if( !pokemonList && !selectingItem )
                    {    
                        game.chooseMenu();
                        if( game.getState() == State.MENU )
                        {
                            menu = new Menu( game );
                            stats = new Stats( game );
                            mapView.add(menu);
                            mapView.add( stats );
                        }
                    }
                    if( pokemonList )
                    {
                        pokemonList = false;
                        mapView.remove( pokemon );
                    }
                    if( selectingItem && !selectingPokemon )
                    {
                        selectingItem = false;
                        mapView.remove( items );
                    }
                    if( selectingPokemon )
                    {
                        selectingPokemon = false;
                        mapView.remove( pokemonChoice );
                    }
                    mapView.repaint();
                }
            }
            if (event.getKeyCode() == KeyEvent.VK_UP ){
                if( ( game.getState() == State.NORMAL && !mapView.animating && !mapView.endAnimation ) || game.getState() == State.BATTLE )
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
                        if( selectingPokemon )
                        {
                            pokemonChoice.moveUp();
                        }
                        else
                        {
                            items.moveUp();
                        }
                    }
                }
            }
            if (event.getKeyCode() == KeyEvent.VK_DOWN  ){
                if( ( game.getState() == State.NORMAL && !mapView.animating && !mapView.endAnimation ) || game.getState() == State.BATTLE )
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
                        if( selectingPokemon )
                        {
                            pokemonChoice.moveDown();
                        }
                        else
                        {
                            items.moveDown();
                        }
                    }
                }
            }
            if (event.getKeyCode() == KeyEvent.VK_LEFT  ){
                if( ( game.getState() == State.NORMAL && !mapView.animating && !mapView.endAnimation ) || game.getState() == State.BATTLE )
                {
                    game.moveLeft();
                }
            }
            if (event.getKeyCode() == KeyEvent.VK_RIGHT ){
                if( ( game.getState() == State.NORMAL && !mapView.animating && !mapView.endAnimation ) || game.getState() == State.BATTLE )
                {
                    game.moveRight();
                }
            }
            if (event.getKeyCode() == KeyEvent.VK_Z ){
                if( game.getState() == State.BATTLE )
                    game.select();
            }
            if( event.getKeyCode() == KeyEvent.VK_ENTER && !mapView.animating && !mapView.endAnimation)
            {
                if( game.getState() == State.MENU )
                {
                    if( selectingItem && !selectingPokemon)
                    {
                        if( items.getNumItems() > 0 )
                        {
                            if( items.getSelected() instanceof PokemonItem )
                            {
                                selectingPokemon = true;
                                pokemonChoice = new PokemonSelector( game );
                                pokemonChoice.setLocation( 300, 10 );
                                pokemonChoice.setVisible( true );
                                mapView.add( pokemonChoice );
                                mapView.repaint();
                            }
                            else
                            {
                                ( (TrainerItem)items.getSelected() ).use( game.getTrainer() );
                                selectingItem = false;
                                mapView.remove( items );
                                mapView.repaint();
                            };
                        };
                    }
                    else if( selectingPokemon )
                    {
                        if( game.getTrainer().openPack().getPokemonCaptured() > 0 )
                        {
                            ( (PokemonItem)items.getSelected() ).use( game.getTrainer().openPack().getPokemonAt( pokemonChoice.getSelected() ) );
                            selectingPokemon = false;
                            mapView.remove( pokemonChoice );
                            mapView.repaint();
                        }
                    }
                    else if( !pokemonList && !selectingItem )
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
                            mapView.remove( stats );
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
                            mapView.remove( stats );
                        }
                    }
                }
            }

            if( !mapView.animating && game.getState() == State.BATTLE){
                GameGUI.this.remove(mapView);
                GameGUI.this.add(battleView);
                battleView.fadeIn();
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
