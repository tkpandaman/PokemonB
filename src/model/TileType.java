package model;

import java.io.Serializable;

// An enum which defines the different behaviors a tile can have.
// (solidity and random-encounter-chance so far)
public enum TileType implements Serializable {

	Floor("Floor", "F", false, 0),
	Grass("Grass", "G", false, 0.05f),
	LongGrass("Long Grass", "L", false, 0.1f),
	Wall("Wall", "W", true, 0);
	
	private String name;
	private String abbrev;
	private boolean solid;
	private float randEncounterChance;
	
	TileType(String name, String abbrev, boolean isSolid, float randomEncounterChance){
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
	public float getRandEncounterChance(){
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
