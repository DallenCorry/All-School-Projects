package oddJob;
/*
  @author: Dallen Corry
 * @version: 1.1
 * @since: 2022/Nov/15
 * @created: 2022/Nov/08
 * Abstract Class: Defaults
 * */
import java.util.ArrayList;
import java.util.Random;

public abstract class Defaults {
    public static final String ICON_PATH = "images/OddJobIcon.png";
    public static final String LOGO_PATH = "images/OddJobLogo.png";
    public static final String IMAGE_NOT_FOUND = "images/imageNotFound.png";
    public static final String NO_PROFILE_PIC = "images/noProfilePic.png";
    public static final String STYLE_SHEET = "css/styles.css";
    public static final String USERS_FILE_PATH = "users.dat";
    public static final String JOBS_FILE_PATH = "jobs.dat";

    public static final int DEFAULT_HEIGHT = 600;
    public static final int DEFAULT_WIDTH = 450;

    /**
     * Returns a String of random ASCII characters ranging from A-Z and a-z.
     * The length of the String is chosen at random with a bound of 1-15 (inclusive).
     * @return String of random length (1-15 inclusive)
     */
    public static String randomString() {
        return randomString(new Random().nextInt(15)+1);
    }

    /**
     * Returns a String of random ASCII characters ranging from A-Z and a-z with length of len.
     * @param len the length of the String
     * @return a String of length len
     */
    public static String randomString(int len) {
        Random r = new Random();
        StringBuilder s= new StringBuilder();
        for (int i=0; i<len; i++) {
            int thing = (r.nextInt(58)+65);
            if (thing > 90 && thing < 97) {
                s.append('a');
            } else {
                s.append((char) thing);
            }
        }
        return s.toString();
    }

    public static User getUserFromID(int ID) {
        ArrayList<User> tmpUsers = driver.readUsers();
        User user = null;
        for(User u: tmpUsers) {
            if(u.getUserID() == ID) {
                user = u;
            }
        }
        if (user == null) {
            System.out.println("Could not find user with ID:"+ID);
        }
        return user;
    }
}
