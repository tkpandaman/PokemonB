package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import model.Game;
import model.pokemon.Arbok;
import model.pokemon.Beedrill;
import model.pokemon.Butterfree;
import model.pokemon.Charizard;
import model.pokemon.Pidgeot;
import model.pokemon.Pikachu;
import model.pokemon.Pokemon;
import model.pokemon.Snorlax;
import model.pokemon.Spearow;
import model.pokemon.Squirtle;
import model.pokemon.Voltorb;

public class PokemonView extends JPanel {
	private static final long serialVersionUID = 1964082514739882696L;
	private Game game;
	private HashMap<Class<? extends Pokemon>, String> fileNameMap = new HashMap<>();
	private int jump;
	public PokemonView( Game game )
	{
		this.game = game;
		jump = 0;
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
		layoutPanel();
	}
	private void layoutPanel()
	{
		this.setLayout( null );
        this.setSize( 200, 30 + ( 30 * this.game.getTrainer().openPack().getPokemonCaptured() ) );
        this.setBorder( BorderFactory.createLineBorder( Color.BLACK ) );
        this.setVisible(true);
        // pokemon label
        JLabel pokemonLabel = new JLabel( "POKEMON (" + game.getTrainer().openPack().getPokemonCaptured() + "):" );
        pokemonLabel.setFont( new Font( "Serif", Font.BOLD, 20 ) );
        pokemonLabel.setSize( 200, 20 );
        pokemonLabel.setLocation( 5, 5 );
        this.add( pokemonLabel );
        for( int i = 0; i < this.game.getTrainer().openPack().getPokemonCaptured(); i++ )
        {
            JLabel poke = new JLabel( this.game.getTrainer().openPack().getPokemonAt( i ).getClass().getSimpleName() );
            poke.setFont( new Font( "Serif", Font.BOLD, 20 ) );
            poke.setSize( 200, 25 );
            poke.setLocation( 35, 30 + ( 30 * i ) );
            this.add( poke );
        }
	}
	public void paintComponent( Graphics g )
	{
	    super.paintComponent( g );
	    Graphics2D g2 = (Graphics2D)g;
	    int count = 0;
        for( int i = 0; i < game.getTrainer().openPack().getPokemonCaptured(); i++ )
        {
            Image pokemonImg = null;
            try
            {
                pokemonImg = ImageIO.read( new File( fileNameMap.get( game.getTrainer().openPack().getPokemonAt( i ).getClass() ) ) );
                g2.drawImage(pokemonImg, 5, 30 + (count*30) + jump, 25, 25, null);
            }
            catch( IOException e )
            {
                e.printStackTrace();
            }
            count++;
        }
        animate();
	}
	public void animate()
    {
        Timer timer = new Timer(300, new ActionListener() {
            @Override
            public void actionPerformed( ActionEvent arg0 ) {
                if( jump == 0 )
                {
                    jump = 5;
                }
                else
                {
                    jump = 0;
                }
                PokemonView.this.repaint();
            }
        });
        timer.setRepeats( false );
        timer.start();
    }
} 