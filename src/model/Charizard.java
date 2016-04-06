package model;

/**
 * This is a Charizard pokemon
 * @author itsjaked
 *
 */
public class Charizard extends Pokemon{
	/**
	 * Makes a new Charizard
	 */
	public Charizard(){
		super(120, 50, 120, 10, "Charizard.png");
	}
	
	public String getType(){
		return "Charizard";
	}
}
