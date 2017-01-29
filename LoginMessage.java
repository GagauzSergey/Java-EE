package ua.kiev.prog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class LoginMessage {
    private String login;
    private String pass;

    public LoginMessage(String login, String pass) {
        this.login = login;
        this.pass = pass;
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
    public static LoginMessage fromJSON(String s) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(s, LoginMessage.class);
    }
}

