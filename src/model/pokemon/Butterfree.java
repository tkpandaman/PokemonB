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
	private static final int HP = 120;
	private static final int RUNLIKELY = 50;
	private static final int MAXHP = 120;
	private static final int MAXBATTLEDURATION = 10;
	
	public Butterfree(){
		super(HP, RUNLIKELY, MAXHP, MAXBATTLEDURATION);
	}
	
	public PokemonType getType(){
		return PokemonType.BUTTERFREE;
	}
}

