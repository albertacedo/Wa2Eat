package logic;

import java.io.Serializable;

public class User implements Serializable {

    private String username;
    private String password;
    private String email;

    public User(String email, String password) {
        if (Util.isNotNullOrEmpty(email)) {
            setUsername(email);
        }

        if (Util.isNotNullOrEmpty(password)) {
            setPassword(password);
        }

        setEmail(email);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
