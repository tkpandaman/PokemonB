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
	private static final int HP = 150;
	private static final int RUNLIKELY = 20;
	private static final int MAXHP = 150;
	private static final int MAXBATTLEDURATION = 6;
	
	public Snorlax(){
		super(HP, RUNLIKELY, MAXHP, MAXBATTLEDURATION);
	}
	
	public PokemonType getType(){
		return PokemonType.SNORLAX;
	}
}
