package ua.kiev.prog;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 28.01.2017.
 */
public class ChatRoomServletPost extends HttpServlet {

    private ChatRoomMessageList roomMsgList = ChatRoomMessageList.getInstance();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        byte[] buf = requestBodyToArray(req);
        String bufStr = new String(buf, StandardCharsets.UTF_8);

        Message msg = Message.fromJSON(bufStr);
        if (msg != null)
            roomMsgList.add(msg);
        else
            resp.setStatus(/*HttpServletResponse.SC_BAD_REQUEST*/402);
    }
    //SC_BAD_REQUEST статик константа для ответа статус кодом

    /*
    * Данный метод позволяет преобразовать Body пост запроса в массив байтов. На вход подаётся HttpServletRequest req
    * и для Request мы вызываем метод Input Stream и нам тело запроса будет доступно как
    * бинарный стрим. В нашем теле JSON лежит. Так как стримы безразмерные и незьля вычислить его размер, то мы должны
    * со стрима вычитывать до тех пор пока стрим не вернёт -1 (по техдокументации) и вычитываем по 10кБ
    * byte[] buf = new byte[10240]. Если r > 0 мы прочитанные данные складываем в временный ByteArrayOutputStream bos и
    * в итоге всё что считали возвращаем как return bos.toByteArray();
    * */

    private byte[] requestBodyToArray(HttpServletRequest req) throws IOException {
        InputStream is = req.getInputStream();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[10240];
        int r;

        do {
            r = is.read(buf);
            if (r > 0) bos.write(buf, 0, r);
        } while (r != -1);

        return bos.toByteArray();
    }
}

