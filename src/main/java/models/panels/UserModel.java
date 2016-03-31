package models.panels;

import models.InstallerModel;

/**
 * Created by eunderhi on 24/03/16.
 * Model that holds the admin user and password data
 */
public class UserModel extends InstallerModel {

    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
        notifyListeners();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyListeners();
    }

}
