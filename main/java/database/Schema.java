package database;

/**
 * Created by yuqisu on 3/27/17.
 */

public class Schema {
    public static final String DATABASE_NAME = "social.db";
    public static final int VERSION = 1;

    public static class User{
        public static final String USERS_NAME = "usersname";

        public static class Info{
            public static final String EMAIL = "email";
            public static final String FULL_NAME ="fullName";
            public static final String BIRTHDAY ="birthday";
            public static final String HOME_TOWN ="hometown";
            public static final String PASSWORD = "password";
            public static final String PICTURE_PATH = "picturePath";
            public static final String BIO = "bio";
        }

    }

    public static class Feed{
        public static final String FEEDS = "feeds";
        public static class Info{
            public static final String EMAIL = "email";
            public static final String POST_DATE ="postdate";
            public static final String FEED_PICTURE_PATH ="feedPicture";
            public static final String CONTENT ="content";

        }
    }

    public static class Favorite{
        public static final String FAVORITES = "favorites";
        public static class Info{
            public static final String EMAIL = "email";
            public static final String ID = "id";
            public static final String FAVORITE ="favorite";

        }
    }
}
