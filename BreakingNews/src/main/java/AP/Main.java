package AP;

public class Main {
    public static void main(String[] args) {

        String APIKEY = "807e1f7346ce4efbaba7eefa9a4d562b";
        Infrastructure  myIN = new Infrastructure(APIKEY);
        myIN.displayNewsList();
    }
}