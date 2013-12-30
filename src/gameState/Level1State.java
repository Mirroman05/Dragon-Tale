package gameState;

import java.awt.Color;
import java.awt.Graphics2D;

import Main.GamePanel;
import TileMap.Background;
import TileMap.TileMap;

public class Level1State extends GameState{

	private TileMap tileMap;
	private Background bg;
	
	public Level1State(GameStateManager gsm){
		this.gsm = gsm;
		init();
	}
	
	public void init() {
		
		tileMap = new TileMap(30);
		tileMap.loadTiles("/Tilesets/grasstileset.gif");
		tileMap.loadMap("/Maps/level1-1.map");
		tileMap.setPosition(0,0);
		
		bg = new Background("/Backgrounds/grassbg1.gif",0.1);
		
	}

	
	public void update() {
		// TODO Auto-generated method stub
		
	}

	
	public void draw(Graphics2D g) {
		
		//draw bg
		bg.draw(g);
		
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
