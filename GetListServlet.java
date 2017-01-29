package ua.kiev.prog;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
* Сервлет отвечающий за GET запрос
*/

public class GetListServlet extends HttpServlet {

	// ссылка у данного сервлета на SingleTon
	private MessageList msgList = MessageList.getInstance();
	private ChatRoomMessageList roomMsgList = ChatRoomMessageList.getInstance();

    @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    	// приходит from который мы парсим, потому что from приходит как строка, а нам нужен Integer

		String fromStr = req.getParameter("from");
		String fromRoom = req.getParameter("chatRoom");

		int from;
		try {
			from = Integer.parseInt(fromStr);
		} catch (Exception ex) {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
		}
		/*
		* С общего списка msgList сообщений в строку String json считываем всё от from до конца
		* и если строка получается не пустая мы её переводим в байты и отправляем клиенту
		* */
		String json;
		if (fromRoom==null){
		json = msgList.toJSON(from);}
		else json = roomMsgList.toJSON(from);

		if (json != null) {
			OutputStream os = resp.getOutputStream();
			/*
			* можем через передачу бинарных данных передавать не только текст,
			* но и фото, музыку и тд. Для только текста можно использовать
			* PrintWriter, но OutputStream и PrintWriter они взаимоисключающие, или то или то.
			* */
            byte[] buf = json.getBytes(StandardCharsets.UTF_8);
			os.write(buf);
		}
	}
}
