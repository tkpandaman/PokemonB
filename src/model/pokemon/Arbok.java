package model.pokemon;

/**
 * This is a Snorlax pokemon
 * @author itsjaked
 *
 */
public class Arbok extends Pokemon{
	/**
	 * Makes a new Arbok
	 */
	private static final int HP = 120;
	private static final int RUNLIKELY = 50;
	private static final int MAXHP = 120;
	private static final int MAXBATTLEDURATION = 10;
	
	public Arbok(){
		super(HP, RUNLIKELY, MAXHP, MAXBATTLEDURATION);
	}
	
	public PokemonType getType(){
		return PokemonType.ARBOK;
	}
}

