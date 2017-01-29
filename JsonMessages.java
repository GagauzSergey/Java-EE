package ua.kiev.prog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/*
* Класс обёртка вокруг списка сообщений
* В Json должен быть беимянный объект а в этом объекте уже всё остальное
* */
public class JsonMessages {
    private final List<Message> list;

    public JsonMessages(List<Message> sourceList, int fromIndex) {
        this.list = new ArrayList<>();
        for (int i = fromIndex; i < sourceList.size(); i++)
            list.add(sourceList.get(i));
    }

    public List<Message> getList() {
        return Collections.unmodifiableList(list);
    }
}
