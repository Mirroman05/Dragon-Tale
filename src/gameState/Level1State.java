package gameState;

import java.awt.Color;
import java.awt.Graphics2D;

import Main.GamePanel;
import TileMap.TileMap;

public class Level1State extends GameState{

	private TileMap tileMap;
	
	public Level1State(GameStateManager gsm){
		this.gsm = gsm;
		init();
	}
	
	public void init() {
		
		tileMap = new TileMap(30);
		tileMap.loadTiles("/Tilesets/grasstileset.gif");
		tileMap.loadMap("/Maps/level1-1.map");
		tileMap.setPosition(0,0);
		
	}

	
	public void update() {
		// TODO Auto-generated method stub
		
	}

	
	public void draw(Graphics2D g) {
		
		//clear screen
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		
		//draw tileMap
		tileMap.draw(g);
	}

	
	public void keyPressed(int k) {
		// TODO Auto-generated method stub
		
	}

	
	public void keyReleased(int k) {
		// TODO Auto-generated method stub
		
	}

}
