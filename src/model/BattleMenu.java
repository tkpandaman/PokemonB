package model;

import java.io.Serializable;
import java.util.Observable;

public class BattleMenu extends Observable implements Serializable{
	
    private static final long serialVersionUID = 7058713038305325130L;
    private int menuIndex;
	private MenuItem[] buttons;
	private Battle battle;
	private String text = "test";
	
	private boolean battleOver;
	private BattleAction curMove;
	
	public BattleMenu(){
		menuIndex = 0;
		curMove = BattleAction.Start;
		
		buttons = new MenuItem[4];
		buttons[0] = new MenuItem("BALL", BattleAction.Ball, 0, 0);
		buttons[1] = new MenuItem("BAIT", BattleAction.Bait, 1, 0);
		buttons[2] = new MenuItem("ROCK", BattleAction.Rock, 0, 1);
		buttons[3] = new MenuItem("RUN", BattleAction.Run, 1, 1);
	}

	public int getIndex(){
		return menuIndex;
	}
	
	public boolean battleOver(){
		return battleOver;
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
		if (menuIndex > 3) menuIndex -= 4;
		setChanged();
		notifyObservers();
	}
	
	public void startBattle(Battle battle){
		this.battle = battle;
		text = "";
		battleOver = false;
		setChanged();
		notifyObservers(1);
	}
	
	public Battle getBattle(){
		return battle;
	}
	
	public void select(){
		MenuItem item = buttons[menuIndex];
		switch(item.getAction()){
		case Ball:
			text = "You threw a safari ball! \r\n";
			curMove = BattleAction.Ball;
			boolean result = battle.throwSafariBall();
			if (result){
				text += "Success!";
				battleOver = true;
			}
			else text += "Failure!";
			break;
		case Bait:
			curMove = BattleAction.Bait;
			text = "You threw bait!";
			battle.throwBait();
			break;
		case Rock:
			curMove = BattleAction.Rock;
			text = "You threw a rock!";
			battle.throwRock();
			break;
		case Run:
			curMove = BattleAction.Run;
			battleOver = true;
			break;
		default: break;
		}
		
		setChanged();
		notifyObservers();
		
	}
	
	public void resultAction(){
		if( battle.getTrainer().openPack().getPokeballsLeft() == 0 )
		{
		    text = "You ran out of pokeballs!";
		    curMove = BattleAction.End;
		    battleOver = true;
		}
		else if (battle.pokemonRanAway() && !battleOver){
			text = "The pokemon ran away!";
			if(curMove == BattleAction.PokeRun){
				curMove = BattleAction.End;
			}
			else{
			    curMove = BattleAction.PokeRun;
			}
			battleOver = true;
		}
		else{
			curMove = BattleAction.End;
		}
		setChanged();
		notifyObservers();
	}
	
	public MenuItem[] getItems(){
		return buttons;
	}
	
	public BattleAction getMove(){
		return this.curMove;
	}
	
	public void setMove(BattleAction b){
		this.curMove = b;
		setChanged();
		notifyObservers();
	}
	
	public String getText(){
		return text;
	}
	
	public class MenuItem implements Serializable{
        private static final long serialVersionUID = -5678340590630203587L;
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
