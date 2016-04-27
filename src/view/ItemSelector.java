package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.Game;
import model.Item;

public class ItemSelector extends JPanel
{
    private static final long serialVersionUID = 9161874824499528002L;
    private Game game;
    private int selected;
    private int numMenuItems;
    private Image arrow;
    public ItemSelector( Game game )
    {
        this.game = game;
        numMenuItems = 0;
        selected = 0;
        arrow = null;
        layoutPanel();
    }
    private void layoutPanel()
    {
        this.setLayout(null);
        for( int i = 0; i < game.getTrainer().openPack().getTrainerItems().size(); i++ )
        {
            numMenuItems++;
        }
        for( int i = 0; i < game.getTrainer().openPack().getPokemonItems().size(); i++ )
        {
            numMenuItems++;
        }
        this.setSize( 200,30 + ( 30 * numMenuItems ) );
        this.setBorder( BorderFactory.createLineBorder( Color.BLACK ) );
        //this.setLocation(700, 100);
        // title label
        JLabel title = new JLabel( "ITEMS" );
        title.setFont( new Font( "Serif", Font.BOLD, 20 ) );
        title.setSize( 200, 20 );
        title.setLocation( 75, 5 );
        this.add( title );
        int count = 0;
        for( int i = 0; i < game.getTrainer().openPack().getTrainerItems().size(); i++ )
        {
            JLabel menuItem = new JLabel( game.getTrainer().openPack().getTrainerItems().get( i ).getName() );
            menuItem.setFont( new Font( "Serif", Font.BOLD, 20 ) );
            menuItem.setSize( 200, 20 );
            menuItem.setLocation( 35, 30 + (count*30)  );
            this.add( menuItem );
            count++;
        };
        for( int i = 0; i < game.getTrainer().openPack().getPokemonItems().size(); i++ )
        {
            JLabel menuItem = new JLabel( game.getTrainer().openPack().getPokemonItems().get( i ).getName() );
            menuItem.setFont( new Font( "Serif", Font.BOLD, 20 ) );
            menuItem.setSize( 200, 20 );
            menuItem.setLocation( 35, 30 + (count*30)  );
            this.add( menuItem );
            count++;
        }
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
        if( selected < numMenuItems - 1 )
        {
            selected++;
            this.repaint();
        }
    }
    public Item getSelected()
    {
        int trainerItems = game.getTrainer().openPack().getTrainerItems().size();
        if( selected < trainerItems )
        {
            return game.getTrainer().openPack().getTrainerItems().get( selected );
        }
        int ind = selected - trainerItems;
        return game.getTrainer().openPack().getPokemonItems().get( ind );
    }
    public int getNumItems()
    {
        return numMenuItems;
    }
}