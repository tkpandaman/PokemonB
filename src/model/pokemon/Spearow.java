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
	private static final String FILENAME = "Charizard.png";
	
	public Spearow(){
		super(HP, RUNLIKELY, MAXHP, MAXBATTLEDURATION, FILENAME);
	}
	
	public PokemonType getType(){
		return PokemonType.SPEAROW;
	}
}

