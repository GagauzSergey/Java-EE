package UserForm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 18.01.2017.
 */
public class UserClass {
    private String firstName;
    private String secondName;
    private String age;
    private String firstQuestionAnswer;
    private String secondQuestionAnswer;
    private int countMarried;
    private int countHappy;


    /*
    * New user constructor
    * */
    public UserClass(String firstName, String secondName, String age, String firstQuestionAnswer, String secondQuestionAnswer) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.age = age;
        this.firstQuestionAnswer = firstQuestionAnswer;
        this.secondQuestionAnswer = secondQuestionAnswer;
    }

    /*
    * Users addition
    * */
    public void addUser(ArrayList<UserClass> users, UserClass user) {
        users.add(user);
    }

    /*
    * Calculation of married users
    * */
    public int marriedStatistic(ArrayList<UserClass> users) {
        for (UserClass u : users) {
            if (u.firstQuestionAnswer.equalsIgnoreCase("yes"))
                countMarried++;
        }
        return countMarried;
    }

    /*
    * Calculation of happy users
    * */
    public int happyStatistic(ArrayList<UserClass> users) {
        for (UserClass u : users) {
            if (u.secondQuestionAnswer.equalsIgnoreCase("yes"))
                countHappy++;
        }
        return countHappy;
    }

    /*
    * Calculation of all users
    * */
    public int generalUserQuantity(ArrayList<UserClass> users) {
        int generalUserQuantity = users.size();
        return generalUserQuantity;
    }
}
