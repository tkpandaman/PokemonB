package model.pokemon;

/**
 * This is a Beedrill pokemon
 * @author itsjaked
 *
 */
public class Beedrill extends Pokemon{
	/**
	 * Makes a new Beedrill
	 */
	private static final int HP = 120;
	private static final int RUNLIKELY = 50;
	private static final int MAXHP = 120;
	private static final int MAXBATTLEDURATION = 10;
	
	public Beedrill(){
		super(HP, RUNLIKELY, MAXHP, MAXBATTLEDURATION);
	}
	
	public PokemonType getType(){
		return PokemonType.BEEDRILL;
	}
}

