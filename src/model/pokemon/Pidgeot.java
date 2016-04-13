package model.pokemon;

/**
 * This is a Pidgeot pokemon
 * @author itsjaked
 *
 */
public class Pidgeot extends Pokemon{
	/**
	 * Makes a new Pidgeot
	 */
	private static final int HP = 120;
	private static final int RUNLIKELY = 50;
	private static final int MAXHP = 120;
	private static final int MAXBATTLEDURATION = 10;
	private static final String FILENAME = "Charizard.png";
	
	public Pidgeot(){
		super(HP, RUNLIKELY, MAXHP, MAXBATTLEDURATION, FILENAME);
	}
	
	public PokemonType getType(){
		return PokemonType.PIDGEOT;
	}
}
