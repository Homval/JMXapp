package org.example.accountServer;

public class AccountServerImpl implements AccountServer{
    private int usersLimit;
    private int userCount;

    public AccountServerImpl(int usersLimit) {
        this.usersLimit = usersLimit;
        userCount = 0;
    }

    @Override
    public void addNewUser() {
        userCount++;
    }

    @Override
    public void removeUser() {
        userCount--;
    }

    @Override
    public void setUserLimit(int usersLimit) {
        this.usersLimit = usersLimit;
    }

    @Override
    public int getUsersLimit() {
        return usersLimit;
    }

    @Override
    public int getUserCount() {
        return userCount;
    }
}
