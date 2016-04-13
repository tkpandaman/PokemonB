package model.pokemon;

/**
 * This is a Snorlax pokemon
 * @author itsjaked
 *
 */
public class Squirtle extends Pokemon{
	/**
	 * Makes a new Squirtle
	 */
	private static final int HP = 105;
	private static final int RUNLIKELY = 35;
	private static final int MAXHP = 105;
	private static final int MAXBATTLEDURATION = 4;
	
	public Squirtle(){
		super(HP, RUNLIKELY, MAXHP, MAXBATTLEDURATION);
	}
	
	public PokemonType getType(){
		return PokemonType.SQUIRTLE;
	}
}
