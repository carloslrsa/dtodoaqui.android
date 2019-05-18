package com.miedo.detodoaqui.Data.Local;

import android.content.Context;
import android.content.SharedPreferences;

import com.miedo.detodoaqui.Data.User;

public class SessionManager {

    private static final String PREFERENCE_NAME = "DTodoAquiClient";

    // Singleton
    private static SessionManager instance = null;

    public static SessionManager getInstance(){
        if(instance == null){
            instance = new SessionManager();
        }
        return instance;
    }

    // String keys
    public static final String USER_NAME = "user_name";
    public static final String IS_LOGGED = "user_login";
    public static final String USER_PASSWORD = "user_pass";

    // Variables para acceder a las preferencias
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Context context;

    private User currentUser;

    private SessionManager() {
    }

    public void SetContext(Context context){
        this.context = context;
        this.preferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        this.editor = preferences.edit();
    }


    public User getCurrentUser(){
        return currentUser;
    }

    /**
     * Inicia sesion marcando el login a true
     * y seteando los datos de usuario en las preferencias
     *
     * @param user datos del login de usuario
     */
    public void StartSession(User user) {
        editor.putBoolean(IS_LOGGED, true);
        editor.putString(USER_NAME, user.getUsername());
        editor.putString(USER_PASSWORD, user.getPassword());
        editor.commit();
        currentUser = user;
    }

    /**
     * Elimina los datos ingresados del usuario, si los hubiera
     * y marca el login como false
     */
    public void CloseSession() {
        editor.putBoolean(IS_LOGGED, false);
        editor.remove(USER_NAME);
        editor.remove(USER_PASSWORD);
        editor.commit();
        currentUser = null;
    }


    /**
     * Funcion que devuelve los datos del usuario en forma de un objeto @{@link User}
     * @return Objeto @{@link User} con los datos de la sesion.
     */
    public User getCurrentSession() {
        User retorno = new User("","","");
        retorno.setUsername(preferences.getString(USER_NAME, ""));
        retorno.setPassword(preferences.getString(USER_PASSWORD, ""));

        return retorno;
    }

    /**
     * Verifica si hay un usuario loggeado
     *
     * @return true si la preferencia IS_LOGGED es true, false de lo contrario
     */

    public boolean isUserLogged() {
        return preferences.getBoolean(IS_LOGGED, false);
    }


}
