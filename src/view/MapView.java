package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import controller.GameGUI;
import model.Game;
import model.Map;
import model.MapItem;
import model.MapTile;
import model.State;
import model.Tileset;

public class MapView extends JPanel implements Observer {

	private static final long serialVersionUID = -117722908651710751L;
	private int cameraX, cameraY;
	private Game game;
	private Map map;
	private Tileset tileset;

	private int trainerOldX;
	private int trainerOldY;
	public boolean animating;
	private final int spriteSize = 32;

	//Trainer Images
	private BufferedImage trainer;
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
    private int oldCamX;
    private int oldCamY;
    public boolean endAnimation;
   
    private final int DELAY_TIME = 80;
    private static final int CAM_X_OFFSET = 32;
    private static final int CAM_Y_OFFSET = 22;
    
    private float transitionAlpha;
	
	//Item Imags
	private HashMap<String,BufferedImage> itemImages;
	public MapView(Game game){
		this.game = game;
		this.map = game.getMap();
		this.animating = false;
		this.endAnimation = true;
		this.tileset = new Tileset(map.getTileset(), map.getTileSize());

		loadTrainerImages();
		itemImages = GameGUI.loadItemImages();

		isUsingLeftFoot = true;
		facing = trainer.getSubimage( 0, 64, spriteSize, spriteSize );
		initial = false;
		selectingItem = false;
		updateCamera();
	}
	private int offset = 0;
    private boolean reset = false;
    public void paintComponent(Graphics g){
        super.paintComponent( g );
        Graphics2D g2 = (Graphics2D)g;
        g2.clearRect(0, 0, 100000, 100000);
        g2.translate(-cameraX*map.getTileSize(), -cameraY*map.getTileSize());
        if( animating )
        {
            if( oldCamY > cameraY )
            {
                if( !reset )
                {
                    offset = 0;
                    reset = true;
                }
                offset+=8;
                g2.translate(-oldCamX*map.getTileSize(), -oldCamY*map.getTileSize());
                g2.translate(cameraX*map.getTileSize(), cameraY*map.getTileSize() + offset);
            }
            if( oldCamY < cameraY )
            {
                if( !reset )
                {
                    offset = 0;
                    reset = true;
                }
                offset+=8;
                g2.translate(-oldCamX*map.getTileSize(), -oldCamY*map.getTileSize());
                g2.translate(cameraX*map.getTileSize(), cameraY*map.getTileSize() - offset);
            }
            if( oldCamX > cameraX )
            {
                if( !reset )
                {
                    offset = 0;
                    reset = true;
                }
                offset+=8;
                g2.translate(-oldCamX*map.getTileSize(), -oldCamY*map.getTileSize());
                g2.translate(cameraX*map.getTileSize() + offset, cameraY*map.getTileSize());
            }
            if( oldCamX < cameraX )
            {
                if( !reset )
                {
                    offset = 0;
                    reset = true;
                }
                offset+=8;
                g2.translate(-oldCamX*map.getTileSize(), -oldCamY*map.getTileSize());
                g2.translate(cameraX*map.getTileSize() - offset, cameraY*map.getTileSize());
            }
        }
        else
        {
            offset = 0;
            reset = false;
        }
        MapTile[][] tiles = map.getTiles();
        int tileSize = map.getTileSize();
        for(int x=0; x<tiles.length; x++){
            for(int y=0; y<tiles[0].length; y++){
                int imgx = tiles[x][y].getTilesetX();
                int imgy = tiles[x][y].getTilesetY();
                g2.drawImage(tileset.tileAt(imgx, imgy), x*tileSize, y*tileSize, null);
            }
        }

        //Draw all of the items on top of the tiles
        for(MapItem m : map.getMapItems()){
            g2.drawImage(itemImages.get(m.getItem().getName()), m.getX()*map.getTileSize(), m.getY()*map.getTileSize(), null);
        }


        //Used for animations (should probably use another thread if possible)
        for(int x=0; x<tiles.length; x++){
            for(int y=0; y<tiles[0].length; y++){
                if (x==game.getPlayerX() && y==game.getPlayerY()){
                    if( animating )
                    {
                        animate(g2,x,y);
                    }
                    else
                    {
                        trainerOldX = x*tileSize;
                        trainerOldY = y*tileSize;
                        g2.drawImage(facing, trainerOldX, trainerOldY, null);
                        endAnimation = false;
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

            PokemonView viewPokemon = new PokemonView( game );
            viewPokemon.setVisible( true );
            viewPokemon.setLocation(100, 210);
            this.add( viewPokemon );
            this.add( endGame );
        }
        
        g2.setColor(new Color(0.0f, 0.0f, 0.0f, transitionAlpha/255.0f));
        g2.fillRect(0, 0, 8000, 8000);
    }

	private void loadTrainerImages(){
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
	}

	private void animate(Graphics2D g2, int x, int y)
    {
        int tileSize = map.getTileSize();
        final int MOVE_PIXELS = 8;
        endAnimation = true;
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

	public void updateCamera(){
	    oldCamX = cameraX;
		cameraX = game.getPlayerX()-15;
		oldCamY = cameraY;
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
	    if (cameraX < 0){
	        cameraX = 0;
	        oldCamX = 0;
	    }
        if (cameraX+CAM_X_OFFSET > map.getWidth()){
            cameraX = map.getWidth()-CAM_X_OFFSET;
            oldCamX = map.getWidth()-CAM_X_OFFSET;
        }
        if (cameraY < 0){
            cameraY = 0;
            oldCamY = 0;
        }
        if (cameraY+CAM_Y_OFFSET > map.getHeight()){
            cameraY = map.getHeight()-CAM_Y_OFFSET;
            oldCamY = map.getHeight()-CAM_Y_OFFSET;
        }
	}

	public void updateTileset(){
		this.tileset = new Tileset(map.getTileset(), map.getTileSize());
	}

	@Override
	public void update(Observable o, Object obj) {
		game = (Game)o;
		transitionAlpha = game.getTransitionAlpha();
		if (!game.getMap().equals(map)){
			map = game.getMap();
			updateTileset();
		}
		else map = game.getMap();
		if( initial && !game.inTransition ){
			animating = true;
		};
		if (obj != null){
			if ((int)obj == 1) updateTrainerPos();
		}
		updateCamera();
		repaint();
		//if( !game.inTransition )
		//game.inTransition = true;
	}
}