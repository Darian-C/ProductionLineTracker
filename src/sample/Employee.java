package sample;

public class Employee {
    private StringBuilder name;
    private String username;
    private String password;
    private String email;

    public void setName(StringBuilder name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private void createUsername(StringBuilder name) {

    }

    private boolean checkName(StringBuilder name) {
        return true;
    }

    private void setEmail() {
    }

    private boolean isValidPassword() {
        return true;
    }

    public Employee(String name, String password) {

    }
}
