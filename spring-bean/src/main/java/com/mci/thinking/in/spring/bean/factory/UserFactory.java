package com.mci.thinking.in.spring.bean.factory;

import com.mci.thinking.in.spring.ioc.overview.dependency.domain.User;

public interface UserFactory {
    default User createUser() {
        return User.createUser();
    }
}
