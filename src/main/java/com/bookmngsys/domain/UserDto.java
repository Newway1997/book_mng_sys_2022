package com.bookmngsys.domain;

import com.bookmngsys.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

/**
 * @author Newway
 */
@Getter
@Setter
public class UserDto {

    private int id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 类型，普通用户1，管理员0
     */
    private int type = 1;
    /**
     * 状态，启用1，不启用0
     */
    private int status = 1;

    public static UserDto convertToUserDto(User user) {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        return userDto;
    }

    public static User convertToUser(UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        return user;
    }

    /**
     * 是否管理员
     * @return
     */
    public boolean isAdmin() {
        return this.type == 0;
    }
}
