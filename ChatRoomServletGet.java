package ua.kiev.prog;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 28.01.2017.
 */
public class ChatRoomServletGet extends HttpServlet{
    private ChatRoomMessageList chatRoomMessageList = ChatRoomMessageList.getInstance();
    private static List<ChatUser> roomList = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // приходит from который мы парсим, потому что from приходит как строка, а нам нужен Integer
        String login = req.getParameter("chatLogin");
        String chatRoom = req.getParameter("chatRoomName");

        if (login!=null&&chatRoom!=null)
        {
            roomList.add(new ChatUser(login, chatRoom, 0));
            resp.setStatus(200);
        }
        else resp.setStatus(404);
    }
}
