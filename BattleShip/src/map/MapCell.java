package map;

public class MapCell {
	public enum CellState {
		NONE_STATE,
		SHIP_STATE,
		MISS_STATE,
		HIT_STATE,
		DESTROY_STATE,	
		ERROR_STATE,
	}
	
	protected CellState state;

	
	public MapCell() {
		init();
	}
	
	public void init() {
		this.state = CellState.NONE_STATE;
	}
	
	public CellState getState() {
		return state;
	}

	public void setState(CellState state) {
		this.state = state;
	} 

}
