//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : PlayerToSpectator.java
//  @ Date : 5/27/2012
//  @ Author : @ThongTrH
//
//



package casino.cardgame.message.event;

import com.smartfoxserver.v2.core.ISFSEvent;
import com.smartfoxserver.v2.core.SFSEvent;
import com.smartfoxserver.v2.core.SFSEventParam;
import com.smartfoxserver.v2.core.SFSEventType;
import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.User;


public class PlayerToSpectator extends SFSGameEvent {
    private Room m_room;
    private User m_user;
    
    @Override
    public String GetEventName() {
        return SFSEventType.PLAYER_TO_SPECTATOR.toString();
    }
    
    @Override
    public SFSGameEvent FromSFSEvent(ISFSEvent isfse) {
        setM_room((Room) isfse.getParameter(SFSEventParam.ROOM));
        setM_user((User) isfse.getParameter(SFSEventParam.USER));
        return this;
    }

    /**
     * @return the m_room
     */
    public Room getM_room() {
        return m_room;
    }

    /**
     * @param m_room the m_room to set
     */
    public void setM_room(Room m_room) {
        this.m_room = m_room;
    }

    /**
     * @return the m_user
     */
    public User getM_user() {
        return m_user;
    }

    /**
     * @param m_user the m_user to set
     */
    public void setM_user(User m_user) {
        this.m_user = m_user;
    }
}