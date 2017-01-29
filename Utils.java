package ua.kiev.prog;

/*
* В класс Utils хардкоди айпишник URL = "http://127.0.0.1"
* PORT = 8080
* на основе этих констант генерится по запросу URL
* */

import sun.net.www.protocol.http.HttpURLConnection;

import java.io.IOException;
import java.net.URL;

public class Utils {
    private static final String URLto = "http://127.0.0.1";
    private static final int PORT = 8080;


    //метод нужен в случае замены значений URL и PORT на другие
    public static String getURL() {
        return URLto + ":" + PORT;
    }

    public static int sendReq(String parameters) {
        try {
            URL url = new URL(getURL() + parameters);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            return conn.getResponseCode();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
