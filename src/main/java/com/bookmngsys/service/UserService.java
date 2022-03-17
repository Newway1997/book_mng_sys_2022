package com.bookmngsys.service;

import com.bookmngsys.entity.User;

/**
 * @author Newway
 */
public interface UserService {
    /**
     * 检查用户名是否存在
     * @param username 用户名
     * @return 用户名是否存在
     */
    boolean checkUserIsExist(String username);

    /**
     * 注册
     * @param user 用户信息
     * @return 注册结果
     */
    boolean register(User user);

    /**
     * 登录
     * @param user 用户信息
     * @return 用户信息
     */
    User login(User user);

    /**
     * 获取状态正常的用户
     * @param id id
     * @return 用户
     */
    User getNormalUser(int id);

    /**
     * 更新密码
     * @param id 用户id
     * @param newPass 新密码
     */
    void updatePass(int id, String newPass);

    /**
     * 启用用户
     * @param id 用户id
     */
    void enableUser(int id);

    /**
     * 禁用用户
     * @param id 用户id
     */
    void disableUser(int id);
}
