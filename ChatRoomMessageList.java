package ua.kiev.prog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by user on 28.01.2017.
 */
public class ChatRoomMessageList {
    private static final ChatRoomMessageList roomMsgList = new ChatRoomMessageList();
    private static final int LIMIT = 100;

    private final Gson gson;

    // Лист наших сообщений в чатруме
    private final List<Message> chatList = new LinkedList<Message>();

    public static ChatRoomMessageList getInstance() {
        return roomMsgList;
    }

    private ChatRoomMessageList() {
        gson = new GsonBuilder().create();
    }

    //добавление сообщения в общий лист и следим за тем, чтобы не превышал лимит сообщений
    public synchronized void add(Message m) {
        if (chatList.size() + 1 == LIMIT) {
            //  list.remove(0);
        }
        chatList.add(m);
    }

    /*
    * Возвращает часть сообщений после проверки на наличие новых сообщений от флага n, а n-это
    * номер с которого клиент хочет запросить сообщение. Если n равен длинне списка, то новых
    * сообщений нет. Если есть, то мы это новое пакуем в пакет JsonMessages и его уже сериализируем.
    * */
    public synchronized String toJSON(int n) {
        if (n == chatList.size()) return null;
        return gson.toJson(new JsonMessages(chatList, n));
    }
}
