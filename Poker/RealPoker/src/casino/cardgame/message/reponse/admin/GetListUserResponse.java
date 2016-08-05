//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : casino project
//  @ File Name : Notify_Start.java
//  @ Date : 6/7/2012
//  @ Author : sangdn
//  @ Description : send init cards for each player
//
//
package casino.cardgame.message.reponse.admin;

import casino.cardgame.entity.UserInfo;
import casino.cardgame.message.reponse.game.poker.*;
import casino.cardgame.message.reponse.game.tala.*;
import com.smartfoxserver.v2.entities.data.ISFSArray;
import com.smartfoxserver.v2.entities.data.SFSArray;
import com.smartfoxserver.v2.entities.data.SFSObject;
import java.util.ArrayList;

public class GetListUserResponse extends casino.cardgame.message.reponse.SFSGameReponse {

    private ArrayList<UserInfo> _listUser; 
    private ISFSArray _arrUsers;
    
    public GetListUserResponse() {
        super(ADMIN_RESPONSE_TYPE.GET_LIST_USER_RES);
    }

//    public SFSObject ToSFSObject() {
//        SFSObject obj = new SFSObject();
//        
//        SFSArray sfsArrUsers = new SFSArray();
//        for(int i = 0; i< _listUser.size(); i++){
//            SFSObject sfsUser = new SFSObject();
//            UserInfo user = _listUser.get(i);
//            sfsUser.putUtfString("user_name", user.getUserName());
//            sfsUser.putUtfString("display_name", user.getDisplayName());
//            sfsUser.putUtfString("password", user.getPassWord());
//            sfsUser.putUtfString("email", user.getEmail());
//            sfsUser.putDouble("chip", user.getChip());
//            sfsUser.putDouble("tour_chip", user.getTourChip());
//            sfsUser.putDouble("buy_in", user.getBuyIn());
//            sfsUser.putUtfString("location", user.getLocation());
//            sfsUser.putUtfString("avartar", user.getAvartar());
//            sfsUser.putUtfString("status", user.getUserStatus());
//            sfsUser.putUtfString("title", user.getUserTitle());
//            
//            sfsArrUsers.addSFSObject(sfsUser);
//        }
//        
//        obj.putSFSArray("list_user", sfsArrUsers);
//        return obj;
//    }

    public SFSObject ToSFSObject() {
        SFSObject obj = new SFSObject();
        
        obj.putSFSArray("list_user", _arrUsers);
        return obj;
    }
    
    public void setListUser(ArrayList<UserInfo> listUser) {
        this._listUser = listUser;
    }

    public void setArrUsers(ISFSArray arrUsers) {
        this._arrUsers = arrUsers;
    }

}