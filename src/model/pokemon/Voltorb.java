package model.pokemon;

/**
 * This is a Voltorb pokemon
 * @author itsjaked
 *
 */
public class Voltorb extends Pokemon{
	/**
	 * Makes a new Voltorb
	 */
	private static final int HP = 99;
	private static final int RUNLIKELY = 25;
	private static final int MAXHP = 99;
	private static final int MAXBATTLEDURATION = 3;
	
	public Voltorb(){
		super(HP, RUNLIKELY, MAXHP, MAXBATTLEDURATION);
	}
	
	public PokemonType getType(){
		return PokemonType.VOLTORB;
	}
}
