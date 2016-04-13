package model.pokemon;

/**
 * This is a Butterfree pokemon
 * @author itsjaked
 *
 */
public class Butterfree extends Pokemon{
	/**
	 * Makes a new Butterfree
	 */
	private static final int HP = 111;
	private static final int RUNLIKELY = 33;
	private static final int MAXHP = 111;
	private static final int MAXBATTLEDURATION = 6;
	
	public Butterfree(){
		super(HP, RUNLIKELY, MAXHP, MAXBATTLEDURATION);
	}
	
	public PokemonType getType(){
		return PokemonType.BUTTERFREE;
	}
}

