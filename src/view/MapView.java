package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Game;
import model.Map;
import model.MapTile;
import model.State;
import model.Tileset;
import model.pokemon.Charizard;

public class MapView extends JPanel implements Observer {
	
	private int cameraX, cameraY;
	private Game game;
	private Map map;
	private Tileset tileset;
	private Image trainer;

	public MapView(Game game){
		this.game = game;
		this.map = game.getMap();
		this.tileset = new Tileset(map.getTileset(), map.getTileSize());
		
		try {
			trainer = ImageIO.read(new File("images/trainer.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		updateCamera();
	}
	
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		
		g2.clearRect(0, 0, 100000, 100000);
		
		g2.translate(-cameraX*map.getTileSize(), -cameraY*map.getTileSize());
		
		MapTile[][] tiles = map.getTiles();
		int tileSize = map.getTileSize();
		for(int x=0; x<tiles.length; x++){
			for(int y=0; y<tiles[0].length; y++){
				int imgx = tiles[x][y].getTilesetX();
				int imgy = tiles[x][y].getTilesetY();
				g2.drawImage(tileset.tileAt(imgx, imgy), x*tileSize, y*tileSize, null);
				if (x==game.getPlayerX() && y==game.getPlayerY()){
					g2.drawImage(trainer, x*tileSize, y*tileSize, null);
				}
			}
		}
		
		g2.translate(cameraX*map.getTileSize(), cameraY*map.getTileSize());
		
		g2.setColor(Color.BLUE);
		if (game.getState() == State.WIN)
		{
		    // Panel to display stats after game ends
            JPanel stats = new JPanel();
            stats.setLayout( null );
            stats.setSize( 800, 100 );
            stats.setLocation( 100, 100 );
            stats.setBorder( BorderFactory.createLineBorder( Color.BLACK ) );
            // game over label
            JLabel end = new JLabel("GAME OVER" );
            end.setFont( new Font( "Serif", Font.BOLD + Font.ITALIC, 20 ) );
            end.setSize( 500, 20 );
            end.setLocation( 10, 10 );
            stats.add( end );
            // player name label
            JLabel name = new JLabel("PLAYER: " + game.getTrainer().getName() );
            name.setFont( new Font( "Serif", Font.BOLD, 20 ) );
            name.setSize( 500, 20 );
            name.setLocation( 10, 40 );
            stats.add( name );
            /*JButton newGame = new JButton( "New Game" );
            newGame.setSize( 150, 25 );
            newGame.setLocation( 10, 60 );
            newGame.addActionListener( new ActionListener()
            {
                @Override
                public void actionPerformed( ActionEvent e )
                {
                    // need a new game method for game if we want this button to work
                }
            });
            stats.add( newGame );*/
            // pokemon caught label
            JLabel pokemonCaught = new JLabel( "POKEMON CAUGHT: " + game.getTrainer().openPack().getPokemonCaptured() );
            pokemonCaught.setFont( new Font( "Serif", Font.BOLD, 20 ) );
            pokemonCaught.setSize( 500, 20 );
            pokemonCaught.setLocation( 410, 10 );
            stats.add( pokemonCaught );
            // pokebalsl remaining label
            JLabel pokeballs = new JLabel( "POKEBALLS REMAINING: " + game.getTrainer().openPack().getPokeballsLeft() );
            pokeballs.setFont( new Font( "Serif", Font.BOLD, 20 ) );
            pokeballs.setSize( 500, 20 );
            pokeballs.setLocation( 410, 40 );
            stats.add( pokeballs );
            // steps taken label
            JLabel steps = new JLabel( "STEPS TAKEN: " + ( 500 - game.getTrainer().getStepsLeft() ) );
            steps.setFont( new Font( "Serif", Font.BOLD, 20 ) );
            steps.setSize( 500, 20 );
            steps.setLocation( 410, 70 );
            stats.add( steps );
            this.add( stats );
            
            // Panel to display pokemon caught after game ends
            JPanel pokemon = new JPanel();
            pokemon.setLayout( null );
            pokemon.setSize( 200, 30 + ( 30 * game.getTrainer().openPack().getPokemonCaptured() ) );
            pokemon.setLocation( 100, 250 );
            pokemon.setBorder( BorderFactory.createLineBorder( Color.BLACK ) );
            // pokemon label
            JLabel pokemonLabel = new JLabel( "POKEMON (" + game.getTrainer().openPack().getPokemonCaptured() + "):" );
            pokemonLabel.setFont( new Font( "Serif", Font.BOLD, 20 ) );
            pokemonLabel.setSize( 200, 20 );
            pokemonLabel.setLocation( 5, 5 );
            pokemon.add( pokemonLabel );
            for( int i = 0; i < game.getTrainer().openPack().getPokemonCaptured(); i++ )
            {
                JLabel poke = new JLabel( ( i + 1 ) + ".) " +game.getTrainer().openPack().getPokemonAt( i ).getClass().getSimpleName() );
                poke.setFont( new Font( "Serif", Font.BOLD, 20 ) );
                poke.setSize( 200, 20 );
                poke.setLocation( 5, 30 + ( 30 * i ) );
                pokemon.add( poke );
            }
            this.add( pokemon );
		}
		
	}
	
	private void updateCamera(){
		cameraX = game.getPlayerX()-15;
		cameraY = game.getPlayerY()-11;
		
		if (cameraX < 0) cameraX = 0;
		if (cameraX+32 > map.getWidth()) cameraX = map.getWidth()-32;
		if (cameraY < 0) cameraY = 0;
		if (cameraY+32 > map.getHeight()) cameraY = map.getHeight()-32;
	}

	@Override
	public void update(Observable o, Object obj) {
		game = (Game)o;
		map = game.getMap();
		updateCamera();
		repaint();
	}
	
	
}