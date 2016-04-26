package editor_controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.shape.Box;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import controller.GameGUI;

//Panel used for adding Items to the Map
public class MapItemPanel extends JPanel {
	
	private LevelEditor level;
	
	private ButtonGroup itemGroup;
	
	private JRadioButton shoesItem;
	private JRadioButton pokeBallItem;
	private JRadioButton potionItem;
	
	private HashMap<String, BufferedImage> itemImages;
	
	private BufferedImage defaultImage;
	
	private JLabel itemPreview;
	
	public MapItemPanel(LevelEditor level){
		
		this.level = level;

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		this.setBackground(Color.ORANGE);
		
		itemImages = MapItemPanel.loadItemImages();
		defaultImage = itemImages.get("Pokeball");
		level.setCurrentItemImage(defaultImage);
		
		
		itemPreview = new JLabel(new ImageIcon(defaultImage));
		
		this.add(itemPreview);		
	}
	
	public BufferedImage getDefaultImage(){
		return defaultImage;
	}
	
	//This is where new Item images need to be mapped to file names
	public static HashMap<String, BufferedImage> loadItemImages(){
		HashMap<String, BufferedImage> itemImages = new HashMap<String, BufferedImage>();
		ArrayList<File> itemFiles = GameGUI.itemImageFiles();
		try{
			for(File f : itemFiles){
				if (f.getName().equals("pokemon_item_safariBall.png")){
					itemImages.put("Pokeball", ImageIO.read(f));
				}
				else if (f.getName().equals("pokemon_item_shoes.png")){
					itemImages.put("Running Shoes", ImageIO.read(f));
				}
				else if (f.getName().equals("pokemon_item_healthPot.png")){
					itemImages.put("Potion", ImageIO.read(f));
				}
			}
		}
		catch(IOException e){
			System.err.println("Couldn't read image file into a BufferedImage");
		}
		return itemImages;

	}
	

}
