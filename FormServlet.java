package UserForm;
/**
 * Created by user on 17.01.2017.
 */

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;



@SuppressWarnings("serial")
public class FormServlet extends HttpServlet {

    private static final String TEMPLATE = "<html>" +
            "<head><title>Happy User Form</title></head>" +
            "<body><h1>%s</h1></body></html>";

    private static final String anwerYes = "yes";
    private static final String anwerNo = "no";

    private ArrayList<UserClass> users = new ArrayList<>();

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String firstName = req.getParameter("first name");
        String lastName = req.getParameter("last name");
        String userAge = req.getParameter("age");
        String firstUserAnswer = req.getParameter("married");
        String secondUserAnswer = req.getParameter("happy");
        String msg1, msg2, msg3, msg4, msg5, msg6;

        /*
        * Happiness definition
        * */
        if (firstUserAnswer.equalsIgnoreCase(anwerYes) && secondUserAnswer.equalsIgnoreCase(anwerYes))
            msg3 = "You are married and happy!";
        else if (firstUserAnswer.equalsIgnoreCase(anwerNo) && secondUserAnswer.equalsIgnoreCase(anwerYes))
            msg3 = "You are lucky and happy but why you still alone?";
        else if (firstUserAnswer.equalsIgnoreCase(anwerNo) && secondUserAnswer.equalsIgnoreCase(anwerNo))
            msg3 = "You unhappy, so go for a walk...";
        else
            msg3 = "I'm so sorry but it is you fault(";

        msg1 = "Congratulations, " + firstName + " " + lastName + "!";
        msg2 = "You are " + userAge + " old";

        /*
        * Calculations of different quantities for statistic
        * */
        UserClass newUser = new UserClass(firstName, lastName, userAge, firstUserAnswer, secondUserAnswer);
        newUser.addUser(users, newUser);

        int marriedUsers = newUser.marriedStatistic(users);
        int happyUsers = newUser.happyStatistic(users);

        msg4 = "Happy users quantity: " + happyUsers;
        msg5 = "Married users quantity: " + marriedUsers;
        msg6 = "General quantity: " + newUser.generalUserQuantity(users);

        /*
        * Response from server with general answers and statistic
        * */
        resp.getWriter().println(String.format(TEMPLATE, msg1));
        resp.getWriter().println(String.format(TEMPLATE, msg2));
        resp.getWriter().println(String.format(TEMPLATE, msg3));
        resp.getWriter().println(String.format(TEMPLATE, msg4));
        resp.getWriter().println(String.format(TEMPLATE, msg5));
        resp.getWriter().println(String.format(TEMPLATE, msg6));
    }
}
