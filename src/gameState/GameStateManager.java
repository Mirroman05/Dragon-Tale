package gameState;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class GameStateManager {

	private ArrayList<GameState> gameStates;
	private int currentState;
	
	public static final int MENUSTATE = 0;
	public static final int LEVELSTATE1 = 1;
	
	public GameStateManager(){
		
		gameStates = new ArrayList<GameState>();
		
		currentState =MENUSTATE;
		gameStates.add(new MenuState(this));
	}
	
	public void setState(int state){
		currentState = state;
		gameStates.get(currentState).init();
		
	}
	
	public void update(){
		gameStates.get(currentState).update();
	}
	
	public void draw(Graphics2D g){
		gameStates.get(currentState).draw();
	}
	
	public void keyPressed(int k) {
		gameStates.get(currentState).keyPressed(k);
	}

	public void keyReleased(int k) {
		gameStates.get(currentState).keyReleased(k);
	}
	
	
	
	
}