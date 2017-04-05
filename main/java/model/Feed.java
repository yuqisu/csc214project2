package model;

/**
 * Created by yuqisu on 3/27/17.
 */

public class Feed {

    private  String Email;
    private  String POST_DATE;
    private String FEED_PICTURE_PATH;
    private String CONTENT;

    public Feed(){

    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getCONTENT() {
        return CONTENT;
    }

    public void setCONTENT(String CONTENT) {
        this.CONTENT = CONTENT;
    }

    public String getFEED_PICTURE_PATH() {
        return FEED_PICTURE_PATH;
    }

    public void setFEED_PICTURE_PATH(String FEED_PICTURE_PATH) {
        this.FEED_PICTURE_PATH = FEED_PICTURE_PATH;
    }

    public String getPOST_DATE() {
        return POST_DATE;
    }

    public void setPOST_DATE(String POST_DATE) {
        this.POST_DATE = POST_DATE;
    }
}
