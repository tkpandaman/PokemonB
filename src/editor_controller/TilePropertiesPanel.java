package editor_controller;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javafx.scene.input.KeyCode;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import model.Map;
import model.MapTile;
import model.TileType;

// A JPanel which allows the user to set the properties of the current tile.
public class TilePropertiesPanel extends JPanel implements Observer{

	private LevelEditor levelEditor;
	private JLabel tileIcon;
	private JCheckBox copy;

	private ArrayList<TileTypeSelectionListener> selectionListeners;

	public TilePropertiesPanel(LevelEditor levelEditor){
		this.levelEditor = levelEditor;

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		this.add(Box.createVerticalStrut(15));

		//this.setPreferredSize(new Dimension(200, 100));

		//add tile preview image
		MapTile currentTile = levelEditor.getCurrentTile();
		BufferedImage tileImg = levelEditor.getTileset().tileAt(currentTile.getTilesetX(), currentTile.getTilesetY());
		tileIcon = new JLabel(new ImageIcon(tileImg));
		this.add(tileIcon);

		this.add(Box.createVerticalStrut(15));

		ButtonGroup tileTypeSelection = new ButtonGroup();

		selectionListeners = new ArrayList<TileTypeSelectionListener>();
		for(TileType type : TileType.values()){
			//add a radio button for each value in TileType enum
			JRadioButton radioButton = new JRadioButton(type.toString());
			radioButton.setFocusable(false);
			TileTypeSelectionListener listener = new TileTypeSelectionListener(type, radioButton);
			radioButton.addActionListener(listener);
			selectionListeners.add(listener);
			tileTypeSelection.add(radioButton);
			this.add(radioButton, BorderLayout.LINE_START);
			//set the correct radio button to be selected by default
			if(levelEditor.getCurrentTile().getTileType() == type){
				radioButton.setSelected(true);
			}
		}

		copy = new JCheckBox();
		copy.setSelected(false);
		copy.setText("Copy Selected Tile");

		copy.addActionListener(new copyButtonListener());

		this.add(copy, BorderLayout.LINE_START);


	}

	@Override
	public void update(Observable model, Object obj) {
		//update current tile
		MapTile currentTile = levelEditor.getCurrentTile();
		BufferedImage tileImg = levelEditor.getTileset().tileAt(currentTile.getTilesetX(), currentTile.getTilesetY());
		tileIcon.setIcon(new ImageIcon(tileImg));
		//update type selection radio buttons
		for(TileTypeSelectionListener listener : selectionListeners){
			if(listener.getType() == levelEditor.getCurrentTile().getTileType()){
				listener.getButton().setSelected(true);
			}
		}
		copy.setSelected(levelEditor.copy);
	}

	//Determines if copy button is selected or not, and updates the levelEditor
	private class copyButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {

			levelEditor.copy = copy.isSelected();

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
