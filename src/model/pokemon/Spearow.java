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
	private static final int HP = 120;
	private static final int RUNLIKELY = 50;
	private static final int MAXHP = 120;
	private static final int MAXBATTLEDURATION = 10;
	
	public Spearow(){
		super(HP, RUNLIKELY, MAXHP, MAXBATTLEDURATION);
	}
	
	public PokemonType getType(){
		return PokemonType.SPEAROW;
	}
}

