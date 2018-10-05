package nl.han.oose;

public class AccountToken {

    private String token;
    private String user;

    public AccountToken(String token, String user) {

        this.token = token;
        this.user = user;
    }

    public AccountToken() {

    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
