package hamood.malak.muslimapp.MyUtils;

import java.util.Date;
import java.util.Hashtable;

public class MyPost
{
    private String Title;
    private String textmore;
    private  Date datepost;
    private String location;
    private String employee;
    private int code;
    private String key;// key: unique id for each object. have to be....
    private String image;


    private Hashtable<String ,String> hashtable; //comments

    public MyPost(){

    }

    public MyPost(String title, String textmore, int howmuchlike, Date datepost, Hashtable<String, String> hashtable) {
        Title = title;
        this.textmore = textmore;
        this.datepost = datepost;
        this.location=location;
        this.hashtable = hashtable;
        this.employee=employee;
        this.key=key;
        this.code=code;
        this.image=image;
    }
    // Getters & Setters

    public String getTitle() {
        return Title;
    }


    public void setTitle(String title) {
        Title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee){
        this.employee=employee;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTextmore() {
        return textmore;
    }

    public void setTextmore(String textmore) {
        this.textmore = textmore;
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

    public String getImage() {
        return image;
    }
    
    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "MyPost{" +
                "Title='" + Title + '\'' +
                ", textmore='" + textmore + '\'' +
                ", datepost=" + datepost +
                ", location='" + location + '\'' +
                ", employee='" + employee + '\'' +
                ", code=" + code +
                ", key='" + key + '\'' +
                ", image='" + image + '\'' +
                ", hashtable=" + hashtable +
                '}';
    }


}
