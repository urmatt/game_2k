package zuul.welt;

import Enums.ZuulEnums.ThingType;

public class Thing {
	String name;
	boolean isUsed = false;
	ThingType type;
	
	public Thing(String name, ThingType type) {
		setName(name);
		setType(type);
	}

	public String getName() {
		return name;
	}
	
	public void makeUsed(){
		setUsed(true);
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isUsed() {
		return isUsed;
	}

	public void setUsed(boolean isUsed) {
		this.isUsed = isUsed;
	}

	public ThingType getType() {
		return type;
	}

	public void setType(ThingType type) {
		this.type = type;
	}
	
	
}
