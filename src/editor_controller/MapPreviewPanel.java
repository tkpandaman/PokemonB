package editor_controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import controller.GameGUI;
import editor_controller.LevelEditor.EditorView;
import model.MapItem;
import model.MapTile;

// A JPanel which shows the current map and allows the user to draw on it.
public class MapPreviewPanel extends JPanel implements Observer {
	private static final long serialVersionUID = -2053840851750981450L;

	private LevelEditor levelEditor;

	private HashMap<String,BufferedImage> itemImages;

	private int mouseoverX = -1; //coords of tile that the mouse is currently hovering over
	private int mouseoverY = -1;

	public MapPreviewPanel(LevelEditor levelEditor){
		this.levelEditor = levelEditor;
		itemImages = GameGUI.loadItemImages();

		//set mouse listeners
		MapMouseListener mouseListener = new MapMouseListener();
		addMouseListener(mouseListener);
		addMouseMotionListener(mouseListener);
	}

	@Override
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		g2.setFont(new Font("Courier", Font.BOLD, 16));
		g2.clearRect(0, 0, this.getWidth(), this.getHeight());

		//draw the map
		MapTile[][] map = levelEditor.getMap().getTiles();
		int tileSize = levelEditor.getTileSize();
		for(int x=0; x<map.length; x++){
			for(int y=0; y<map[0].length; y++){
				//draw the tile
				int imgx = map[x][y].getTilesetX();
				int imgy = map[x][y].getTilesetY();
				g2.drawImage(levelEditor.getTileset().tileAt(imgx, imgy), x*tileSize, y*tileSize, null);

				if (levelEditor.getEditorView() == EditorView.Collisions){
					//show a red square if a tile is solid, and green if it isn't.
					if(map[x][y].isSolid())
						g2.setColor(new Color(1.0f, 0.0f, 0.0f, 0.3f));
					else
						g2.setColor(new Color(0.0f, 1.0f, 0.0f, 0.3f));
					g2.fillRect(x*tileSize, y*tileSize, tileSize, tileSize);
				}

				if (levelEditor.getEditorView() == EditorView.TileType){
					//show the abbreviation of each tile's TileType
					g2.setColor(new Color(0.0f, 0.0f, 0.0f, 0.4f));
					g2.fillRect(x*tileSize, y*tileSize, tileSize, tileSize);
					g2.setColor(new Color(1.0f, 1.0f, 1.0f, 1));
					g2.drawString(map[x][y].getTileType().getAbbreviation(), x*tileSize+tileSize/2-4, y*tileSize+tileSize/2+4);
				}
			}
		}

		//Draw all of the items on top of the tiles
		for(MapItem m : levelEditor.getMap().getMapItems()){
			g2.drawImage(itemImages.get(m.getItem().getName()), m.getX()*levelEditor.getTileSize(), m.getY()*levelEditor.getTileSize(), null);
		}

		//draw preview for cursor
		if(levelEditor.cursor.equals("Tiles")){
			int imgx = levelEditor.getCurrentTile().getTilesetX();
			int imgy = levelEditor.getCurrentTile().getTilesetY();
			BufferedImage ghostImage = levelEditor.getTileset().tileAt(imgx, imgy);
			g2.drawImage(ghostImage, mouseoverX*tileSize, mouseoverY*tileSize, null);
		}
		else if(levelEditor.cursor.equals("Items") && levelEditor.getCurrentItem() != null){
			BufferedImage ghostItemImage = itemImages.get(levelEditor.getCurrentItem().getName());
			g2.drawImage(ghostItemImage, mouseoverX*tileSize, mouseoverY*tileSize, null);
		}

	}

	//Whenever the model changes, repaint.
	@Override
	public void update(Observable model, Object obj) {
		repaint();
	}

	// A class which responds to mouse events so you can draw tiles onto the level.
	private class MapMouseListener implements MouseListener, MouseMotionListener{
		private MapMouseListener(){

		}

		@Override
		public void mouseClicked(MouseEvent e) {

			try{
				if(!levelEditor.copy){ //If copy button is not selected
					if(levelEditor.cursor.equals("Tiles")){
						levelEditor.drawTile(e.getX()/levelEditor.getTileSize(), e.getY()/levelEditor.getTileSize());
					}
					else if(levelEditor.cursor.equals("Items")){
						if(levelEditor.getCurrentItem() != null){
							levelEditor.getMap().addMapItem(new MapItem(e.getX()/levelEditor.getTileSize(), e.getY()/levelEditor.getTileSize(), levelEditor.getCurrentItem()));
						}
						else {
							levelEditor.getMap().popMapItemAt(e.getX()/levelEditor.getTileSize(), e.getY()/levelEditor.getTileSize());
						}
					}
				}
				else{
					levelEditor.copy = false;
					levelEditor.setTile(levelEditor.getMap().getTiles()[e.getX()/levelEditor.getTileSize()][e.getY()/levelEditor.getTileSize()]);
				}
			} catch (ArrayIndexOutOfBoundsException ex){
				//don't do anything if user clicked out of bounds
			}

		}

		@Override
		public void mouseDragged(MouseEvent e) {
			mouseoverX = e.getX()/levelEditor.getTileSize();
			mouseoverY = e.getY()/levelEditor.getTileSize();
			try{
				if(levelEditor.cursor.equals("Tiles")){
					levelEditor.drawTile(e.getX()/levelEditor.getTileSize(), e.getY()/levelEditor.getTileSize());
				}
				else if(levelEditor.cursor.equals("Items")){
					if(levelEditor.getCurrentItem() != null){
						levelEditor.getMap().addMapItem(new MapItem(e.getX()/levelEditor.getTileSize(), e.getY()/levelEditor.getTileSize(), levelEditor.getCurrentItem()));
					}
					else {
						levelEditor.getMap().popMapItemAt(e.getX()/levelEditor.getTileSize(), e.getY()/levelEditor.getTileSize());
					}
				}
			} catch (ArrayIndexOutOfBoundsException ex){
				//don't do anything if user clicked out of bounds
			}
			repaint();
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			mouseoverX = e.getX()/levelEditor.getTileSize();
			mouseoverY = e.getY()/levelEditor.getTileSize();
			repaint();
		}

		public void mouseExited(MouseEvent e) {
			mouseoverX = -1;
			mouseoverY = -1;
			repaint();
		}

		public void mouseEntered(MouseEvent e) {}
		public void mousePressed(MouseEvent arg0) {}
		public void mouseReleased(MouseEvent arg0) {}
	}

}
