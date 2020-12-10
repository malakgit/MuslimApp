package hamood.malak.muslimapp.MyUtils;

import java.util.Date;
import java.util.Hashtable;

public class MyPost
{
    private String Title;
    private String textmore;
    private int howmuchlike;
    private  Date datepost;
    private Hashtable<String ,String> hashtable; //comments

    public MyPost(){

    }

    public MyPost(String title, String textmore, int howmuchlike, Date datepost, Hashtable<String, String> hashtable) {
        Title = title;
        this.textmore = textmore;
        this.howmuchlike = howmuchlike;
        this.datepost = datepost;
        this.hashtable = hashtable;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getTextmore() {
        return textmore;
    }

    public void setTextmore(String textmore) {
        this.textmore = textmore;
    }

    public int getHowmuchlike() {
        return howmuchlike;
    }

    public void setHowmuchlike(int howmuchlike) {
        this.howmuchlike = howmuchlike;
    }

    public Date getDatepost() {
        return datepost;
    }

    public void setDatepost(Date datepost) {
        this.datepost = datepost;
    }

    public Hashtable<String, String> getHashtable() {
        return hashtable;
    }

    public void setHashtable(Hashtable<String, String> hashtable) {
        this.hashtable = hashtable;
    }

    @Override
    public String toString() {
        return "MyPost{" +
                "Title='" + Title + '\'' +
                ", textmore='" + textmore + '\'' +
                ", howmuchlike=" + howmuchlike +
                ", datepost=" + datepost +
                ", hashtable=" + hashtable +
                '}';
    }
}
