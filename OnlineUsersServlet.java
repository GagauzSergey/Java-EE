package ua.kiev.prog;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by user on 24.01.2017.
 */
public class OnlineUsersServlet extends HttpServlet {
    // создаётся ссылка на SingleTon msgList
    private MessageList msgList = MessageList.getInstance();
    List<ChatUser> registeredUsers = LoginServlet.getRegisteredUsers();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
      if (registeredUsers != null)
            for (ChatUser u : registeredUsers) {
          if (u.getOnlineStatus()=="ONLINE")
                msgList.add(new Message(u.getLogin(), "online"));
                resp.setStatus(200);
            }
        else
            resp.setStatus(404);
    }
}
