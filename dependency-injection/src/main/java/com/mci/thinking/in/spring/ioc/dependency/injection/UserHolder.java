package com.mci.thinking.in.spring.ioc.dependency.injection;

import com.mci.thinking.in.spring.ioc.overview.dependency.domain.User;

/**
 * {@link User} Holder object
 * <p>
 * Ref. 55
 */
public class UserHolder {
    private User user;

    public UserHolder() {

    }

    public UserHolder(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                '}';
    }
}
