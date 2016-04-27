package editor_controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import model.Pokeball;
import model.Potion;
import model.RunningShoes;
import controller.GameGUI;

//Panel used for adding Items to the Map
public class MapItemPanel extends JPanel {

	private LevelEditor level;

	private ButtonGroup itemGroup;

	private JRadioButton shoesItem;
	private JRadioButton pokeBallItem;
	private JRadioButton potionItem;
	private JRadioButton deleteItem;

	private HashMap<String, BufferedImage> itemImages;

	private BufferedImage defaultImage;

	private JLabel itemPreview;

	public MapItemPanel(LevelEditor level){

		this.level = level;
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBackground(Color.ORANGE);

		itemImages = GameGUI.loadItemImages();
		defaultImage = itemImages.get("Pokeball");
		itemPreview = new JLabel(new ImageIcon(defaultImage));

		//Initialize buttons
		pokeBallItem = new JRadioButton("Safari Ball");
		pokeBallItem.setActionCommand("pokeBall");
		pokeBallItem.setSelected(true);

		shoesItem = new JRadioButton("Speedy Shoes");
		shoesItem.setActionCommand("speedShoes");

		potionItem = new JRadioButton("Health Potion");
		potionItem.setActionCommand("health");

		deleteItem = new JRadioButton("Delete Items");
		deleteItem.setActionCommand("delete");

		//Add them to a group
		itemGroup = new ButtonGroup();
		itemGroup.add(pokeBallItem);
		itemGroup.add(shoesItem);
		itemGroup.add(potionItem);
		itemGroup.add(deleteItem);

		//Register a listener for each of the buttons
		pokeBallItem.addActionListener(new buttonListener());
		shoesItem.addActionListener(new buttonListener());
		potionItem.addActionListener(new buttonListener());
		deleteItem.addActionListener(new buttonListener());

		//Add components to this panel
		this.add(Box.createVerticalStrut(15));
		this.add(itemPreview);

		this.add(Box.createVerticalStrut(15));
		this.add(pokeBallItem);
		this.add(shoesItem);
		this.add(potionItem);
		this.add(deleteItem);

	}

	private class buttonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			switch(e.getActionCommand()){
			case "pokeBall": level.setCurrentItem(new Pokeball(new Random()));
			break;
			case "speedShoes": level.setCurrentItem(new RunningShoes());
			break;
			case "health": level.setCurrentItem(new Potion());
			break;
			case "delete": level.setCurrentItem(null);
			break;
			default: level.setCurrentItem(null);
			break;
			}

			if(level.getCurrentItem() != null){
				itemPreview.setIcon(new ImageIcon(itemImages.get(level.getCurrentItem().getName())));
			}
			else{
				itemPreview.setIcon(new ImageIcon());
			}
		}

	}

}
