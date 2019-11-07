package www.app.ypy.com.journalism_kotlin.base.bean;

/**
 * Created by ypu
 * on 2019/11/5 0005
 */
public class ConstellBean {

  private String name;
  private String datetime;

    /**
     * date : 20140627
     * all : 89%
     * color : 古铜色
     * health : 95%
     * love : 80%
     * money : 84%
     * number : 8
     * QFriend : 处女座
     * summary : 有些思考的小漩涡，可能让你忽然的放空，生活中许多的
     * work : 80%
     * error_code : 0
     */

    private String date;
    private double all;
    private String color;
    private String health;
    private String love;
    private String money;
    private String number;
    private String QFriend;
    private String summary;
    private String work;
    private int error_code;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getAll() {
        return all;
    }

    public void setAll(double all) {
        this.all = all;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public String getLove() {
        return love;
    }

    public void setLove(String love) {
        this.love = love;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }



    public String getQFriend() {
        return QFriend;
    }

    public void setQFriend(String QFriend) {
        this.QFriend = QFriend;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }
}
