package accountServer;

public interface AccountServer {
    void addNewUser();

    void removeUser();

    void setUserLimit(int usersLimit);

    int getUsersLimit();

    int getUserCount();
}
