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
	private static final int HP = 120;
	private static final int RUNLIKELY = 50;
	private static final int MAXHP = 120;
	private static final int MAXBATTLEDURATION = 10;
	private static final String FILENAME = "Charizard.png";
	
	public Squirtle(){
		super(HP, RUNLIKELY, MAXHP, MAXBATTLEDURATION, FILENAME);
	}
	
	public PokemonType getType(){
		return PokemonType.SQUIRTLE;
	}
}
