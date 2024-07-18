package utils;

import entity.Employees;

public class UserSession {
	private static Employees loggedInUser;

    public static Employees getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(Employees employees) {
        loggedInUser = employees;
    }
}
