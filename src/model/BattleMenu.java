package model;

import java.util.Observable;

public class BattleMenu extends Observable {
	
	private int menuIndex;
	private MenuItem[] buttons;
	
	private enum BattleAction{Ball, Bait, Rock, Run};
	
	public BattleMenu(){
		menuIndex = 0;
		
		buttons = new MenuItem[4];
		buttons[0] = new MenuItem("BALL", BattleAction.Ball, 0, 0);
		buttons[1] = new MenuItem("BAIT", BattleAction.Ball, 1, 0);
		buttons[2] = new MenuItem("ROCK", BattleAction.Ball, 0, 1);
		buttons[3] = new MenuItem("RUN", BattleAction.Ball, 1, 1);
	}
	
	public int getIndex(){
		return menuIndex;
	}
	
	public void left(){
		menuIndex--;
		if (menuIndex < 0) menuIndex = 3;
		setChanged();
		notifyObservers();
	}
	
	public void right(){
		menuIndex++;
		if (menuIndex > 3) menuIndex = 0;
		setChanged();
		notifyObservers();
	}
	
	public void up(){
		menuIndex -= 2;
		if (menuIndex < 0) menuIndex += 4;
		setChanged();
		notifyObservers();
	}
	
	public void down(){
		menuIndex += 2;
		if (menuIndex < 0) menuIndex -= 4;
		setChanged();
		notifyObservers();
	}
	
	public void select(){
		//TODO: talk to Battle based on selected button's BattleAction
		setChanged();
		notifyObservers();
	}
	
	public MenuItem[] getItems(){
		return buttons;
	}
	
	public class MenuItem{
		private String text;
		private BattleAction battleAction;
		private int x, y;
		
		public MenuItem(String text, BattleAction action, int x, int y){
			this.text = text;
			this.x = x;
			this.y = y;
			this.battleAction = action;
		}
		
		public String getText(){
			return text;
		}
		
		public int getX(){
			return x;
		}
		
		public int getY(){
			return y;
		}
		
		public BattleAction getAction(){
			return battleAction;
		}
	}
	
}
