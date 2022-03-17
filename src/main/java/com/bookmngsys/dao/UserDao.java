package com.bookmngsys.dao;

import com.bookmngsys.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Newway
 */
public interface UserDao extends JpaRepository<User, Integer> {
    @Transactional
    @Modifying
    @Query(value = "update user set status=:status where id=:id")
    void updateUserStatus(int id, int status);

    User findByUsernameAndPassword(String username, String password);


    User findByUsernameAndStatus(String username, int i);
}
