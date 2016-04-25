package editor_controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

import editor_controller.LevelEditor.EditorView;

// The menu bar.
public class MenuBar extends JMenuBar {
	private static final long serialVersionUID = 3433014390954340108L;

	private LevelEditor levelEditor;

	public MenuBar(LevelEditor levelEditor){
		this.levelEditor = levelEditor;

		JMenu fileMenu = new JMenu("File");
		JMenuItem newLevel = new JMenuItem("New level");
		newLevel.addActionListener(new NewLevelButtonListener());
		fileMenu.add(newLevel);
		JMenuItem loadLevel = new JMenuItem("Load level");
		loadLevel.addActionListener(new LoadButtonListener());
		fileMenu.add(loadLevel);
		JMenuItem saveLevel = new JMenuItem("Save level");
		saveLevel.addActionListener(new SaveButtonListener());
		fileMenu.add(saveLevel);
		fileMenu.addSeparator();
		JMenuItem saveTileDefaults = new JMenuItem("Save tile defaults");
		fileMenu.add(saveTileDefaults);
		saveTileDefaults.addActionListener(new SaveTileDefaultsListener());
		this.add(fileMenu);

		JMenu levelMenu = new JMenu("Level");
		JMenuItem resizeLevel = new JMenuItem("Resize level"); //TODO action listeners
		levelMenu.add(resizeLevel);
		this.add(levelMenu);

		JMenu viewMenu = new JMenu("View");
		ButtonGroup viewGroup = new ButtonGroup();

		JRadioButtonMenuItem normalView = new JRadioButtonMenuItem("Normal view");
		normalView.addActionListener(new EditorViewListener(EditorView.Normal));
		if(levelEditor.getEditorView() == EditorView.Normal) normalView.setSelected(true);
		viewGroup.add(normalView);
		viewMenu.add(normalView);

		JRadioButtonMenuItem collisionView = new JRadioButtonMenuItem("Show collisions");
		collisionView.addActionListener(new EditorViewListener(EditorView.Collisions));
		if(levelEditor.getEditorView() == EditorView.Collisions) collisionView.setSelected(true);
		viewGroup.add(collisionView);
		viewMenu.add(collisionView);

		JRadioButtonMenuItem tileTypeView = new JRadioButtonMenuItem("Show tile type");
		tileTypeView.addActionListener(new EditorViewListener(EditorView.TileType));
		if(levelEditor.getEditorView() == EditorView.TileType) tileTypeView.setSelected(true);
		viewGroup.add(tileTypeView);
		viewMenu.add(tileTypeView);

		this.add(viewMenu);
	}

	// An ActionListener which creates a new level.
	private class NewLevelButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			levelEditor.newLevel();
		}
	}

	// An ActionListener which prompts the user to save the current level.
	private class SaveButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {

			if(levelEditor.getMap().getCurMap() != null){
				File f = new File("levels/" + levelEditor.getMap().getCurMap());
				levelEditor.saveLevel(f);
				System.out.println("Level Saved!");
			}
			
			//Sorry don't need this anymore lol
			//			final JFileChooser fc = new JFileChooser();
			//			Path currentRelativePath = Paths.get("");
			//			File currentPath = currentRelativePath.toAbsolutePath().toFile();
			//			fc.setCurrentDirectory(currentPath);
			//			int returnVal = fc.showSaveDialog(null);
			//			if (returnVal == JFileChooser.APPROVE_OPTION) {
			//				levelEditor.saveLevel(fc.getSelectedFile());
			//			}
			
		}
	}

	// An ActionListener which prompts the user to load a level.
	private class LoadButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			final JFileChooser fc = new JFileChooser();
			Path currentRelativePath = Paths.get("");
			File currentPath = currentRelativePath.toAbsolutePath().toFile();
			fc.setCurrentDirectory(currentPath);
			int returnVal = fc.showOpenDialog(null);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				levelEditor.loadLevel(fc.getSelectedFile());
			}
		}
	}

	// An ActionListener which saves the user's current TileType settings for each tile
	// to a file, to be automatically loaded when the program is next launched.
	private class SaveTileDefaultsListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			levelEditor.getTileset().saveTileDefaults();
		}
	}

	// An ActionListener which switches the view in the MapPreviewPanel.
	private class EditorViewListener implements ActionListener{
		EditorView view;
		public EditorViewListener(EditorView view){
			this.view = view;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			levelEditor.setEditorView(view);
		}
	}

}
