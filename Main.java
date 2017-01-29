package ua.kiev.prog;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int responseCod;
        Scanner scanner = new Scanner(System.in);
        String login;
        String to;
        String pass;
        String text;
        String messageText;

/*
* Вводим логин и пароль до тех пор пока не будут введены правильные значения
* */
        do {
            System.out.println("Enter your login: "); //вводим свой логин
            login = scanner.nextLine();

            if (login.isEmpty()) return;

            System.out.println("Enter your password: "); //вводим свой пароль
            pass = scanner.nextLine();

            if (pass.isEmpty()) return;

            responseCod = Utils.sendReq("/login?login=" + login + "&pass=" + pass);
            System.out.println(responseCod);
        }
        while (responseCod != 200);


        System.out.println(login + " !!! ");
        Thread th = new Thread(new GetThread()); //запускаем в виде демона поток для проверки новых сообщений
        th.setDaemon(true);
        th.start();


/*
Ввод сообщения другому пользователю
*/
        System.out.println("Please, choose next MENU command: ");
        System.out.println("[MESSAGE TO ALL], [PRIVATE MESSAGE], [GET ALL REGISTERED USERS]," +
                "[GET ALL ONLINE USERS], [CREATE CHAT_ROOM]: ");
        System.out.println("Enter your message: ");//вводим текст сообщения

        while (true) {
            try {
                text = scanner.nextLine();
                if (text.isEmpty()) break; //если сообщение пустое, то завершаем клиент
                switch (text) {

                    case "PRIVATE MESSAGE":
                        System.out.println("Enter message receiver");
                        to = scanner.nextLine();
                        while (true) {
                            System.out.println("Enter message");
                            messageText = scanner.nextLine();
                            Message m1 = new Message(login, to, messageText); // если есть сообщение то создаём объект Message
                            int res1 = m1.send(Utils.getURL() + "/add");// и отправляем его по url: Utils.getURL() + "/add"
                            if (res1 != 200) { //если 20Ок не пришло, то выводим ошибку сервера на экран
                                System.out.println("HTTP error occurred: " + res1);
                            }
                            break;
                        }

                    case "MESSAGE TO ALL":
                        System.out.println("Enter message");
                        messageText = scanner.nextLine();
                        Message m2 = new Message(login, messageText); // если есть сообщение то создаём объект Message

                        int res2 = m2.send(Utils.getURL() + "/add");// и отправляем его по url: Utils.getURL() + "/add"
                        if (res2 != 200) { //если 20Ок не пришло, то выводим ошибку сервера на экран
                            System.out.println("HTTP error occurred: " + res2);
                        }
                        break;

                    case "GET ALL REGISTERED USERS":
                        int res3 = Utils.sendReq("/registered");// GET запрос на url: Utils.getURL() + "/registered"
                        System.out.println(res3);
                        break;

                    case "GET ALL ONLINE USERS":
                        int res4 = Utils.sendReq("/online"); // GET запрос на url: Utils.getURL() + "/online"
                        System.out.println(res4);
                        break;

                    case "CREATE CHAT_ROOM":
                        System.out.println("Enter the name of Chat Room:");
                        String chatLogin = login;
                        String chatRoomName = scanner.nextLine();

                        responseCod = Utils.sendReq("/chat_room_get?chatLogin=" +
                                chatLogin + "&chatRoomName=" + chatRoomName); // GET запрос на url: Utils.getURL() + chatLogin +chatRoomName
                        System.out.println("Room cod: " + responseCod);

                        System.out.println("Enter message");
                        messageText = scanner.nextLine();
                        Message m5 = new Message(chatRoomName, chatLogin, messageText, 1); // если есть сообщение то создаём объект Message
                        int res5 = m5.send(Utils.getURL() + "/chat_room_post");// и отправляем его по url: Utils.getURL() + "/chat_room_post"
                        if (res5 != 200) { //если 20Ок не пришло, то выводим ошибку сервера на экран
                            System.out.println("HTTP error occurred: " + res5);
                        }
                        new Message().setChatRoom(chatRoomName);
                        Thread th2 = new Thread(new GetRoomThread()); //запускаем в виде демона поток для проверки новых сообщений
                        th2.setDaemon(true);
                        th2.start();
                        break;
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}

