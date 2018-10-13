package com.czb.wechat.pojo;

public class MyFriends {
    private String id;

    private String myFriendId;

    private String myFriendUserId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getMyFriendId() {
        return myFriendId;
    }

    public void setMyFriendId(String myFriendId) {
        this.myFriendId = myFriendId == null ? null : myFriendId.trim();
    }

    public String getMyFriendUserId() {
        return myFriendUserId;
    }

    public void setMyFriendUserId(String myFriendUserId) {
        this.myFriendUserId = myFriendUserId == null ? null : myFriendUserId.trim();
    }
}