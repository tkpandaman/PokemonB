package model.pokemon;

/**
 * This is a Pidgeot pokemon
 * @author itsjaked
 *
 */
public class Pidgeot extends Pokemon{
	/**
	 * Makes a new Pidgeot
	 */
	private static final int HP = 75;
	private static final int RUNLIKELY = 55;
	private static final int MAXHP = 75;
	private static final int MAXBATTLEDURATION = 3;
	
	public Pidgeot(){
		super(HP, RUNLIKELY, MAXHP, MAXBATTLEDURATION);
	}
	
	public PokemonType getType(){
		return PokemonType.PIDGEOT;
	}
}
