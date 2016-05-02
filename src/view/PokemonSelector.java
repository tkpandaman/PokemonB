package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
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

public class PokemonSelector extends JPanel
{
    private static final long serialVersionUID = 9161874824499528002L;
    private Game game;
    private int selected;
    private int numPokemon;
    private Image arrow;
    private HashMap<Class<? extends Pokemon>, String> fileNameMap = new HashMap<>();
    public PokemonSelector( Game game )
    {
        this.game = game;
        numPokemon = game.getTrainer().openPack().getPokemonCaptured();
        selected = 0;
        arrow = null;
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
        this.setLayout(null);
        this.setSize( 300,30 + ( 30 * numPokemon ) );
        this.setBorder( BorderFactory.createLineBorder( Color.BLACK ) );
        JLabel title = new JLabel( "POKEMON" );
        title.setFont( new Font( "Serif", Font.BOLD, 20 ) );
        title.setSize( 200, 20 );
        title.setLocation( 75, 5 );
        this.add( title );
        int count = 0;
        for( int i = 0; i < numPokemon; i++ )
        {
            Pokemon pokemon = game.getTrainer().openPack().getPokemonAt( i );
            JLabel menuItem = new JLabel( pokemon.getClass().getSimpleName() + " [" + pokemon.getCurHP() + "/" + pokemon.getMaxHP() + "]" );
            menuItem.setFont( new Font( "Serif", Font.BOLD, 20 ) );
            menuItem.setSize( 200, 25 );
            menuItem.setLocation( 70, 30 + (count*30)  );
            this.add( menuItem );
            count++;
        };
        try
        {
            arrow = ImageIO.read( new File( "images/arrow.png" ) );
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        int count = 0;
        for( int i = 0; i < numPokemon; i++ )
        {
            Image pokemonImg = null;
            try
            {
                pokemonImg = ImageIO.read( new File( fileNameMap.get( game.getTrainer().openPack().getPokemonAt( i ).getClass() ) ) );
                g2.drawImage(pokemonImg, 35, 30 + (count*30), 25, 25, null);
            }
            catch( IOException e )
            {
                e.printStackTrace();
            }
            count++;
        }
        g2.drawImage( arrow, 10, 35 + ( selected * 30 ), null );
    }
    public void moveUp()
    {
        if( selected > 0 )
        {
            selected--;
            this.repaint();
        }
    }
    public void moveDown()
    {
        if( selected < numPokemon - 1 )
        {
            selected++;
            this.repaint();
        }
    }
    public int getSelected()
    {
        return selected;
    }
}