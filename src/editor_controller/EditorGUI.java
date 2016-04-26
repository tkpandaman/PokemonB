package editor_controller;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class EditorGUI extends JFrame {
	private static final long serialVersionUID = 2084081246604831895L;
	
	private JTabbedPane propertiesPane;

	public static void main(String[] args) {
		
		EditorGUI gui = new EditorGUI();
		gui.setVisible(true);

	}
	
	public EditorGUI(){
		//set window properties
		this.setTitle("Pokemon Level Editor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1024, 768);
		//this.setLayout(new FlowLayout());
		
		//level editor = model
		LevelEditor levelEditor = new LevelEditor("emerald-x2", 32);
		
		//map preview: shows the map, lets you draw on it
		MapPreviewPanel mapPreview = new MapPreviewPanel(levelEditor);
		mapPreview.setPreferredSize(new Dimension(levelEditor.getMapPixelWidth(), levelEditor.getMapPixelHeight()));
		JScrollPane mapScrollPane = new JScrollPane(mapPreview);
		
		levelEditor.addObserver(mapPreview);
		
		//tile selection: lets you choose a tile to draw from the tileset
		TileSelectionPanel tileSelection = new TileSelectionPanel(levelEditor);
		//add it to a JScrollPane
		JScrollPane tileScrollPane = new JScrollPane(tileSelection);
		tileScrollPane.setPreferredSize(new Dimension(800, 250));
		
		levelEditor.addObserver(tileSelection);
		
		//tile properties: lets you choose the properties (solidity, random encounter chance) of the tile
		TilePropertiesPanel tileProperties = new TilePropertiesPanel(levelEditor);
		MapPropertiesPanel mapProperties = new MapPropertiesPanel(levelEditor);
		MapItemPanel mapItems = new MapItemPanel(levelEditor);
		
		propertiesPane = new JTabbedPane();
		propertiesPane.add("Tiles", tileProperties);
		propertiesPane.add("Items", mapItems);
		
		//Change the cursor type to display a ghost image of the Item being placed
		propertiesPane.addChangeListener(new ChangeListener()
	    {
			@Override
			public void stateChanged(ChangeEvent e) {
				if(propertiesPane.getSelectedIndex() == 1){
					levelEditor.cursor = "Items";
					levelEditor.setCurrentItemImage(mapItems.getDefaultImage());
				}
				else if(propertiesPane.getSelectedIndex() == 0){
					levelEditor.cursor = "Tiles";
				}
				
			}
		    });
		
		levelEditor.addObserver(tileProperties);
		levelEditor.addObserver(mapProperties);
		
		JMenuBar menuBar = new MenuBar(levelEditor);
		setJMenuBar(menuBar);
		
		JPanel mainPanel = new JPanel();
		JPanel rightPanel = new JPanel();
		
		rightPanel.setLayout(new BorderLayout());
		mainPanel.setLayout(new BorderLayout());
		
		rightPanel.add(propertiesPane, BorderLayout.PAGE_START);
		rightPanel.add(mapProperties, BorderLayout.PAGE_END);
		mainPanel.add(rightPanel, BorderLayout.EAST);
		mainPanel.add(mapScrollPane, BorderLayout.CENTER);
		//mainPanel.add(menuBar, BorderLayout.NORTH);
		
		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, mainPanel, tileScrollPane);
		splitPane.setDividerLocation(550);
		
		this.add(splitPane);
		
	}
	

}
