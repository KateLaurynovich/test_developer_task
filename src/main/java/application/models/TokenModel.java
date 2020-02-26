package application.models;

public class TokenModel {
    String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public TokenModel(String token) {
        this.token = token;
    }
}
