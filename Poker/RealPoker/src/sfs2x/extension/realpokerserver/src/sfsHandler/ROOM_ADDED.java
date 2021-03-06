//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : casino project
//  @ File Name : ROOM_ADDED.java
//  @ Date : 5/27/2012
//  @ Author : sangdn
//
//



package sfs2x.extension.realpokerserver.src.sfsHandler;

import casino.cardgame.controller.game.table.TaLaController;
import casino.cardgame.message.event.RoomAdded;
import casino.cardgame.utils.GlobalValue;
import casino.cardgame.utils.Logger;
import com.smartfoxserver.v2.core.ISFSEvent;
import com.smartfoxserver.v2.entities.variables.RoomVariable;
import com.smartfoxserver.v2.entities.variables.SFSRoomVariable;
import com.smartfoxserver.v2.extensions.BaseServerEventHandler;
import java.util.List;


public class ROOM_ADDED  extends BaseServerEventHandler{
        @Override
	public void handleServerEvent(ISFSEvent isfse) {
            try{
                Logger.trace("Enter Room_Added Event");
                GlobalValue.serverHandler.HandleRoomAdded(isfse);
            }catch(Exception ex){
                Logger.error(this.getClass(), ex);
            }
	}
}
