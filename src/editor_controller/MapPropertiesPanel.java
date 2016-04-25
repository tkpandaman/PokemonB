package editor_controller;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.GameGUI;
import model.Map;

//Allows the user to set the adjacent maps
public class MapPropertiesPanel extends JPanel implements Observer {

	private LevelEditor level;
	private JComboBox<String> upBox;
	private JComboBox<String> downBox;
	private JComboBox<String> rightBox;
	private JComboBox<String> leftBox;
	
	private JLabel curMapLabel;
	private JTextField curBox;

	public MapPropertiesPanel(LevelEditor level){

		this.level = level;

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		this.setBackground(Color.GREEN);

		//Adding combo boxes to allow user to set the maps above, below, etc.
		//Used mainly for transitions	
		ArrayList<File> files = GameGUI.mapFiles();
		String[] names = new String[files.size()];
		for(int i = 0; i < files.size(); i++){
			names[i] = files.get(i).getName();
		}

		upBox = new JComboBox<String>(names);
		downBox = new JComboBox<String>(names);
		rightBox = new JComboBox<String>(names);
		leftBox = new JComboBox<String>(names);
		curBox = new JTextField("");

		upBox.setName("up");
		downBox.setName("down");
		rightBox.setName("right");
		leftBox.setName("left");
		curBox.setName("cur");

		upBox.addActionListener(new comboListener());
		downBox.addActionListener(new comboListener());
		rightBox.addActionListener(new comboListener());
		leftBox.addActionListener(new comboListener());
		curBox.addActionListener(new textFieldListener());


		JLabel title = new JLabel("Current Map Name:");
		curMapLabel = new JLabel("");
	
		JLabel title2 = new JLabel("Adjacent Maps:");
		JLabel upTxt = new JLabel("Map above:");
		JLabel belowTxt = new JLabel("Map below:");
		JLabel leftTxt = new JLabel("Map left:");
		JLabel rightTxt = new JLabel("Map right:");

		initializeBoxValues();

		title.setAlignmentX(CENTER_ALIGNMENT);
		curMapLabel.setAlignmentX(CENTER_ALIGNMENT);
		title2.setAlignmentX(CENTER_ALIGNMENT);

		this.add(title);
		this.add(curMapLabel);
		this.add(curBox);
		this.add(Box.createVerticalStrut(30));

		this.add(title2);
		this.add(Box.createVerticalStrut(15));

		this.add(upTxt);
		this.add(upBox);
		this.add(Box.createVerticalStrut(15));

		this.add(belowTxt);
		this.add(downBox);
		this.add(Box.createVerticalStrut(15));

		this.add(leftTxt);
		this.add(leftBox);
		this.add(Box.createVerticalStrut(15));

		this.add(rightTxt);
		this.add(rightBox);

	}

	private void initializeBoxValues(){
		curMapLabel.setText(level.getMap().getCurMap());
		upBox.setSelectedItem(level.getMap().getUpMap());
		downBox.setSelectedItem(level.getMap().getDownMap());
		leftBox.setSelectedItem(level.getMap().getLeftMap());
		rightBox.setSelectedItem(level.getMap().getRightMap());
	}

	@Override
	public void update(Observable observedObject, Object arg) {
		level = (LevelEditor) observedObject;
		initializeBoxValues();
	}

	//Used to set the map values for adjacent maps
	private class comboListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(((JComboBox) e.getSource()).getName() == "up"){
				MapPropertiesPanel.this.level.getMap().setUpMap((String)((JComboBox) e.getSource()).getSelectedItem());
			}
			else if(((JComboBox) e.getSource()).getName() == "down"){
				MapPropertiesPanel.this.level.getMap().setDownMap((String)((JComboBox) e.getSource()).getSelectedItem());
			}
			else if(((JComboBox) e.getSource()).getName() == "left"){
				MapPropertiesPanel.this.level.getMap().setLeftMap((String)((JComboBox) e.getSource()).getSelectedItem());
			}
			else if(((JComboBox) e.getSource()).getName() == "right"){
				MapPropertiesPanel.this.level.getMap().setRightMap((String)((JComboBox) e.getSource()).getSelectedItem());
			}

		}

	}

	private class textFieldListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			MapPropertiesPanel.this.level.getMap().setCurMap((String)((JTextField) e.getSource()).getText());
			MapPropertiesPanel.this.initializeBoxValues();
			curBox.setText("");
		}

	}

}
