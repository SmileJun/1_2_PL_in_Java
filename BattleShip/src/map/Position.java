package map;

public class Position {	
	//members
	protected char x;
	protected char y;
	
	//constructor
	public Position() {
		this((char)0, (char)0);
	}
	
	public Position(char x, char y) {
		this.x = x;
		this.y = y;
	}
	
	public Position(Position src) {
		this.x = src.x;
		this.y = src.y;
	}
	
	//method
	public boolean hasValidPosition() {
		if(this.x > 0 || this.y > 0){
			return true;
		}
		
		return false;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	
	//getter, setter
	public char getX() {
		return x;
	}

	public void setX(char x) {
		this.x = x;
	}

	public char getY() {
		return y;
	}

	public void setY(char y) {
		this.y = y;
	}
}
