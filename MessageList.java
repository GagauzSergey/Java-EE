package ua.kiev.prog;

import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/*
* Massage List Class это список сообщение выводимых на экран, установленных в очереди.
* В данном классе реализуется SingleTon. Для этого создан private объект MassageList чтоб
* в последствии никто не мог возмользоваться конструктором и создать объект класса через new. Вместо этого
* в статик поле private static final MessageList msgList = new MessageList() мы предсоздаём объект класса, то есть
* при загрузке класса у нас сразу же создаётся объект класса и мы static методом MessageList getInstance всем кто
* запрашивает возвращаем ссылку на уже созданный объект msgList. А новый никто создать не может.
* */

public class MessageList {
    private static final MessageList msgList = new MessageList();
    private static final int LIMIT = 100;

    private final Gson gson;

    // Лист наших сообщений
    private final List<Message> list = new LinkedList<Message>();

    public static MessageList getInstance() {
        return msgList;
    }

    private MessageList() {
        gson = new GsonBuilder().create();
    }

    //добавление сообщения в общий лист и следим за тем, чтобы не превышал лимит сообщений

public synchronized void add(Message m) {
	    if (list.size() + 1 == LIMIT) {
	      //  list.remove(0);
        }
		list.add(m);
	}

/*
* Возвращает часть сообщений после проверки на наличие новіх сообщений от флага n, а n-это
* номер с которого клиент хочет запросить сообщение. Если n равен длинне списка, то новых
* сообщений нет. Если есть, то мы это новое пакуем в пакет JsonMessages и его уже сериализируем.
* */
    public synchronized String toJSON(int n) {
        if (n == list.size()) return null;
        return gson.toJson(new JsonMessages(list, n));
    }
}
