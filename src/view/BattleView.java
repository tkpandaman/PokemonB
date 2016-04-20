package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import model.Battle;
import model.BattleMenu;
import model.BattleMenu.MenuItem;
import model.Game;

public class BattleView extends JPanel implements Observer{
	
	private Image trainer, arrow;
	private BattleMenu menu;
	private Battle battle;

	public BattleView(Game game){
		
		try {
			trainer = ImageIO.read(new File("images/battle_trainer.png"));
			arrow = ImageIO.read(new File("images/arrow.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		menu = game.getBattleMenu();
		menu.addObserver(this);
		
		battle = game.getBattle();
	}
	
	public void setBattle(){
		//TODO set battle, yo
		//and load image
	}
	
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		
		g2.clearRect(0, 0, 100000, 100000);
		
		g2.drawImage(trainer, 400, 400, null);
		
		MenuItem[] items = menu.getItems();
		
		for (MenuItem item : items){
			g2.drawString(item.getText(), 400+50*item.getX(), 500+50*item.getY());
		}
		
		MenuItem currentItem = items[menu.getIndex()];
		
		g2.drawImage(arrow, 380+50*currentItem.getX(), 488+50*currentItem.getY(), null);
		
	}

	@Override
	public void update(Observable o, Object obj) {
		repaint();
	}
	
}
