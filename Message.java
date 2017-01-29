package ua.kiev.prog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class Message {
    private Date date = new Date();
    private String from;
    private String to;
    private String text;
    private String chatRoom;
    private String userName;

    public Message(String userName) {
        this.userName = userName;
    }

    public Message(String from, String text) {
        this.chatRoom = " General chat";
        this.from = from;
        this.text = text;
        this.to = "All general chat users";
    }


    public Message(String from, String to, String text) {
        this.chatRoom = " General chat";
        this.from = from;
        this.text = text;
        this.to = to;
    }

    public Message(String chatRoom, String from, String text, int i) {
        this.from = from;
        this.to = "All Room Users";
        this.text = text;
        this.chatRoom = chatRoom;
    }

    public Message() {
    }

    /*
    * Сериализируем объект Massage в JSON для передачи Клиенту в этом формате
    * */
    public String toJSON() {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(this);
    }

    /*
    * Десериализация объекта Massage с JSON формата сообщения пришедшего от Клиента. То есть из JSON строки создать объект Massage!
    * */
    public static Message fromJSON(String s) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(s, Message.class);
    }


    /*
    * Создание вида сообщения отображаемое коиенту
    * */

    @Override
    public String toString() {

        return new StringBuilder().append("[").append(date).append("  ChatRoom: ").append(chatRoom)
                .append(", From: ").append(from).append(", To: ").append(to)
                .append("] ").append(text)
                .toString();
    }

	/*
    * Отправка сообщение, метод нужен только для клиента. Указываем URL и с помощью HttpURLConnection формируем POST запрос с данными и
	* в OutputStream os пишем сериализированные JSON данные и считываем статус код conn.getResponseCode().
	* */

    public int send(String url) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

        conn.setRequestMethod("POST");
        conn.setDoOutput(true);

        OutputStream os = conn.getOutputStream();
        try {
            String json = toJSON();
            os.write(json.getBytes(StandardCharsets.UTF_8));
            return conn.getResponseCode();
        } finally {
            os.close();
        }
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getChatRoom() {
        return chatRoom;
    }

    public void setChatRoom(String chatRoom) {
        this.chatRoom = chatRoom;
    }
}
