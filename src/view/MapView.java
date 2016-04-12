package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import model.Game;
import model.Map;
import model.MapTile;
import model.Tileset;

public class MapView extends JPanel implements Observer {
	
	private int cameraX, cameraY;
	private Game game;
	private Map map;
	private Tileset tileset;

	public MapView(Game game){
		this.game = game;
		this.map = game.getMap();
		this.tileset = new Tileset(map.getTileset(), map.getTileSize());
	}
	
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		
		g2.drawString(map.getTileset(), 40, 40);
		//g2.translate(cameraX, cameraY);
		
		MapTile[][] tiles = map.getTiles();
		int tileSize = map.getTileSize();
		for(int x=0; x<tiles.length; x++){
			for(int y=0; y<tiles[0].length; y++){
				int imgx = tiles[x][y].getTilesetX();
				int imgy = tiles[x][y].getTilesetY();
				g2.drawImage(tileset.tileAt(imgx, imgy), x*tileSize, y*tileSize, null);
			}
		}
	}

	@Override
	public void update(Observable o, Object obj) {
		game = (Game)o;
		map = game.getMap();
	}
	
}