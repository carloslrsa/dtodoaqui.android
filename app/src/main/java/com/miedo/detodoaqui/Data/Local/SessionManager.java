package com.miedo.detodoaqui.Data.Local;

import android.content.Context;
import android.content.SharedPreferences;

import com.miedo.detodoaqui.Data.User;

public class SessionManager {

    private static final String PREFERENCE_NAME = "DTodoAquiClient";

    // String keys
    public static final String USER_NAME = "user_name";
    public static final String IS_LOGGED = "user_login";
    public static final String USER_PASSWORD = "user_pass";

    // Variables para acceder a las preferencias
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Context context;

    public SessionManager(Context context) {
        this.context = context;
        this.preferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        this.editor = preferences.edit();
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

    }


    /**
     * Funcion que devuelve los datos del usuario en forma de un objeto @{@link User}
     * @return Objeto @{@link User} con los datos de la sesion.
     */
    public User getCurrentSession() {
        User retorno = new User();
        retorno.setUsername(preferences.getString(USER_NAME, "gaaaaa"));
        retorno.setPassword(preferences.getString(USER_PASSWORD, "elmacaco"));

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
