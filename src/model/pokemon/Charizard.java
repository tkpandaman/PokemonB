package model.pokemon;

/**
 * This is a Charizard pokemon
 * @author itsjaked
 *
 */
public class Charizard extends Pokemon{
	/**
	 * Makes a new Charizard
	 */
	private static final int HP = 120;
	private static final int RUNLIKELY = 50;
	private static final int MAXHP = 120;
	private static final int MAXBATTLEDURATION = 10;
	
	public Charizard(){
		super(HP, RUNLIKELY, MAXHP, MAXBATTLEDURATION);
	}
	
	public PokemonType getType(){
		return PokemonType.CHARIZARD;
	}
}
