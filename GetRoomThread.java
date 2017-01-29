package ua.kiev.prog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/*
* Данный класс запускает поток Thread, который будет постоянно запущен
* в фоне и запрашивать сервер есть ли новые сообщения на нём
* */

public class GetRoomThread implements Runnable {
    private final Gson gson;
    private int n; // это номер последнего прочитанного сообщения в общем списке на сервере MessageList msgList, на сервере тоже n
    private String roomName;

    // клиент будет запоминать сколько он прочитал сообщений
    public GetRoomThread() {
        gson = new GsonBuilder().create();
    }


    /*
    * Пока поток Thread не прерывался while ( ! Thread.interrupted()) создаём URL url = new UR
    * куда подставляем Utils.getURL() + "/get?from=" + n где n - номер последнего прочитанного сообщения.
    * то есть формируем параметры запроса
    * */
    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                System.out.print(".");
                URL url = new URL(Utils.getURL() + "/get?from=" + n + "&chatRoom=" + roomName);
                HttpURLConnection http = (HttpURLConnection) url.openConnection(); // выполняем запрос

                InputStream is = http.getInputStream(); // получаем ответ в виде InputStream
                try {
                    byte[] buf = responseBodyToArray(is);
                    String strBuf = new String(buf, StandardCharsets.UTF_8);

                    JsonMessages list = gson.fromJson(strBuf, JsonMessages.class);// пытаемся получить объёкт JsonMessages
                    if (list != null) {
                        for (Message m : list.getList()) {
                            System.out.println(m);
                            n++;
                        }
                    }
                } finally {
                    is.close();
                }

                Thread.sleep(500);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /*
    * Метод преобразовующий post запрос в массив байтов
    * */
    private byte[] responseBodyToArray(InputStream is) throws IOException {
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
