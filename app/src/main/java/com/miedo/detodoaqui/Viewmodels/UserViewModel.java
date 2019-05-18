package com.miedo.detodoaqui.Viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.miedo.detodoaqui.Data.Profile;
import com.miedo.detodoaqui.Data.User;
import com.miedo.detodoaqui.Models.UserModel;

public class UserViewModel extends ViewModel {

    public MutableLiveData<User> getUser() {
        return model.getLiveUser();
    }

    public UserModel model;

    public UserViewModel(){
        model = new UserModel();
    }

    public void RegisterUser(String username, String email, String password, String password_confirm){
        model.Register(username,email,password,password_confirm);
    }

    public void SincUser(String username, String password) {
        model.SincUser(username,password);
    }

    public void UpdateProfile(Profile profile){
        model.UpdateProfile(profile);
    }

    public void Logout(){
        model.Logout();
    }

}
