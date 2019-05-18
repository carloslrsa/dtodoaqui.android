package com.miedo.detodoaqui.Utils;

import android.util.Patterns;

import com.miedo.detodoaqui.Data.EstablishmentSearch;
import com.miedo.detodoaqui.Data.EstablishmentUser;
import com.miedo.detodoaqui.Data.Profile;
import com.miedo.detodoaqui.Data.User;

import java.util.regex.Pattern;

public class Validators {
    public static boolean Validate(Profile profile){
        return true;
    }

    public static boolean Validate(User user){
        if(user.getUsername().length() > 2 && user.getPassword().length() > 2 && validEmail(user.getEmail()))
            return true;
        return false;
    }

    private static boolean validEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    public static boolean Validate(EstablishmentUser establishmentUser){

        return true;
    }

    public static boolean Validate(EstablishmentSearch establishmentSearch){

        return  true;
    }
}
