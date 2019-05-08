package com.miedo.detodoaqui;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;

public class ProfileFragmentDirections {
  private ProfileFragmentDirections() {
  }

  @NonNull
  public static NavDirections actionProfileToEstablishments() {
    return new ActionOnlyNavDirections(R.id.action_profile_to_establishments);
  }
<<<<<<< HEAD
=======

  @NonNull
  public static NavDirections actionProfileDestToRegisterUserActivity() {
    return new ActionOnlyNavDirections(R.id.action_profile_dest_to_registerUserActivity);
  }
>>>>>>> ba2c262796db46dc816a1bf0ec1363c8554f30f0
}
