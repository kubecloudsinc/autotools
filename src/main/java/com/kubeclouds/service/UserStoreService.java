
package com.kubeclouds.service;

import com.kubeclouds.domain.appdb.User;

public interface UserStoreService {
    public void store(User user, String password);
}
