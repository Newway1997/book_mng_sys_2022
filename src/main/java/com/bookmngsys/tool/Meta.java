package com.bookmngsys.tool;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Newway
 */
@Getter
@Setter
@AllArgsConstructor
public class Meta {
    private boolean success;
    private String message;

    public Meta(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
