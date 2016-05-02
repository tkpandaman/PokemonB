package view;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JPanel;
import javax.swing.Timer;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import model.Battle;
import model.BattleMenu;
import model.BattleMenu.MenuItem;
import model.pokemon.*;
import model.Game;
import model.BattleAction;

public class BattleView extends JPanel implements Observer{
	
    private static final long serialVersionUID = 2777359359845616783L;
    private Image trainer, trainerThrow, arrow, pokemonImg, background, pokeball, rock, bait;
	private BattleMenu menu;
	private Battle battle;
	private int animX = -1;
	private int animY = -1;
	private int endX = -1;
	private Timer timer;
	private boolean isAnimating = false;
	private double healthPerc = 1;
	
	//Sounds Effects
	private MediaPlayer mediaPlayer;
	private static final String SONG_ONE = Paths.get("audio/battleSounds/runsAway.mp3").toUri().toString();
	private JFXPanel fxPanel;
	
	// Timer Variables
	private int transitionRectangleAlpha;
	private static final int MAX_ALPHA = 255;
	private static final int MIN_ALPHA = 0;
	private Timer fadeInTimer;
	private Timer fadeOutTimer;
	private static final int DURATION = 50;
	private static final int DELTA = 5;
	
	//Image Variables
	private static final String BATTLEBG = "images/battle/battleBG.png";
	private static final String TRAINERIMG = "images/battle/trainer.png";
	private static final String TRAINERTHROW = "images/battle/trainerThrow.png";
	private static final String POKEBALL = "images/battle/pokeball.png";
	private static final String ROCK = "images/battle/rock.png";
	private static final String BAIT = "images/battle/bait.png";
	private static final String ARROW = "images/arrow.png";
	
	HashMap<Class<? extends Pokemon>, String> fileNameMap = new HashMap<>();

	public BattleView(Game game){
		
		// Set Initial Variables
		this.transitionRectangleAlpha = MAX_ALPHA;
		menu = game.getBattleMenu();
		menu.addObserver(this);
		battle = game.getBattle();	
		this.fxPanel = new JFXPanel();
		
		// Set images and initialize fade timers
        setImages();
		makeFadeTimers();
		
		
								
	}
	
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		g2.clearRect(0, 0, 100000, 100000);
		
		g2.drawImage(background, 0, 0, null);
	
		if(animX == -1 || animX > 220){
			g2.drawImage(trainer, 200, 300, null);
		}
		else{
			g2.drawImage(trainerThrow, 200, 300, null);
		}
		
		
		if(animX > endX){
			System.out.println("In here");
			timer.stop();
			animX = -1;
			animY = -1;
			endX = -1;
			menu.resultAction();
			isAnimating = false;
			healthPerc = (double) battle.getPokemon().getCurHP()/(double) battle.getPokemon().getMaxHP();
		}
		
		switch(menu.getMove()){
		    case Ball: 
		    	if(animX == -1){
		    		animX = 200;
		    		animY = 350;
		    		endX = 620;
		    		animateX();
		    		isAnimating = true;
		    	}
		    	g2.drawImage(pokeball, animX, animY, null);
		    break;
		    case Bait:
		    	if(animX == -1){
		    		animX = 200;
		    		animY = 350;
		    		endX = 620;
		    		animateX();
		    		isAnimating = true;
		    	}
		    	g2.drawImage(bait, animX, animY, null);
		    break;
		    case Rock: 
		    	if(animX == -1){
		    		animX = 200;
		    		animY = 350;
		    		endX = 620;
		    		animateX();
		    		isAnimating = true;
		    	}
		    	g2.drawImage(rock, animX, animY, null);
		    break;
		    case Run:
		    	menu.setMove(BattleAction.End);
		    break; 
		    case PokeRun:
		    	if(animX == -1){
		    		animX = 600;
		    		endX = this.getWidth() + pokemonImg.getWidth(null)/4;
		    		animateX();
		    		isAnimating = true;
		    		this.playSong(SONG_ONE);
		    	}
				   g2.drawImage(pokemonImg, animX, 550-pokemonImg.getHeight(null)/3, pokemonImg.getWidth(null)/3, pokemonImg.getHeight(null)/3, null);
		    break;
		    case End:
		    	if(menu.battleOver()){
		    	    try {
		    	        Robot robot = new Robot();

		    	        // Simulate a key press
		    	        robot.keyPress(KeyEvent.VK_Z);
		    	        robot.keyRelease(KeyEvent.VK_Z);
		    	        robot.keyPress(KeyEvent.VK_Z);
		    	        robot.keyRelease(KeyEvent.VK_Z);
		    	        robot.keyPress(KeyEvent.VK_Z);
		    	        robot.keyRelease(KeyEvent.VK_Z);
                        robot.keyPress(KeyEvent.VK_Z);
                        robot.keyRelease(KeyEvent.VK_Z);

		    	    } catch (AWTException e) {
		    	        e.printStackTrace();
		    	    }
		    	}
		    break;
		    default:
		    break;
		}
		
		if(menu.getMove() != BattleAction.PokeRun){
			
			g.setColor(Color.RED);
			g.fillRect(600,550-pokemonImg.getHeight(null)/3 - 30, pokemonImg.getWidth(null)/3 ,20);
			
			g.setColor(Color.GREEN);
			g.fillRect(600,550-pokemonImg.getHeight(null)/3 - 30, Math.round((float) (pokemonImg.getWidth(null)/3 * healthPerc)),20);
		   
			g.setColor(Color.black);
			g2.drawImage(pokemonImg, 600, 550-pokemonImg.getHeight(null)/3, pokemonImg.getWidth(null)/3, pokemonImg.getHeight(null)/3, null);
		}
		
		MenuItem[] items = menu.getItems();
		
		for (MenuItem item : items){
			g2.drawString(item.getText(), 400+80*item.getX(), 500+50*item.getY());
		}
		
		MenuItem currentItem = items[menu.getIndex()];
		
		g2.drawImage(arrow, 380+80*currentItem.getX(), 488+50*currentItem.getY(), null);
		
		g2.drawString(menu.getText(), 400, 600);
		
		Color transitionRectangleColor = new Color(0, 0, 0, this.transitionRectangleAlpha);
		g.setColor(transitionRectangleColor);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

	}
	
	private void animateX(){
		
		timer = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               animX += 10;
               animY += 2;
               repaint();
            }

        });
        timer.setRepeats(true);
        timer.setCoalesce(true);
        timer.start();
	}


	@Override
	public void update(Observable o, Object obj) {
		menu = (BattleMenu)o;
		if (obj != null){
			if ((int)obj == 1){
				this.battle = menu.getBattle();
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
	
	/**
	 * Fades in the map image, note that this will break if fadeOut is called at
	 * the same time.
	 */
	public void fadeIn() {
		this.fadeInTimer.restart();
	}

	/**
	 * Fades out the map image, note that this will break if fadeIn is called at
	 * the same time.
	 */
	public void fadeOut() {
		this.fadeOutTimer.restart();
	}
	
	private void setImages(){
		try {
			trainer = ImageIO.read(new File(TRAINERIMG));
			trainerThrow = ImageIO.read(new File(TRAINERTHROW));
			arrow = ImageIO.read(new File(ARROW));
			background = ImageIO.read(new File(BATTLEBG));
			pokeball = ImageIO.read(new File(POKEBALL));
			rock = ImageIO.read(new File(ROCK));
			bait = ImageIO.read(new File(BAIT));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
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
        fileNameMap.put(KillMe.class, "images/pokemon/KillMe.png");
	}
	
	private void makeFadeTimers(){
		this.fadeInTimer = new Timer(DURATION, null);
		this.fadeInTimer.addActionListener((e) -> {
			BattleView.this.transitionRectangleAlpha -= DELTA;
			if (BattleView.this.transitionRectangleAlpha <= MIN_ALPHA) {
				BattleView.this.transitionRectangleAlpha = MIN_ALPHA;
				BattleView.this.fadeInTimer.stop();
			}
			BattleView.this.repaint();

		});
		this.fadeOutTimer = new Timer(DURATION, null);
		this.fadeOutTimer.addActionListener((e) -> {
			BattleView.this.transitionRectangleAlpha += DELTA;
			if (BattleView.this.transitionRectangleAlpha >= MAX_ALPHA) {
				BattleView.this.transitionRectangleAlpha = MAX_ALPHA;
				BattleView.this.fadeOutTimer.stop();
			}
			BattleView.this.repaint();

		});
	}
	
	public boolean isAnimating(){
		return this.isAnimating;
	}
	
	private void playSong(String location) {
		if (this.mediaPlayer != null) {
			this.mediaPlayer.stop();
			this.mediaPlayer.dispose();
		}
		Media song = new Media(location);
		this.mediaPlayer = new MediaPlayer(song);
		// The song will repeat forever
		this.mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		this.mediaPlayer.play();
	}
	
}
