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
	private static final int HP = 120;
	private static final int RUNLIKELY = 50;
	private static final int MAXHP = 120;
	private static final int MAXBATTLEDURATION = 10;
	private static final String FILENAME = "Charizard.png";
	
	public Voltorb(){
		super(HP, RUNLIKELY, MAXHP, MAXBATTLEDURATION, FILENAME);
	}
	
	public PokemonType getType(){
		return PokemonType.VOLTORB;
	}
}
