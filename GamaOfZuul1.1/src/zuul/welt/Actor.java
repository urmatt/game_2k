package zuul.welt;

import Enums.ZuulEnums;
import Enums.ZuulEnums.ActorStatus;

/**
 *
 */
public class Actor {
    String name;
    Room actualRoom;
    boolean isALive = true;
    ZuulEnums.ActorStatus status = ZuulEnums.ActorStatus.NORMAL;


    public Actor(String name, Room actualRoom) {
        setName(name);
        setActualRoom(actualRoom);
        actualRoom.addActor(this);
    }

    public void dad(){
        status = ZuulEnums.ActorStatus.DAD;
        isALive =false;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public Room getActualRoom() {
        return actualRoom;
    }

    public void setActualRoom(Room actualRoom) {
        this.actualRoom = actualRoom;
    }

    public String setStatus(ActorStatus status){
        String response = "";
        if(status == ActorStatus.DAD){
            dad();
            response = " is getoetet";
        }else if(status == ActorStatus.DAZZLED){
            this.status = status;
            response = " is geblended";
        }else if(status == ActorStatus.DISSOLVED){
            this.status = status;
            response = " is ausloesed";
        }else if(status == ActorStatus.HYPNOTIZED){
            this.status = status;
            response = " is hypnotized";
        }else if(status == ActorStatus.NORMAL){
            this.status = status;
            response = " is im normale Zustand";
        }else{
            return "Mit "+name+" nicht war passiert";
        }
        return name + response;
    }

    public ZuulEnums.ActorStatus getStatus() {
        return status;
    }
}
