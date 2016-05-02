package view;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.Game;

public class Stats extends JPanel
{
    private static final long serialVersionUID = -5310012963432882975L;
    private Game game;
    public Stats( Game game )
    {
        this.game = game;
        layoutPanel();
    }
    private void layoutPanel()
    {
        this.setLayout(null);
        this.setSize( 200, 90 );
        this.setBorder( BorderFactory.createLineBorder( Color.BLACK ) );
        this.setLocation( 700, 300);
        JLabel title = new JLabel( "STATS" );
        title.setFont( new Font( "Serif", Font.BOLD, 20 ) );
        title.setSize( 200, 20 );
        title.setLocation( 75, 5 );
        this.add( title );
        JLabel steps = new JLabel( "Steps Left: " + game.getTrainer().getStepsLeft() );
        steps.setFont( new Font( "Serif", Font.BOLD, 20 ) );
        steps.setSize( 200, 25 );
        steps.setLocation( 5,  30 );
        this.add( steps );
        JLabel pokeballs = new JLabel( "Pokeballs: " + game.getTrainer().openPack().getPokeballsLeft() );
        pokeballs.setFont( new Font( "Serif", Font.BOLD, 20 ) );
        pokeballs.setSize( 200, 25 );
        pokeballs.setLocation( 5,  60 );
        this.add( pokeballs );
    }
}