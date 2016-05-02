package editor_controller;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import model.Tileset;

// A JPanel which allows the user to select a tile to draw.
public class TileSelectionPanel extends JPanel implements Observer{
	private static final long serialVersionUID = 2282569307358593724L;

	private LevelEditor levelEditor;
	
	private static int tileSpacing = 4;

	public TileSelectionPanel(LevelEditor levelEditor){

		this.levelEditor = levelEditor;
		
		this.setLayout(null);
		this.setFocusable(false);
		
		setupLayout();
		
	}
	
	private void setupLayout(){
		this.removeAll();
		
		//load tileset image
		Tileset tileset = levelEditor.getTileset();
		
		//create a button for each image
		int tileSize = tileset.getTileSize();
		JButton[] tileButtons = new JButton[(tileset.getWidth()/tileSize)*(tileset.getHeight()/tileSize)];
		int i = 0;
		for(int y=0; y<tileset.getHeight()/tileSize; y++){
			for(int x=0; x<tileset.getWidth()/tileSize; x++){
				BufferedImage tile = tileset.tileAt(x, y);
				tileButtons[i] = new JButton(new ImageIcon(tile));
				tileButtons[i].setFocusable(false);
				//add listener
				tileButtons[i].addActionListener(new TileSelectionListener(x, y));
				//set size/position
				tileButtons[i].setSize(tileSize+3, tileSize+3);
				tileButtons[i].setLocation(tileSpacing+x*(tileSize+tileSpacing), tileSpacing+y*(tileSize+tileSpacing));
				this.add(tileButtons[i]);
				i++;
			}
		}
		
		// Set size of panel based on number of tiles in tileset
		int w = tileSpacing+tileset.getWidth()/tileSize*(tileSize+tileSpacing);
		int h = tileSpacing+tileset.getHeight()/tileSize*(tileSize+tileSpacing);
		this.setPreferredSize(new Dimension(w, h));
	}
	
	// An ActionListener which responds to a button being clicked and tells
	// the LevelEditor to switch to the corresponding tile.
	private class TileSelectionListener implements ActionListener{

		int posX, posY;
		
		public TileSelectionListener(int x, int y){
			posX = x; posY = y;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			levelEditor.setTileImage(posX, posY);
		}
		
	}

	@Override
	public void update(Observable model, Object obj) {
		// if the tileset has changed, update the layout.
		if (obj == LevelEditor.UpdateType.TilesetChanged){
			setupLayout();
			//repaint();
		}
	}
	
}
