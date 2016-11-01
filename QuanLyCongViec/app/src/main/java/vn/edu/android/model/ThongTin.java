package vn.edu.android.model;

import java.io.Serializable;

/**
 * Created by Nguyen Trung Truc on 10/13/2016.
 */
public class ThongTin implements Serializable {

    private int id;
    private String username;
    private String password;

    public ThongTin() {
    }

    public ThongTin(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
