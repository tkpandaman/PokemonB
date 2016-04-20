package model.pokemon;

/**
 * This is a Arbok pokemon
 * @author itsjaked
 *
 */
public class Arbok extends Pokemon{
	/**
	 * Makes a new Arbok
	 */
	private static final int HP = 90;
	private static final int RUNLIKELY = 70;
	private static final int MAXHP = 90;
	private static final int MAXBATTLEDURATION = 3;
	
	public Arbok(){
		super(HP, RUNLIKELY, MAXHP, MAXBATTLEDURATION);
	}
	
	public PokemonType getType(){
		return PokemonType.ARBOK;
	}
}

