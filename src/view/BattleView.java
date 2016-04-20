package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import model.BattleMenu;
import model.Game;

public class BattleView extends JPanel {
	
	private Image trainer;
	private BattleMenu menu;

	public BattleView(Game game){
		try {
			trainer = ImageIO.read(new File("images/battle_trainer.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//menu = game.getMenu();
	}
	
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		
		g2.clearRect(0, 0, 100000, 100000);
		
		g2.drawImage(trainer, 400, 400, null);
	}
	
}
