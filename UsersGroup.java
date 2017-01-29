package ua.kiev.prog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 27.01.2017.
 */
public class UsersGroup {

    private static final String onlineStatus = "ONLINE";
    private static final String offlineStatus = "OFFLINE";
    private List<ChatUser> chatUserList = new ArrayList<>();
    public UsersGroup (){}


    public boolean addUserInUserGroup (ChatUser chatUser){
     chatUserList.add(chatUser);
     return true;
    }

    public static String getOnlineStatus() {
        return onlineStatus;
    }


    public static String getOfflineStatus() {
        return offlineStatus;
    }

    public List<ChatUser> getChatUserList() {
        return chatUserList;
    }

    public void setChatUserList(List<ChatUser> chatUserList) {
        this.chatUserList = chatUserList;
    }


}
