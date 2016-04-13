package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
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
			g2.drawString("You won?!", 400, 400);
		
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