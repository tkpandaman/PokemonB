package editor_controller;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import model.LevelEditor;

public class EditorGUI extends JFrame {
	private static final long serialVersionUID = 2084081246604831895L;

	public static void main(String[] args) {
		
		EditorGUI gui = new EditorGUI();
		gui.setVisible(true);

	}
	
	public EditorGUI(){
		//set window properties
		this.setTitle("Pokemon Level Editor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1024, 768);
		
		//level editor = model
		LevelEditor levelEditor = new LevelEditor("tileset-x2", 32);
		
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
		
		levelEditor.addObserver(tileProperties);
		
		JMenuBar menuBar = new MenuBar(levelEditor);
		setJMenuBar(menuBar);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(mapScrollPane, BorderLayout.CENTER);
		//mainPanel.add(menuBar, BorderLayout.NORTH);
		mainPanel.add(tileProperties, BorderLayout.EAST);
		
		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, mainPanel, tileScrollPane);
		splitPane.setDividerLocation(550);
		
		this.add(splitPane);
		
	}

}
