package com.miedo.detodoaqui.Data.Local;

import com.miedo.detodoaqui.Data.User;

public class SessionManager {

    // String keys
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";

    public static final String ISLOGGED = "islogged";
    public static final String JWT = "jwt";

    //Singleton
    private static SessionManager _instance = null;

    public static SessionManager getInstance() {
        if (_instance == null) {
            _instance = new SessionManager();
        }
        return _instance;
    }

    private SessionManager() {
    }
    //SingletonEnd

    private User activeUser;

    private void LookForStoredSession() {

    }

    public void StartSession(User user) {
        activeUser = user;
        //Start session
    }

    public void CloseSession() {
        if (activeUser != null) {
            //Close session
        }
    }

    public User getActiveUser() {
        return activeUser;
    }

}
