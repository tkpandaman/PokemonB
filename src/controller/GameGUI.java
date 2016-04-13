package controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.Gamestate;

public class GameGUI extends JFrame
{
    private class SaveAndLoad extends WindowAdapter
    {
        private static final String SAVED_COLLECTION_LOCATION = "pokemonSave";

        @Override
        public void windowOpened( WindowEvent e )
        {
            int selectedChoice = JOptionPane.showConfirmDialog( null, "Start with previous saved game?", "Select an option", JOptionPane.YES_NO_CANCEL_OPTION );
            if( selectedChoice == JOptionPane.NO_OPTION )
            {
                // load defaults if we do not want to restore our data
                Gamestate.getInstance();
            };
            if( selectedChoice == JOptionPane.YES_OPTION )
            {
                try
                {
                    FileInputStream fis = new FileInputStream( SAVED_COLLECTION_LOCATION );
                    ObjectInputStream ois = new ObjectInputStream( fis );
                    // load our saved data
                    Gamestate.setInstance( (Gamestate)ois.readObject() );
                    ois.close();
                    fis.close();
                    
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            };
            // change GUI after data loaded
        };
        @Override
        public void windowClosing( WindowEvent e )
        {
            int selectedChoice = JOptionPane.showConfirmDialog( null, "Save data?", "Select an option", JOptionPane.YES_NO_CANCEL_OPTION );
            if( selectedChoice == JOptionPane.NO_OPTION )
            {
                System.exit( 0 );
            };
            if( selectedChoice == JOptionPane.YES_OPTION )
            {
                try
                {
                    // save current state of pokemon game (Trainer, pokemon, items, backpack, etc.)
                    FileOutputStream fos = new FileOutputStream(SAVED_COLLECTION_LOCATION);
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    // save all data we need to a file
                    oos.writeObject( Gamestate.getInstance() );
                    oos.close();
                    fos.close();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            };
        };
    };
}