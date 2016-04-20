package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import model.Battle;
import model.BattleMenu;
import model.BattleMenu.MenuItem;
import model.pokemon.*;
import model.Game;

public class BattleView extends JPanel implements Observer{
	
	private Image trainer, arrow, pokemonImg;
	private BattleMenu menu;
	private Battle battle;
	
	HashMap<Class<? extends Pokemon>, String> fileNameMap = new HashMap<>();

	public BattleView(Game game){
		
		try {
			trainer = ImageIO.read(new File("images/battle_trainer.png"));
			arrow = ImageIO.read(new File("images/arrow.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		menu = game.getBattleMenu();
		menu.addObserver(this);
		
        fileNameMap.put(Arbok.class, "images/pokemon/Arbok.png");
        fileNameMap.put(Beedrill.class, "images/pokemon/Beedrill.png");
        fileNameMap.put(Butterfree.class, "images/pokemon/Butterfree.png");
        fileNameMap.put(Charizard.class, "images/pokemon/Charizard.png");
        fileNameMap.put(Pidgeot.class, "images/pokemon/Pidgeot.png");
        fileNameMap.put(Pikachu.class, "images/pokemon/Pikachu.png");
        fileNameMap.put(Snorlax.class, "images/pokemon/Snorlax.png");
        fileNameMap.put(Spearow.class, "images/pokemon/Spearow.png");
        fileNameMap.put(Squirtle.class, "images/pokemon/Squirtle.png");
        fileNameMap.put(Voltorb.class, "images/pokemon/Voltorb.png");
		
		battle = game.getBattle();
	}
	
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		
		g2.clearRect(0, 0, 100000, 100000);
		
		g2.drawImage(trainer, 400, 400, null);
		
		g2.drawImage(pokemonImg, 500, 300, pokemonImg.getWidth(null)/4, pokemonImg.getHeight(null)/4, null);
		
		MenuItem[] items = menu.getItems();
		
		for (MenuItem item : items){
			g2.drawString(item.getText(), 400+80*item.getX(), 500+50*item.getY());
		}
		
		MenuItem currentItem = items[menu.getIndex()];
		
		g2.drawImage(arrow, 380+80*currentItem.getX(), 488+50*currentItem.getY(), null);
		
		g2.drawString(menu.getText(), 400, 600);
		
	}

	@Override
	public void update(Observable o, Object obj) {
		if (obj != null){
			if ((int)obj == 1){
				//load pokemon image
				String filename = fileNameMap.get(menu.getBattle().getPokemon().getClass());
				try {
					pokemonImg = ImageIO.read(new File(filename));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		repaint();
	}
	
}
