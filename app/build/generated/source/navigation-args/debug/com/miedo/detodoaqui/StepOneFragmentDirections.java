package com.miedo.detodoaqui;

import androidx.annotation.NonNull;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;

public class StepOneFragmentDirections {
  private StepOneFragmentDirections() {
  }

  @NonNull
  public static NavDirections nextAction() {
    return new ActionOnlyNavDirections(R.id.next_action);
  }
}
