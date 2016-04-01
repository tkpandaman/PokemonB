package editor_controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import model.LevelEditor;
import model.TileType;

// A JPanel which allows the user to set the properties of the current tile.
public class TilePropertiesPanel extends JPanel implements Observer {
	private static final long serialVersionUID = -5091951983192087240L;
	
	private LevelEditor levelEditor;
	private JLabel tileIcon;
	
	private ArrayList<TileTypeSelectionListener> selectionListeners;
	
	public TilePropertiesPanel(LevelEditor levelEditor){
		this.levelEditor = levelEditor;
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		this.add(Box.createVerticalStrut(15));
		
		//add tile preview image
		BufferedImage tileImg = levelEditor.getCurrentTile().getImage();
		tileIcon = new JLabel(new ImageIcon(tileImg));
		this.add(tileIcon);
		
		this.add(Box.createVerticalStrut(15));
		
		ButtonGroup tileTypeSelection = new ButtonGroup();
		
		selectionListeners = new ArrayList<TileTypeSelectionListener>();
		for(TileType type : TileType.values()){
			//add a radio button for each value in TileType enum
			JRadioButton radioButton = new JRadioButton(type.toString());
			TileTypeSelectionListener listener = new TileTypeSelectionListener(type, radioButton);
			radioButton.addActionListener(listener);
			selectionListeners.add(listener);
			tileTypeSelection.add(radioButton);
			this.add(radioButton);
			//set the correct radio button to be selected by default
			if(levelEditor.getCurrentTile().getTileType() == type){
				radioButton.setSelected(true);
			}
		}
	}

	@Override
	public void update(Observable o, Object obj) {
		//update current tile
		BufferedImage tileImg = levelEditor.getCurrentTile().getImage();
		tileIcon.setIcon(new ImageIcon(tileImg));
		//update type selection radio buttons
		for(TileTypeSelectionListener listener : selectionListeners){
			if(listener.getType() == levelEditor.getCurrentTile().getTileType()){
				listener.getButton().setSelected(true);
			}
		}
	}
	
	// An ActionListener which responds to a TileType being selected and sets the current TileType to that.
	private class TileTypeSelectionListener implements ActionListener{
		
		private TileType type;
		private JRadioButton button;
		
		public TileTypeSelectionListener(TileType type, JRadioButton button){
			this.type = type;
			this.button = button;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			levelEditor.setTileType(type);
		}
		
		public TileType getType(){
			return type;
		}
		
		public JRadioButton getButton(){
			return button;
		}
		
	}
	
}
