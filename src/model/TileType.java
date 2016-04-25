package model;

import java.io.Serializable;

// An enum which defines the different behaviors a tile can have.
// (solidity and random-encounter-chance so far)
public enum TileType implements Serializable {

	Floor("Floor", "F", false, Integer.MAX_VALUE),
	Grass("Grass", "G", false, 100), // 1/100 chance
	LongGrass("Long Grass", "L", false, 20), // 1/20 change
	Wall("Wall", "W", true, Integer.MAX_VALUE);
	
	private String name;
	private String abbrev;
	private boolean solid;
	private int randEncounterChance;
	
	TileType(String name, String abbrev, boolean isSolid, int randomEncounterChance){
		this.name = name;
		this.abbrev = abbrev;
		this.solid = isSolid;
		this.randEncounterChance = randomEncounterChance;
	}
	
	// Returns whether this tile is solid.
	public boolean isSolid(){
		return solid;
	}
	
	// Returns the chance (out of 1) of encountering a Pokemon on this tile.
	public int getRandEncounterChance(){
		return randEncounterChance;
	}
	
	// A string representation of the enum.
	public String toString(){
		return name;
	}
	
	// A shorter string representation of the enum.
	public String getAbbreviation(){
		return abbrev;
	}
	
}
