package accountServer;

public class AccountServerController implements AccountServerControllerMBean{
    private final AccountServer accountServer;

    public AccountServerController(AccountServer accountServer) {
        this.accountServer = accountServer;
    }

    @Override
    public int getUsers() {
        return accountServer.getUserCount();
    }

    @Override
    public int getUsersLimit() {
        return accountServer.getUsersLimit();
    }

    @Override
    public void setUsersLimit(int usersLimit) {
        accountServer.setUserLimit(usersLimit);
    }
}
