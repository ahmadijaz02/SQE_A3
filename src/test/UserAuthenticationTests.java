package test;

import java.io.IOException;
import java.util.List;

import org.apache.commons.io.filefilter.TrueFileFilter;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import UserManagement.UserAuthentication;
import UserManagement.UserProfile;

public class UserAuthenticationTests {

    UserAuthentication auth = new UserAuthentication();
    
    @DataProvider(name = "excelLoginData")
    public Object[][] getLoginData() throws IOException {
        List<Object[]> data = ExcelDataProvider.readExcelData("D:\\SQE_A3\\TestCases.xlsx", "Sheet1");
        Object[][] dataArray = new Object[data.size()][6];

        for (int i = 0; i < data.size(); i++) {
            dataArray[i] = data.get(i); // Fill the Object array with data from the List
        }

        return dataArray;
    }


    @Test(dataProvider = "excelLoginData", groups = "loginTests")
    public void testLoginWithExcelData(String testID, String function, String description, String username, String password, boolean expected) {
        Assert.assertEquals(auth.login(username, password), expected);
    }


    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][] {
            {"user", "pass", true},          // Valid credentials
            {"user", "wrong", false},        // Invalid password
            {"wrongUser", "pass", false},    // Invalid username
            {"", "pass", false},              // Empty username
            {"user", "", false},              // Empty password
            {"", "", false}                   // Both empty
        };
    }

    @Test(dataProvider = "loginData")
    public void testLogin(String username, String password, boolean expected) {
        boolean result = auth.login(username, password);
        Assert.assertEquals(result, expected, "Login test failed for username: " + username);
    }

    // Test cases for the logout method
    @Test
    public void testLogout() {
        Assert.assertTrue(auth.logout(1), "Logout should return true for valid userId");
        Assert.assertFalse(auth.logout(0), "Logout should return false for invalid userId");
    }

    // Test cases for the resetPassword method
    @DataProvider(name = "resetPasswordData")
    public Object[][] resetPasswordData() {
        return new Object[][] {
            {"@", true},      // Valid email
            {"user.com", false},              // Invalid email (missing @)
            {"@example.com", true},          // Invalid email (missing username)
            {"", false}                       // Empty email
        };
    }

    @Test(dataProvider = "resetPasswordData")
    public void testResetPassword(String email, boolean expected) {
        boolean result = auth.resetPassword(email);
        Assert.assertEquals(result, expected, "Reset password test failed for email: " + email);
    }
}

class UserProfileTests {

    UserProfile profile = new UserProfile();

    @Test
    public void testUpdateProfile() {
        Assert.assertTrue(profile.updateProfile(1, "New Data"));
    }

    @Test
    public void testGetProfile() {
        Assert.assertEquals(profile.getProfile(1), "User Profile Data");
    }
}
