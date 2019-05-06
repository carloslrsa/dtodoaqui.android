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
}
