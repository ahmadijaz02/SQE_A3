package UserManagement;

public class UserProfile {

    public boolean updateProfile(int userId, String newProfileData) {
        // Simulated update logic
        return userId > 0 && !newProfileData.isEmpty();
    }

    public String getProfile(int userId) {
        // Simulated profile retrieval
        return userId > 0 ? "User Profile Data" : null;
    }
}
