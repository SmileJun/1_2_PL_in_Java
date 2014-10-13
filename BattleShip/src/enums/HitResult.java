package enums;

public enum HitResult {
	NONE, MISS, HIT, DESTROY;

	private String shipName;

	public String getName() {
		return shipName;
	}

	public void setName(String name) {
		this.shipName = name;
	}

	HitResult() {
		this("");
	}

	HitResult(String name) {
		this.shipName = name;
	}
}
