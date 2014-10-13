package enums;

public class Direction {
	public final static int DIRECTION_NUM = 4;
	
	enum DirectionType {
		DOWN, RIGHT, UP, LEFT;
	}

	private DirectionType dir;

	public DirectionType getDir() {
		return dir;
	}

	public void setDir(DirectionType dir) {
		this.dir = dir;
	}

	public void setDir(int idx) {		
		switch (idx) {
		case 0:
			dir = DirectionType.DOWN;
			break;
		case 1:
			dir = DirectionType.RIGHT;
			break;
		case 2:
			dir = DirectionType.UP;
			break;
		case 3:
			dir = DirectionType.LEFT;
			break;
		default:
			break;
		}
		
	}
	public void makeDxDyValueWithDir(int[] dx, int[] dy) {
		switch (dir) {
		case DOWN: 
			dx[0] = 1;
			dy[0] = 0;
			break;
		case RIGHT:
			dx[0] = 1;
			dy[0] = 0;
			break;
		case UP:
			dx[0] = 1;
			dy[0] = 0;
			break;
		case LEFT:
			dx[0] = 1;
			dy[0] = 0;
			break;
		default:
			dx[0] = 0;
			dy[0] = 0;
			break;
		}
	}
}
