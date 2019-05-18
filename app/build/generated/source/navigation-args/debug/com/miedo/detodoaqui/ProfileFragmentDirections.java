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

  @NonNull
  public static NavDirections actionProfileDestToRegisterUserActivity() {
    return new ActionOnlyNavDirections(R.id.action_profile_dest_to_registerUserActivity);
  }

  @NonNull
  public static NavDirections actionProfileDestToLoginuserDest() {
    return new ActionOnlyNavDirections(R.id.action_profile_dest_to_loginuser_dest);
  }

  @NonNull
  public static NavDirections actionProfileDestToUpdateuserDest() {
    return new ActionOnlyNavDirections(R.id.action_profile_dest_to_updateuser_dest);
  }
}
