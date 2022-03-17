package com.bookmngsys.service.impl;

import com.bookmngsys.dao.UserDao;
import com.bookmngsys.entity.User;
import com.bookmngsys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Newway
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public User login(User user) {
        return userDao.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    @Override
    public boolean checkUserIsExist(String username) {
        return userDao.findByUsernameAndStatus(username,1) != null;
    }

    @Override
    public User getNormalUser(int id) {
        return userDao.getById(id);
    }

    @Override
    public boolean register(User user) {
        try {
            userDao.save(user);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public void updatePass(int id, String newPass) {
        User user = userDao.getById(id);
        user.setPassword(newPass);
        userDao.save(user);
    }

    @Override
    public void enableUser(int id) {
        userDao.updateUserStatus(id, 1);
    }

    @Override
    public void disableUser(int id) {
        userDao.updateUserStatus(id, 0);
    }
}
