package model;

/**
 * This is a Voltorb pokemon
 * @author itsjaked
 *
 */
public class Voltorb extends Pokemon{
	/**
	 * Makes a new Voltorb
	 */
	public Voltorb(){
		super(100, 30, 100, 10, "Voltorb.png");
	}
	
	public String getType(){
		return "Voltorb";
	}
}
