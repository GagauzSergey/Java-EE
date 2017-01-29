package ua.kiev.prog;

/**
 * Created by user on 21.01.2017.
 */
public class ChatUser {
    private String login;
    private String password;
    private String onlineStatus;
    private String userRoom;

    public ChatUser (String login, String password) {
        this.login=login;
        this.password = password;
    }

    public ChatUser (String login, String userRoom, int i){
        this.login=login;
        this.userRoom = userRoom;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(String onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public String getUserRoom() {
        return userRoom;
    }

    public void setUserRoom(String userRoom) {
        this.userRoom = userRoom;
    }
}
