package UserManagement;

public class UserAuthentication {

    public boolean login(String username, String password) {
        // Simulated login logic
        return username.equals("user") && password.equals("pass");
    }

    public boolean logout(int userId) {
        // Simulated logout logic
        return userId > 0;
    }

    public boolean resetPassword(String email) {
        // Simulated password reset logic
        return email.contains("@");
    }
}