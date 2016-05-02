package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import model.IntroScreenModel;

public class IntroScreenView extends JPanel implements Observer{
	private static final long serialVersionUID = -5028534985506854419L;
	private int selected;
	private int numMenuItems;
	private IntroScreenModel introModel;
	private Image arrow;
	private Image startScreen;
	private Image mainMenuScreen;
	private Timer timer;

	private JPanel menuPanel;

	private boolean displayIcon;
	
	private static final int BLINK_DELAY = 500;
	
	private static final int ARROW_X_OFFSET = -20;
	private static final int ARROW_Y_OFFSET = -13;

	private static final int MENU_X_OFFSET_DEFAULT = -40;
	private static final int MENU_Y_OFFSET_DEFAULT = -100;

	private int MENU_X_OFFSET = -40;
	private int MENU_Y_OFFSET = 0;

	private static final String NEW_GAME_STR = "Choose a win condition:";
	private static final String LOAD_FAIL_STR = "No save game was detected";

	public IntroScreenView(IntroScreenModel introModel){
		this.introModel = introModel;
		numMenuItems = 0;
		selected = 0;
		displayIcon = true;
		timer = new Timer(BLINK_DELAY, new BlinkIconListener());
		timer.start();
		layoutPanel();
	}

	private void layoutPanel(){
		this.setSize(1024, 720);
		//this.setLayout(new BoxLayout(this, numMenuItems));
		this.setBackground(Color.WHITE);

		menuPanel = new JPanel();

		try
		{
			arrow = ImageIO.read( new File( "images/arrow.png" ) );
			startScreen = ImageIO.read( new File( "images/mainMenu/StartScreen.jpg" ) );
			mainMenuScreen = ImageIO.read( new File( "images/mainMenu/MainMenu.png" ) );
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;

		//Draw Background
		if(this.introModel.getState() == IntroScreenModel.IntroScreenState.Start){
			g2.drawImage(startScreen, 0, 0, null);
		}
		else{
			g2.drawImage(mainMenuScreen, 0, 0, null);
		}

		//g2.setColor(Color.WHITE);

		this.MENU_X_OFFSET = MENU_X_OFFSET_DEFAULT;
		this.MENU_Y_OFFSET = MENU_Y_OFFSET_DEFAULT;

//		if(this.introModel.getState() != IntroScreenModel.IntroScreenState.Start){
//			g2.setColor(Color.WHITE);
//			int boxSize = 250;
//			int boxX = 10;
//			int boxY = 30;
//			g2.fillRect(this.getWidth()/2 - boxSize/2 + boxX, this.getHeight()/2 - boxSize/2 + boxY, boxSize, boxSize);
//		}

		g2.setColor(Color.BLACK);
		

		switch(this.introModel.getState()){
		case Start:
			this.MENU_Y_OFFSET = 200;
			break;
		case NewGame:
			g2.drawString(NEW_GAME_STR, this.getWidth()/2 + MENU_X_OFFSET - 30, this.getHeight()/2 + MENU_Y_OFFSET - 30 );
			break;
		case LoadFail:
			g2.drawString(LOAD_FAIL_STR, this.getWidth()/2 + MENU_X_OFFSET - 30, this.getHeight()/2 + MENU_Y_OFFSET - 30 );
			break;
		case Credits:
			int gameByX = -150;
			int gameByY = -150;
			int musicByX = 80;
			int musicByY = -150;
			g2.drawString("Game By:", this.getWidth()/2 + MENU_X_OFFSET + gameByX - 20, this.getHeight()/2 + MENU_Y_OFFSET + gameByY );
			g2.drawString("Alex Katzfey", this.getWidth()/2 + MENU_X_OFFSET + gameByX, this.getHeight()/2 + MENU_Y_OFFSET + gameByY + 30 );
			g2.drawString("Joshua Rafael", this.getWidth()/2 + MENU_X_OFFSET+ gameByX, this.getHeight()/2 + MENU_Y_OFFSET + gameByY+ 50 );
			g2.drawString("Peter Chipman", this.getWidth()/2 + MENU_X_OFFSET+ gameByX, this.getHeight()/2 + MENU_Y_OFFSET + gameByY+ 70 );
			g2.drawString("Jake Deichmann", this.getWidth()/2 + MENU_X_OFFSET+ gameByX, this.getHeight()/2 + MENU_Y_OFFSET + gameByY+ 90 );
			g2.drawString("Music By:", this.getWidth()/2 + MENU_X_OFFSET + musicByX -20, this.getHeight()/2 + MENU_Y_OFFSET + musicByY );
			g2.drawString("Alex Katzfey", this.getWidth()/2 + MENU_X_OFFSET + musicByX, this.getHeight()/2 + MENU_Y_OFFSET + musicByY + 30 );
			break;
		default:
			break;
		}

		//Draw Selection Arrow
		if(introModel.getMenuOptions().length > 0){
			g2.drawImage( arrow, this.getWidth()/2 + ARROW_X_OFFSET + MENU_X_OFFSET, 
					this.getHeight()/2 + ( selected * 30 ) + ARROW_Y_OFFSET + MENU_Y_OFFSET, null );
		}

		//Draw Menu Items
		if(displayIcon){
			int count = 0;
			for( String item : introModel.getMenuOptions() ){
				setFont( new Font( "Serif", Font.BOLD, 20 ) );
				g2.drawString(item, this.getWidth()/2 + MENU_X_OFFSET, this.getHeight()/2 + (count*30) + MENU_Y_OFFSET);
				count++;
			}
		}


	}

	@Override
	public void update(Observable o, Object arg) {
		this.introModel = (IntroScreenModel) o;
		this.selected = this.introModel.getSelected();
		if(this.introModel.getState() == IntroScreenModel.IntroScreenState.Start && !timer.isRunning()){
			timer.start();
		}
		else if(this.introModel.getState() != IntroScreenModel.IntroScreenState.Start && timer.isRunning()){
			timer.stop();
			displayIcon = true;
		}
		//layoutPanel();
		this.repaint();

	}
	

	private class BlinkIconListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(displayIcon) displayIcon = false;
			else displayIcon = true;
			repaint();
			
		}
		
	}


}