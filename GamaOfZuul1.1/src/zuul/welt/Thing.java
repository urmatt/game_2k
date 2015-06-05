package zuul.welt;

import Enums.ZuulEnums.ActorStatus;
import Enums.ZuulEnums.ThingType;

public class Thing {
	String name;
	boolean isUsed = false;
	ThingType type = ThingType.BOMBE;
	
	public Thing(String name, ThingType type) {
		setName(name);
		setType(type);
	}

    /**
     * Этот метод вызывается Player ом Например когда игрок кого то ослепляет.
     * @param actor - для кого должна быть использована данная вешь
     * @return текст которое должны сказать при использовании вешьи
     */
    public String useThingFor(Actor actor){
        String res = "";
        if(!isUsed) {
            if (type == ThingType.MEDICATION) {
                res = actor.setStatus(ActorStatus.NORMAL);
            } else if (type == ThingType.DAZZLE) {
                res = actor.setStatus(ActorStatus.DAZZLED);
            } else if (type == ThingType.HYPNOTIC) {
                res = actor.setStatus(ActorStatus.HYPNOTIZED);
            } else if (type == ThingType.DISSOLVENT) {
                res = actor.setStatus(ActorStatus.DISSOLVED);
            } else if (type == ThingType.BOMBE) {
                res = actor.setStatus(ActorStatus.DAD);
            }
            actor.lafeThisRoom();
            isUsed = true;
        }else{
            res = "Diese Dinge war schon benuzt";
        }
        return res;
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
