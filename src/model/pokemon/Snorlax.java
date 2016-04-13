package model.pokemon;

/**
 * This is a Snorlax pokemon
 * @author itsjaked
 *
 */
public class Snorlax extends Pokemon{
	/**
	 * Makes a new Snorlax
	 */
	private static final int HP = 120;
	private static final int RUNLIKELY = 50;
	private static final int MAXHP = 120;
	private static final int MAXBATTLEDURATION = 10;
	
	public Snorlax(){
		super(HP, RUNLIKELY, MAXHP, MAXBATTLEDURATION);
	}
	
	public PokemonType getType(){
		return PokemonType.SNORLAX;
	}
}
