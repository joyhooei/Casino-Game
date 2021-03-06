//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : casino project
//  @ File Name : UserController.java
//  @ Date : 5/27/2012
//  @ Author : sangdn
//  @ Description: UserController is used for handle all user's events
//      - Login     : check if user is valid
//      - Logout    :
//      - JoinZone  : update UserInfo as UserVariable(s)
//
package casino.cardgame.controller.user;

import casino.cardgame.entity.UserInfo;
import casino.cardgame.game_enum.UserInfoParams;
import casino.cardgame.message.event.Login;
import casino.cardgame.message.event.SFSGameEvent;
import casino.cardgame.message.event.UserJoinZone;
import casino.cardgame.utils.GlobalValue;
import casino.cardgame.utils.Logger;
import casino.cardgame.utils.ZLogger;
import com.smartfoxserver.bitswarm.sessions.ISession;
import com.smartfoxserver.bitswarm.sessions.Session;
import com.smartfoxserver.v2.core.SFSEventParam;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.entities.variables.UserVariable;
import com.smartfoxserver.v2.exceptions.SFSErrorCode;
import com.smartfoxserver.v2.exceptions.SFSErrorData;
import com.smartfoxserver.v2.exceptions.SFSLoginException;
import com.smartfoxserver.v2.security.DefaultPermissionProfile;
import com.smartfoxserver.v2.util.CryptoUtils;
import java.util.ArrayList;

public class UserController implements IUserController {

    protected static UserController m_instance;

    protected UserController() {
    }

    @Override
    public void HandleUserLogin(SFSGameEvent sfse) throws SFSLoginException {

        Login evt = (Login) sfse;
        String username = evt.getM_name();
        String password = evt.getM_password();
        String LoginRole = evt.getM_inData().getUtfString("role");

        if ("admin".equalsIgnoreCase(LoginRole)) {
            processAdminLogin(username, password, evt.getM_session(), evt.getM_outData());
        } else {
            processUserLogin(username, password, evt.getM_session(), evt.getM_outData());
        }
    }

    private void processAdminLogin(String username, String password, ISession session, ISFSObject outdata) throws SFSLoginException {
        String pass = GlobalValue.dataProxy.GetAdminPassword(username);

        SFSErrorData sfsLoginError;
        if (pass == null) {
            sfsLoginError = new SFSErrorData(SFSErrorCode.LOGIN_BAD_USERNAME);
            sfsLoginError.addParameter(username);
            throw new SFSLoginException("Your usename is not valid", sfsLoginError);
        } else if (!GlobalValue.sfsServer.getApi().checkSecurePassword(session, pass, password)) {
            sfsLoginError = new SFSErrorData(SFSErrorCode.LOGIN_BAD_PASSWORD);
            sfsLoginError.addParameter(username);
            throw new SFSLoginException("Your password is incorrect", sfsLoginError);
        }
        session.setProperty("$permission", DefaultPermissionProfile.ADMINISTRATOR);
    }

    private void processUserLogin(String username, String password, ISession session, ISFSObject outdata) throws SFSLoginException {
        String pass = GlobalValue.dataProxy.GetUserPassword(username);

        SFSErrorData sfsLoginError;
        if (pass == null) {
            sfsLoginError = new SFSErrorData(SFSErrorCode.LOGIN_BAD_USERNAME);
            sfsLoginError.addParameter(username);
            throw new SFSLoginException("Your usename is not valid", sfsLoginError);
        } else if (!GlobalValue.sfsServer.getApi().checkSecurePassword(session, pass, password)) {
            sfsLoginError = new SFSErrorData(SFSErrorCode.LOGIN_BAD_PASSWORD);
            sfsLoginError.addParameter(username);
            throw new SFSLoginException("Your password is incorrect", sfsLoginError);
        }
        session.setProperty("$permission", DefaultPermissionProfile.STANDARD);

        UserInfo info = GlobalValue.dataProxy.GetUserInfo(username);
        double chip = 0.0;
        String avatar = "";
        if (info != null) {
            chip = info.getChip();
            avatar = info.getAvartar();
        }
        outdata.putDouble("chip", chip);
        outdata.putUtfString("avatar", avatar);
    }

    @Override
    public void HandleUserLogout(SFSGameEvent igame) {
    }

    @Override
    public void HandleUserJoinZone(SFSGameEvent joinZoneEvt) {
        //Update UserVariable 
        UserJoinZone evt = (UserJoinZone) joinZoneEvt;
        User user = evt.getM_user();
        String userName = user.getName();

        UserInfo userInfo = GlobalValue.dataProxy.GetUserInfo(userName);
        GlobalValue.sfsServer.getApi().setUserVariables(user, userInfo.ToUserVariables(), true, true);
    }

    public static UserController getInstance() {
        if (m_instance == null) {
            m_instance = new UserController();
        }
        return m_instance;
    }
}
