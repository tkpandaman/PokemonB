package editor_controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import model.LevelEditor;
import model.LevelEditor.EditorView;
import model.MapTile;
import model.Tileset;

// A JPanel which shows the current map and allows the user to draw on it.
public class MapPreviewPanel extends JPanel implements Observer {
	private static final long serialVersionUID = -2053840851750981450L;

	private LevelEditor levelEditor;
	
	private int mouseoverX = -1; //coords of tile that the mouse is currently hovering over
	private int mouseoverY = -1;

	public MapPreviewPanel(LevelEditor levelEditor){
		this.levelEditor = levelEditor;
		
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
		for(int x=0; x<map.length; x++){
			for(int y=0; y<map[0].length; y++){
				//draw the tile
				g2.drawImage(map[x][y].getImage(), x*Tileset.tileSize, y*Tileset.tileSize, null);
				
				if (levelEditor.getEditorView() == EditorView.Collisions){
					//show a red square if a tile is solid, and green if it isn't.
					if(map[x][y].isSolid())
						g2.setColor(new Color(1.0f, 0.0f, 0.0f, 0.3f));
					else
						g2.setColor(new Color(0.0f, 1.0f, 0.0f, 0.3f));
					g2.fillRect(x*Tileset.tileSize, y*Tileset.tileSize, Tileset.tileSize, Tileset.tileSize);
				}
				
				if (levelEditor.getEditorView() == EditorView.TileType){
					//show the abbreviation of each tile's TileType
					int size = Tileset.tileSize;
					g2.setColor(new Color(0.0f, 0.0f, 0.0f, 0.4f));
					g2.fillRect(x*Tileset.tileSize, y*Tileset.tileSize, Tileset.tileSize, Tileset.tileSize);
					g2.setColor(new Color(1.0f, 1.0f, 1.0f, 1));
					g2.drawString(map[x][y].getTileType().getAbbreviation(), x*size+size/2-4, y*size+size/2+4);
				}
			}
		}
		
		//draw preview
		BufferedImage ghostImage = levelEditor.getCurrentTile().getImage();
		g2.drawImage(ghostImage, mouseoverX*Tileset.tileSize, mouseoverY*Tileset.tileSize, null);

	}

	//Whenever the model changes, repaint.
	@Override
	public void update(Observable o, Object obj) {
		repaint();
	}
	
	// A class which responds to mouse events so you can draw tiles onto the level.
	private class MapMouseListener implements MouseListener, MouseMotionListener{
		private MapMouseListener(){
			
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			try{
				levelEditor.drawTile(e.getX()/Tileset.tileSize, e.getY()/Tileset.tileSize);
			} catch (ArrayIndexOutOfBoundsException ex){
				//don't do anything if user clicked out of bounds
			}
		}
		
		@Override
		public void mouseDragged(MouseEvent e) {
			mouseoverX = e.getX()/Tileset.tileSize;
			mouseoverY = e.getY()/Tileset.tileSize;
			try{
				levelEditor.drawTile(e.getX()/Tileset.tileSize, e.getY()/Tileset.tileSize);
			} catch (ArrayIndexOutOfBoundsException ex){
				//don't do anything if user clicked out of bounds
			}
			repaint();
		}
		
		@Override
		public void mouseMoved(MouseEvent e) {
			mouseoverX = e.getX()/Tileset.tileSize;
			mouseoverY = e.getY()/Tileset.tileSize;
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
