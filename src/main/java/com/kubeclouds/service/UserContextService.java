
package com.kubeclouds.service;

import com.kubeclouds.domain.appdb.User;

public interface UserContextService {
    public User getUserFromContext();

    public void addUserToContext(User user, String password);
}
