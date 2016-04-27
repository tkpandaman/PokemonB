package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Game;

public class EndGame extends JPanel {
	private Game game;
	private static final long serialVersionUID = 9038488916105954451L;
	public EndGame( Game game )
	{
		this.game = game;
		drawData();
	}
	private void drawData()
	{
		this.setLayout(null);
		this.setSize(800,100);
		this.setBorder( BorderFactory.createLineBorder( Color.BLACK ) );
		// game over label
        JLabel end = new JLabel("GAME OVER" );
        end.setFont( new Font( "Serif", Font.BOLD + Font.ITALIC, 20 ) );
        end.setSize( 500, 20 );
        end.setLocation( 10, 10 );
        this.add( end );
        // player name label
        JLabel name = new JLabel("PLAYER: " + game.getTrainer().getName() );
        name.setFont( new Font( "Serif", Font.BOLD, 20 ) );
        name.setSize( 500, 20 );
        name.setLocation( 10, 40 );
        this.add( name );
        /*JButton newGame = new JButton( "New Game" );
        newGame.setSize( 150, 25 );
        newGame.setLocation( 10, 70 );
        newGame.addActionListener( new ActionListener()
        {
            @Override
            public void actionPerformed( ActionEvent e )
            {
                // need a new game method for game if we want this button to work
            }
        });
        this.add( newGame );*/
        // pokemon caught label
        JLabel pokemonCaught = new JLabel( "POKEMON CAUGHT: " + game.getTrainer().openPack().getPokemonCaptured() );
        pokemonCaught.setFont( new Font( "Serif", Font.BOLD, 20 ) );
        pokemonCaught.setSize( 500, 20 );
        pokemonCaught.setLocation( 410, 10 );
        this.add( pokemonCaught );
        // pokeballs remaining label
        JLabel pokeballs = new JLabel( "POKEBALLS REMAINING: " + game.getTrainer().openPack().getPokeballsLeft() );
        pokeballs.setFont( new Font( "Serif", Font.BOLD, 20 ) );
        pokeballs.setSize( 500, 20 );
        pokeballs.setLocation( 410, 40 );
        this.add( pokeballs );
        // steps taken label
        JLabel steps = new JLabel( "STEPS TAKEN: " + ( 500 - game.getTrainer().getStepsLeft() ) );
        steps.setFont( new Font( "Serif", Font.BOLD, 20 ) );
        steps.setSize( 500, 20 );
        steps.setLocation( 410, 70 );
        this.add( steps );
	}
}
