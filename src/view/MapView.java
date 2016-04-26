package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
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
    public boolean initial;
    public boolean selectingItem;
    private final int DELAY_TIME = 80;
    
	public MapView(Game game){
		this.game = game;
		this.map = game.getMap();
		this.animating = false;
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
		initial = false;
		selectingItem = false;
		updateCamera();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent( g );
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
				    if( animating )
				    {
				        animate(g2,x,y);
				    }
				    else
				    {
				        trainerOldX = x*tileSize;
                        trainerOldY = y*tileSize;
				        g2.drawImage(facing, trainerOldX, trainerOldY, null);
				        try
                        {
                            Thread.sleep( DELAY_TIME / (int)game.getTrainer().getSpeed() );
                        }
                        catch( InterruptedException e )
                        {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
				    }
				}
			}
			
		}
		
		g2.translate(cameraX*map.getTileSize(), cameraY*map.getTileSize());
		
		g2.setColor(Color.BLUE);
		if (game.getState() == State.WIN)
		{
			EndGame endGame = new EndGame( game );
			endGame.setLocation( 100, 100 );
			this.add( endGame );
            
			PokemonView viewPokemon = new PokemonView( game );
			viewPokemon.setLocation(100, 250);
			this.add( viewPokemon );
		}
	}
	
	private void animate(Graphics2D g2, int x, int y)
	{
		int tileSize = map.getTileSize();
		final int MOVE_PIXELS = 8;
		if( trainerOldY < y*tileSize )
        {
            facing = forward;
            if( isUsingLeftFoot ){
                g2.drawImage( forward_walking_left, trainerOldX, trainerOldY+=MOVE_PIXELS, null );
            }
            else
            {
                g2.drawImage( forward_walking_right, trainerOldX, trainerOldY+=MOVE_PIXELS, null );
            };
            isUsingLeftFoot = !isUsingLeftFoot;
            try
            {
                Thread.sleep( DELAY_TIME / (int)game.getTrainer().getSpeed() );
                repaint();
            }
            catch( InterruptedException e )
            {
                e.printStackTrace();
            }
            //g2.translate(cameraX*map.getTileSize(), cameraY*map.getTileSize()+16);
        }
        if( trainerOldY > y*tileSize )
        {
            facing = backward;
            if( isUsingLeftFoot )
            {
                g2.drawImage( backward_walking_left, trainerOldX, trainerOldY-=MOVE_PIXELS, null );
            }
            else
            {
                g2.drawImage( backward_walking_right, trainerOldX, trainerOldY-=MOVE_PIXELS, null );
            }
            isUsingLeftFoot = !isUsingLeftFoot;
            try
            {
                Thread.sleep( DELAY_TIME / (int)game.getTrainer().getSpeed() );
                repaint();
            }
            catch( InterruptedException e )
            {
                e.printStackTrace();
            }
        }
        if( trainerOldX > x*tileSize){
            facing = left;
            if( isUsingLeftFoot )
            {
                g2.drawImage( left_walking_left, trainerOldX-=MOVE_PIXELS, trainerOldY, null );
            }
            else
            {
                g2.drawImage( left_walking_right, trainerOldX-=MOVE_PIXELS, trainerOldY, null );
            }
            isUsingLeftFoot = !isUsingLeftFoot;    
            try
            {
                Thread.sleep( DELAY_TIME / (int)game.getTrainer().getSpeed() );
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
                g2.drawImage( right_walking_left, trainerOldX+=MOVE_PIXELS, trainerOldY, null );
            }
            else
            {
                g2.drawImage( right_walking_right, trainerOldX+=MOVE_PIXELS, trainerOldY, null );
            }
            isUsingLeftFoot = !isUsingLeftFoot;
            try
            {
                Thread.sleep( DELAY_TIME / (int)game.getTrainer().getSpeed() );
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
	
	private void updateCamera(){
		cameraX = game.getPlayerX()-15;
		cameraY = game.getPlayerY()-11;
		checkCamera();
	}
	
	private void updateTrainerPos(){
		trainerOldX = game.getPlayerX();
		trainerOldY = game.getPlayerY();
		animating = false;
	}
	
	private void checkCamera()
	{
	    if (cameraX < 0) cameraX = 0;
        if (cameraX+32 > map.getWidth()) cameraX = map.getWidth()-32;
        if (cameraY < 0) cameraY = 0;
        if (cameraY+28 > map.getHeight()) cameraY = map.getHeight()-28;
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
		if( initial ){
		    animating = true;
		};
		if (obj != null){
			if ((int)obj == 1) updateTrainerPos();
		}
		updateCamera();
		repaint();
	}
}