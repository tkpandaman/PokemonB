package model.pokemon;

/**
 * This is a Snorlax pokemon
 * @author itsjaked
 *
 */
public class Pikachu extends Pokemon{
	/**
	 * Makes a new Pikachu
	 */
	private static final int HP = 200;
	private static final int RUNLIKELY = 5;
	private static final int MAXHP = 200;
	private static final int MAXBATTLEDURATION = 20;
	
	public Pikachu(){
		super(HP, RUNLIKELY, MAXHP, MAXBATTLEDURATION);
	}
	
	public PokemonType getType(){
		return PokemonType.PIKACHU;
	}
}

