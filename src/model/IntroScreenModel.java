package model;

import java.util.Observable;

public class IntroScreenModel extends Observable {
	
	public String[] startChoice;
	public String[] mainChoice;
	public String[] loadChoice;
	public String[] newGameChoice;
	
	private int selected;
	private IntroScreenState state;
	
	private Game game;
	
	public enum IntroScreenState{ Start, Main, NewGame, StartNewGame, LoadGame, LoadFail, Credits, Quit };
	
	public IntroScreenModel(Game game){
		this.game = game;
		this.selected = 0;
		this.state = IntroScreenState.Start;
		
		loadStrings();
	}
	
	public String[] getMenuOptions(){
		switch(this.state){
		case Start: return startChoice;
		case Main: return mainChoice;
		case NewGame: return newGameChoice;
		case LoadFail: return loadChoice;
		case Credits: return loadChoice;
		default: return new String[0];
		}
	}
	
	public void update(){
		this.setChanged();
		this.notifyObservers();
	}
	
	public void moveUp()
	{
		if( selected > 0 )
		{
			selected--;
			update();
		}
	}
	
	public void moveDown()
	{
		if( selected < getMenuOptions().length - 1 )
		{
			selected++;
			update();
		}
	}
	
	public IntroScreenState getState(){
		return this.state;
	}
	
	public void reset(){
		this.state = IntroScreenState.Start;
	}
	 
	public void select(){
		
		switch(this.state){
		case Start: 
			this.state = IntroScreenState.Main;
			break;
		case Main: 
			if (mainChoice[selected].equals("New Game")){
				this.state = IntroScreenState.NewGame;
			} else if (mainChoice[selected].equals("Continue")){
				this.state = IntroScreenState.LoadGame;
			} else if (mainChoice[selected].equals("Credits")){
				this.state = IntroScreenState.Credits;
			} else if (mainChoice[selected].equals("Quit")){
				this.state = IntroScreenState.Quit;
			}
			break;
		case NewGame: 
			if (newGameChoice[selected].equals("Walk 500 Steps")){
				game.setWin(WinCondition.STEPS);
				game.setState(State.NORMAL);
				this.state = IntroScreenState.StartNewGame;
			} else if (newGameChoice[selected].equals("Capture 10 Pokemon")){
				game.setWin(WinCondition.POKEMON);
				game.setState(State.NORMAL);
				this.state = IntroScreenState.StartNewGame;
			} else if (newGameChoice[selected].equals("Use 30 Safariballs")){
				game.setWin(WinCondition.SAFARIBALLS);
				game.setState(State.NORMAL);
				this.state = IntroScreenState.StartNewGame;
			} 
			else if (newGameChoice[selected].equals("Back")){
				this.state = IntroScreenState.Main;
			} 
			break;
		case LoadFail:
			this.state = IntroScreenState.Main;
			break;
		case Credits:
			this.state = IntroScreenState.Main;
			break;
		default: break;
		}
		this.selected = 0;
		update();
		
	}
	public int getSelected()
	{
		return selected;
	}
	
	private void loadStrings(){
		startChoice = new String[]{"Press Enter"};
		mainChoice = new String[]{"New Game", "Continue", "Credits", "Quit"};
		loadChoice = new String[]{"Back"};
		newGameChoice = new String[]{"Walk 500 Steps", "Capture 10 Pokemon", "Use 30 Safariballs", "Back"};
	}

	public void loadFailed() {
		this.state = IntroScreenState.LoadFail;
	}

}
