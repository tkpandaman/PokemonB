package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.Game;
import model.Map;
import model.MapTile;
import model.State;
import model.Tileset;

public class MapView extends JPanel implements Observer {
	
    private static final long serialVersionUID = -117722908651710751L;
    private int cameraX, cameraY;
	private Game game;
	private Map map;
	private Tileset tileset;
	private BufferedImage trainer;
	private int trainerOldX;
	private int trainerOldY;
	public boolean animating;
	private int imageX;
	private int imageY;
	private final int spriteSize = 32;
	private BufferedImage facing;
	private BufferedImage forward;
	private BufferedImage backward;
	private BufferedImage left;
	private BufferedImage right;
	private BufferedImage forward_walking_left;
	private BufferedImage forward_walking_right;
	private BufferedImage backward_walking_left;
    private BufferedImage backward_walking_right;
    private BufferedImage left_walking_left;
    private BufferedImage left_walking_right;
    private BufferedImage right_walking_left;
    private BufferedImage right_walking_right;
    private boolean isUsingLeftFoot;
	public MapView(Game game){
		this.game = game;
		this.map = game.getMap();
		this.animating = false;
		this.imageX = 0;
		this.imageY = 64;
		//this.trainerOldX = 2;
		//this.trainerOldY = 2;
		this.tileset = new Tileset(map.getTileset(), map.getTileSize());
		try {
			trainer = ImageIO.read(new File("images/red.png"));
			forward = trainer.getSubimage( 0, 64, spriteSize, spriteSize );
			backward = trainer.getSubimage( 0, 0, spriteSize, spriteSize );
			left = trainer.getSubimage( 0, 32, spriteSize, spriteSize );
			right = trainer.getSubimage( 0, 96, spriteSize, spriteSize );
			forward_walking_left = trainer.getSubimage( 32, 64, spriteSize, spriteSize );
			forward_walking_right = trainer.getSubimage( 64, 64, spriteSize, spriteSize );
			backward_walking_left = trainer.getSubimage( 32, 0, spriteSize, spriteSize );
			backward_walking_right = trainer.getSubimage( 64, 0, spriteSize, spriteSize );
			left_walking_left = trainer.getSubimage( 32, 32, spriteSize, spriteSize );
			left_walking_right = trainer.getSubimage( 64, 32, spriteSize, spriteSize );
			right_walking_left = trainer.getSubimage( 32, 96, spriteSize, spriteSize );
			right_walking_right = trainer.getSubimage( 64, 96, spriteSize, spriteSize );
		} catch (IOException e) {
			e.printStackTrace();
		}
		isUsingLeftFoot = true;
		facing = trainer.getSubimage( 0, 64, spriteSize, spriteSize );
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
				//g2.drawImage( trainer.getSubimage( 64, 0, 32, 32 ), 0, 0, null );
			}
		}
		for(int x=0; x<tiles.length; x++){
            for(int y=0; y<tiles[0].length; y++){
				if (x==game.getPlayerX() && y==game.getPlayerY()){
				    //g2.drawImage(trainer, trainerOldX, trainerOldY, null);
				    
				    if( animating ){
				        if( trainerOldY < y*tileSize )
				        {
				            facing = forward;
				            if( isUsingLeftFoot ){
				                g2.drawImage( forward_walking_left, trainerOldX, trainerOldY+=8, null );
				            }
				            else
				            {
				                g2.drawImage( forward_walking_right, trainerOldX, trainerOldY+=8, null );
				            };
				            isUsingLeftFoot = !isUsingLeftFoot;
				            try
                            {
                                Thread.sleep( 75 );
                                repaint();
                            }
                            catch( InterruptedException e )
                            {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
	                        //g2.translate(cameraX*map.getTileSize(), cameraY*map.getTileSize()+16);
				        }
				        if( trainerOldY > y*tileSize )
                        {
                            facing = backward;
                            if( isUsingLeftFoot )
                            {
                                g2.drawImage( backward_walking_left, trainerOldX, trainerOldY-=8, null );
                            }
                            else
                            {
                                g2.drawImage( backward_walking_right, trainerOldX, trainerOldY-=8, null );
                            }
                            isUsingLeftFoot = !isUsingLeftFoot;
                            try
                            {
                                Thread.sleep( 75 );
                                repaint();
                            }
                            catch( InterruptedException e )
                            {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            //g2.translate(cameraX*map.getTileSize(), cameraY*map.getTileSize()+16);
                        }
				        if( trainerOldX > x*tileSize){
				            facing = left;
				            if( isUsingLeftFoot )
				            {
				                g2.drawImage( left_walking_left, trainerOldX-=8, trainerOldY, null );
				            }
				            else
				            {
				                g2.drawImage( left_walking_right, trainerOldX-=8, trainerOldY, null );
				            }
				            isUsingLeftFoot = !isUsingLeftFoot;    
                            try
                            {
                                Thread.sleep( 50 );
                                repaint();
                            }
                            catch( InterruptedException e )
                            {
                                e.printStackTrace();
                            }
				        }
				        if( trainerOldX < x*tileSize){
				            facing = right;
				            if( isUsingLeftFoot )
				            {
				                g2.drawImage( right_walking_left, trainerOldX+=8, trainerOldY, null );
				            }
				            else
				            {
				                g2.drawImage( right_walking_right, trainerOldX+=8, trainerOldY, null );
				            }
				            isUsingLeftFoot = !isUsingLeftFoot;
                            try
                            {
                                Thread.sleep( 50 );
                                repaint();
                            }
                            catch( InterruptedException e )
                            {
                                e.printStackTrace();
                            }
                        }
				        if(trainerOldX == x*tileSize && trainerOldY==y*tileSize)
				        {
	                        animating = false;
	                    };
	                    
				    }
				    else
				    {
				        trainerOldX = x*tileSize;
                        trainerOldY = y*tileSize;
				        g2.drawImage(facing, trainerOldX, trainerOldY, null);
				    }
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
		checkCamera();
	}
	private void checkCamera()
	{
	    if (cameraX < 0) cameraX = 0;
        if (cameraX+32 > map.getWidth()) cameraX = map.getWidth()-32;
        if (cameraY < 0) cameraY = 0;
        if (cameraY+32 > map.getHeight()) cameraY = map.getHeight()-32;
	}
	
	public void updateTileset(){
		this.tileset = new Tileset(map.getTileset(), map.getTileSize());
	}

	@Override
	public void update(Observable o, Object obj) {
		game = (Game)o;
		if (!game.getMap().equals(map)){
			map = game.getMap();
			updateTileset();
		}
		map = game.getMap();
		animating = true;
		updateCamera();
		repaint();
	}
}