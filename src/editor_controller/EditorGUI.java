package editor_controller;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

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
		
		levelEditor.addObserver(tileProperties);
		levelEditor.addObserver(mapProperties);
		
		JMenuBar menuBar = new MenuBar(levelEditor);
		setJMenuBar(menuBar);
		
		JPanel mainPanel = new JPanel();
		JPanel rightPanel = new JPanel();
		
		rightPanel.setLayout(new BorderLayout());
		mainPanel.setLayout(new BorderLayout());
		
		rightPanel.add(tileProperties, BorderLayout.PAGE_START);
		rightPanel.add(mapProperties, BorderLayout.PAGE_END);
		mainPanel.add(rightPanel, BorderLayout.EAST);
		mainPanel.add(mapScrollPane, BorderLayout.CENTER);
		//mainPanel.add(menuBar, BorderLayout.NORTH);
		
		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, mainPanel, tileScrollPane);
		splitPane.setDividerLocation(550);
		
		this.add(splitPane);
		
	}
	

}
