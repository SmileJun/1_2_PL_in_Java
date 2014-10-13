package map;

import map.MapCell.CellState;
import enums.*;

public class Map {
	public final static int START_ROW = 'a';
	public final static int START_COL = '1';
	public final static int ROW_SIZE = 8; // 'a'~'h'
	public final static int COL_SIZE = 8; // '1'~'8'
	public final static int END_ROW = START_ROW + ROW_SIZE - 1;
	public final static int END_COL = START_COL + COL_SIZE - 1;

	protected MapCell[][] cell;

	public Map() {
		cell = new MapCell[ROW_SIZE][COL_SIZE];
		
		for (int row = 0; row < ROW_SIZE; row++) {
			for (int col = 0; col < COL_SIZE; col++) {
				cell[row][col] = new MapCell();
			}
		}
	}

	public void init() {
		for (int row = 0; row < ROW_SIZE; row++) {
			for (int col = 0; col < COL_SIZE; col++) {
				cell[row][col].setState(CellState.NONE_STATE);
			}
		}
	}

	public boolean isPositionWithinBorders(Position pos) {
		if (pos.getX() < START_COL || pos.getX() > END_COL)
			return false;

		if (pos.getY() < START_ROW || pos.getY() > END_ROW)
			return false;

		return true;
	}
	public boolean isPositionWithinBorders(char x, char y) {
		Position pos = new Position(x, y);
		return isPositionWithinBorders(pos);
	}

	private boolean isValidPlacePosition(Position pos, Direction dir, int shipLength) {
		if (!isPositionWithinBorders(pos.getX(), pos.getY())) {
			return false;
		}
		
		int[] dx = { 0 };
		int[] dy = { 0 };

		dir.makeDxDyValueWithDir(dx, dy);
		
		// 참조로 인한 문제 때문에 Position객체를 하나 복사하여 사용한다.
		Position tempPos = new Position(pos);
		
		for (int i = 0; i < shipLength; i++) {
			if(tempPos.getX() < Map.START_COL || tempPos.getX() > Map.END_COL)
				return false;
			
			if(tempPos.getY() < Map.START_ROW || tempPos.getY() > Map.END_ROW)
				return false;
			
			MapCell currentCell = getCell(pos);
			if(!(currentCell.getState() == CellState.NONE_STATE)) {
				return false;
			}
			
			tempPos.setX((char)(tempPos.getX() + (char)dx[0]));
			tempPos.setY((char)(tempPos.getY() + (char)dy[0]));
		}

		return true;
	}
	public boolean isValidPlacePosition(char x, char y, Direction dir, int shipLength) {
		Position pos = new Position(x,y);
		return isValidPlacePosition(pos, dir, shipLength);
	}
	
	public boolean isValidAttackPos(Position pos) {
		if (!isPositionWithinBorders(pos)) {
			return false;
		}
		
		MapCell targetCell = getCell(pos);
		
		if (targetCell.getState() == CellState.NONE_STATE) {
			return true;
		}
			
		return false;
	}
	public boolean isValidAttackPos(char x, char y) {
		Position pos = new Position(x,y);
		return isValidAttackPos(pos);
	}
	
	public MapCell getCell(Position pos) {
		if (!isPositionWithinBorders(pos)) {
			return (MapCell)null;
		}
		return cell[pos.getX() - START_COL][pos.getY() - START_ROW];
	}
	public MapCell getCell(char x, char y) {
		Position pos = new Position(x,y);
		return getCell(pos);
	}

	public void drawMap() {
		System.out.printf("  ");
		for (int x = 0; x < COL_SIZE; x++) {
			System.out.printf("%-2c", START_COL + x);
		}
		System.out.println("");

		for (int y = 0; y < ROW_SIZE; y++) {
			for (int x = -1; x < COL_SIZE; x++) {
				if (x == -1) {
					System.out.printf("%-2c", START_ROW + y);
					continue;
				}

				MapCell tempCell = getCell((char) (x + START_COL),
						(char) (y + START_ROW));
				CellState tempCellState = tempCell.getState();

				switch (tempCellState) {
				case NONE_STATE:
					System.out.printf("%-2c", 'n');
					break;
				case SHIP_STATE:
					System.out.printf("%-2c", 's');
					break;
				case HIT_STATE:
					System.out.printf("%-2c", 'h');
					break;
				case MISS_STATE:
					System.out.printf("%-2c", 'm');
					break;
				case DESTROY_STATE:
					System.out.printf("%-2c", 'd');
					break;
				default:
					System.out.printf("%-2c", 'x');
					break;
				}
			}
			System.out.println("");
		}
	}
}
