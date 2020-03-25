package pojo;

public class Login {
    private String email;
    private String password;

    public Login(){}

    public Login(String email, String password) {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static class Builder{
        private String email;
        private String password;

        public Builder() {
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public Login build(){
            return new Login(email, password);
        }
    }
}
