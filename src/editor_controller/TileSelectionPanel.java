package editor_controller;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import model.LevelEditor;
import model.Tileset;

// A JPanel which allows the user to select a tile to draw.
public class TileSelectionPanel extends JPanel{
	private static final long serialVersionUID = 2282569307358593724L;

	private LevelEditor levelEditor;
	
	private static int tileSpacing = 4;

	public TileSelectionPanel(LevelEditor levelEditor){

		this.levelEditor = levelEditor;
		
		this.setLayout(null);
		
		//load tileset image
		Tileset tileset = Tileset.getInstance();
		
		//create a button for each image
		JButton[] tileButtons = new JButton[(tileset.getWidth()/Tileset.tileSize)*(tileset.getHeight()/Tileset.tileSize)];
		int i = 0;
		for(int y=0; y<tileset.getHeight()/Tileset.tileSize; y++){
			for(int x=0; x<tileset.getWidth()/Tileset.tileSize; x++){
				BufferedImage tile = tileset.tileAt(x, y);
				tileButtons[i] = new JButton(new ImageIcon(tile));
				//add listener
				tileButtons[i].addActionListener(new TileSelectionListener(x, y));
				//set size/position
				tileButtons[i].setSize(Tileset.tileSize+3, Tileset.tileSize+3);
				tileButtons[i].setLocation(tileSpacing+x*(Tileset.tileSize+tileSpacing), tileSpacing+y*(Tileset.tileSize+tileSpacing));
				this.add(tileButtons[i]);
				i++;
			}
		}
		
		// Set size of panel based on number of tiles in tileset
		int w = tileSpacing+tileset.getWidth()/Tileset.tileSize*(Tileset.tileSize+tileSpacing);
		int h = tileSpacing+tileset.getHeight()/Tileset.tileSize*(Tileset.tileSize+tileSpacing);
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
	
}
