package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Game;

public class PokemonView extends JPanel {
	private static final long serialVersionUID = 1964082514739882696L;
	private Game game;
	public PokemonView( Game game )
	{
		this.game = game;
		layoutPanel();
	}
	private void layoutPanel()
	{
		this.setLayout( null );
        this.setSize( 200, 30 + ( 30 * this.game.getTrainer().openPack().getPokemonCaptured() ) );
        this.setBorder( BorderFactory.createLineBorder( Color.BLACK ) );
        // pokemon label
        JLabel pokemonLabel = new JLabel( "POKEMON (" + game.getTrainer().openPack().getPokemonCaptured() + "):" );
        pokemonLabel.setFont( new Font( "Serif", Font.BOLD, 20 ) );
        pokemonLabel.setSize( 200, 20 );
        pokemonLabel.setLocation( 5, 5 );
        this.add( pokemonLabel );
        for( int i = 0; i < this.game.getTrainer().openPack().getPokemonCaptured(); i++ )
        {
            JLabel poke = new JLabel( ( i + 1 ) + ".) " + this.game.getTrainer().openPack().getPokemonAt( i ).getClass().getSimpleName() );
            poke.setFont( new Font( "Serif", Font.BOLD, 20 ) );
            poke.setSize( 200, 20 );
            poke.setLocation( 5, 30 + ( 30 * i ) );
            this.add( poke );
        }
	}
} 