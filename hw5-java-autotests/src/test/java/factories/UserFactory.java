package factories;

import models.User;

public class UserFactory {
    public static User standardUser() {
        return new User("standard_user", "secret_sauce");
    }

    public static User lockedOutUser() {
        return new User("locked_out_user", "secret_sauce");
    }
}
