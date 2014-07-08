package aorta.kr;

public enum KBType {

	BELIEF("bel"),
	ORGANIZATION("org"),
	OPTION("opt"),
    GOAL("goal");
	
	private String type;
	
	private KBType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
	
	public static KBType get(String qualifier) {
		for (KBType type : values()) {
			if (type.type.equals(qualifier)) {
				return type;
			}
		}
		return null;
	}
	
}
