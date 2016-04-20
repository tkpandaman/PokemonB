package model;

public class BattleMenu {
	
	private int menuIndex;
	
	public BattleMenu(){
		menuIndex = 0;
	}
	
	public void left(){
		menuIndex--;
		if (menuIndex < 0) menuIndex = 3;
	}
	
	public void right(){
		menuIndex++;
		if (menuIndex > 3) menuIndex = 0;
	}
	
	public void up(){
		menuIndex -= 2;
		if (menuIndex < 0) menuIndex += 4;
	}
	
	public void down(){
		menuIndex += 2;
		if (menuIndex < 0) menuIndex -= 4;
	}
	
	public class MenuItem{
		private String text;
		
		public MenuItem(String text){
			this.text = text;
		}
		
		public String getText(){
			return text;
		}
	}
	
}
