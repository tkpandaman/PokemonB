package model;

//Wrapper class for Item that allows for an Item's X and Y position to be stored for the Map.
public class MapItem {
	
	private int X,Y;
	private Item item;
	
	public MapItem(int x, int y, Item item){
		this.setX(x);
		this.setY(y);
		this.setItem(item);
	}

	public int getX() {
		return X;
	}

	public void setX(int x) {
		X = x;
	}

	public int getY() {
		return Y;
	}

	public void setY(int y) {
		Y = y;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

}
