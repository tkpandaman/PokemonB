package model.pokemon;

/**
 * ???
 * @author
 *
 */
public class KillMe extends Pokemon{
	/**
	 * Makes a new KillMe
	 */
	private static final int HP = -999;
	private static final int RUNLIKELY = 10;
	private static final int MAXHP = 90;
	private static final int MAXBATTLEDURATION = 5;
	
	public KillMe(){
		super(HP, RUNLIKELY, MAXHP, MAXBATTLEDURATION);
	}
	
	public PokemonType getType(){
		return PokemonType.KILLME;
	}
}

