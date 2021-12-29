package com.ducnb.uigmail.data;

import java.io.Serializable;

public class Avatar implements Serializable {
    private String thumbnail;
    private String photo;

    public Avatar(String thumbnail, String photo) {
        this.thumbnail = thumbnail;
        this.photo = photo;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
