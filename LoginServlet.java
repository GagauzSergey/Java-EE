package ua.kiev.prog;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import javax.servlet.http.HttpServlet;

/**
 * Created by user on 21.01.2017.
 * Сервлет принимающий Get запрос проверяет зарегестрирован ли юзер и устанавливает статус ONLINE
 */

public class LoginServlet extends HttpServlet {

    boolean userPresent = false;
    private static List<ChatUser> registeredUsers = new ArrayList<>(); //лист с базой зарегестрированных юзеров

    {
        {

            registeredUsers.add(new ChatUser("admin", "admin"));
            registeredUsers.add(new ChatUser("bot", "bot"));
            registeredUsers.add(new ChatUser("user", "user"));
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String pass = req.getParameter("pass");

        for (ChatUser u : registeredUsers) {
            if (login.equals(u.getLogin()) && pass.equals(u.getPassword())) {
                userPresent = true;
                u.setOnlineStatus("ONLINE");
                return;

            }
        }

        if (userPresent = false) {
            resp.setStatus(200);
            HttpSession httpSession = req.getSession(true);
            httpSession.setAttribute(login, pass);
        } else
            resp.setStatus(404);
    }

    public static List<ChatUser> getRegisteredUsers() {
        return registeredUsers;
    }


}


