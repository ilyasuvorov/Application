package com.example.bober.myapplication.db;
import java.util.List;

import com.example.bober.myapplication.app.App;
import  com.example.bober.myapplication.db.models.User;
public class DbService {

    public void createUser(String email, String pass) {
        App.getDbSession().boxFor(User.class).put(getCreatedUser(email, pass));
    }

    public void updateUser(User user) {
        App.getDbSession().boxFor(User.class).put(user);
    }

    public void deleteUser() {
        App.getDbSession().boxFor(User.class).removeAll();
    }

    private static final int EMPTY_DB = 0;
    private static final int FIRST_ENTITY = 0;
    public User readUser() {
        List<User> users = App.getDbSession().boxFor(User.class).getAll();
        if (users.size() == EMPTY_DB) {
            return null;
        } else {
            return users.get(FIRST_ENTITY);
        }
    }

    private User getCreatedUser(String email, String pass) {
        User user = new User();
        user.setEmail(email);
        user.setPass(pass);
        user.setActive(true);

        return user;
    }

}
