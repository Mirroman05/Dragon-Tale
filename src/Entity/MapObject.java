package Entity;

import java.awt.Rectangle;

import Main.GamePanel;
import TileMap.Tile;
import TileMap.TileMap;

public abstract class MapObject {

	// tile stuff
	protected TileMap tileMap;
	protected int tileSize;
	protected double xmap, ymap;

	// position and vector
	protected double x, y, dx, dy;

	// dimensions
	protected int width, height;

	// collision box
	protected int cwidth, cheight;

	// collision
	protected int currRow, currCol;
	protected double xdest, ydest, xtemp, ytemp;
	protected boolean topLeft, topRight, bottomLeft, bottomRight;

	// animation
	protected Animation animation;
	protected int currentAction, previousAction;
	protected boolean facingRight;

	// movement
	protected boolean left, right, up, down, jumping, falling;

	// movement attributes
	protected double moveSpeed, maxSpeed, stopSpeed, fallSpeed, maxFallSpeed,
			jumpStart, stopJumpSpeed;

	public MapObject(TileMap tm) {
		tileMap = tm;
		tileSize = tm.getTilesSize();
	}

	public boolean intersects(MapObject o) {
		Rectangle r1 = getRectangle();
		Rectangle r2 = o.getRectangle();
		return r1.intersects(r2);
	}

	public Rectangle getRectangle() {
		return new Rectangle((int) x - cwidth, (int) y - cheight, cwidth,
				cheight);
	}

	public void calculateCorners(double x, double y) {
		int leftTile = (int) (x - cwidth / 2) / tileSize;
		int rightTile = (int) (x - cwidth / 2 - 1) / tileSize;
		int topTile = (int) (y - cheight / 2) / tileSize;
		int bottomTile = (int) (y - cheight / 2 - 1) / tileSize;

		int tl = tileMap.getType(topTile, leftTile);
		int tr = tileMap.getType(topTile, rightTile);
		int bl = tileMap.getType(bottomTile, leftTile);
		int br = tileMap.getType(bottomTile, rightTile);

		topLeft = tl == Tile.BLOCKED;
		topRight = tr == Tile.BLOCKED;
		bottomLeft = bl == Tile.BLOCKED;
		bottomRight = br == Tile.BLOCKED;

	}

	public void checkTileMapCollision() {
		currCol = (int) x / tileSize;
		currRow = (int) y / tileSize;

		xdest = x + dx;
		ydest = y + dy;

		xtemp = x;
		ytemp = y;

		calculateCorners(x, ydest);
		if (dy < 0) {
			if (topLeft || topRight) {
				dy = 0;
				ytemp = currRow * tileSize + cheight / 2;
			} else {
				ytemp += dy;
			}
		}
		if (dy > 0) {
			if (bottomLeft || bottomRight) {
				dy = 0;
				falling = false;
				ytemp = (currRow + 1) * tileSize - cheight / 2;
			} else {
				ytemp += dy;
			}
		}
		calculateCorners(xdest, y);
		if (dx < 0) {
			if (topLeft || bottomLeft) {
				dx = 0;
				xtemp = currCol * tileSize + cwidth / 2;
			} else {
				xtemp += dx;
			}
		}
		if (dx > 0) {
			if (topRight || bottomRight) {
				dx = 0;
				xtemp = (currCol + 1) * tileSize - cwidth / 2;
			} else {
				ytemp += dy;
			}
		}

		if (!falling) {
			calculateCorners(x, ydest + 1);
			if (!bottomLeft && !bottomRight) {
				falling = true;
			}
		}

	}

	public int getX() {
		return (int) x;
	}

	public int getY() {
		return (int) y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getCwidth() {
		return cwidth;
	}

	public int getCheight() {
		return cheight;
	}
	
	public void setPosition(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public void setVector(double dx, double dy){
		this.dx = dx;
		this.dy = dy;
	}
	
	public void setMapPosition(){
		xmap = tileMap.getx();
		ymap = tileMap.gety();
	}

	public void setLeft(boolean b) {
		left = b;
	}
	public void setRight(boolean b) {
		right = b;
	}
	public void setUP(boolean b) {
		up = b;
	}
	public void setDown(boolean b) {
		down = b;
	}
	public void setJumping(boolean b) {
		jumping = b;
	}
	
	public boolean notOnScreen(){
		return x + xmap + width <0|| x + xmap -width> GamePanel.WIDTH|| y +ymap + height < 0 || y +ymap - height > GamePanel.HEIGHT;
	}

}
