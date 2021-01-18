package org.example.accountServer;

public interface AccountServerControllerMBean {
    int getUsers();

    int getUsersLimit();

    void setUsersLimit(int usersLimit);
}
