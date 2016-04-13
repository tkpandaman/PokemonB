package model.pokemon;

/**
 * This is a Spearow pokemon
 * @author itsjaked
 *
 */
public class Spearow extends Pokemon{
	/**
	 * Makes a new Spearow
	 */
	private static final int HP = 60;
	private static final int RUNLIKELY = 25;
	private static final int MAXHP = 60;
	private static final int MAXBATTLEDURATION = 2;
	
	public Spearow(){
		super(HP, RUNLIKELY, MAXHP, MAXBATTLEDURATION);
	}
	
	public PokemonType getType(){
		return PokemonType.SPEAROW;
	}
}

