package com.example.hadeel.samples;

import android.graphics.Bitmap;
import android.os.Bundle;

/**
 * Created by hadeel on 9/10/2017.
 */

public class BottomItem {
    private int image,imageBackGround;

    public int getImageBackGround() {
        return imageBackGround;
    }

    public void setImageBackGround(int imageBackGround) {
        this.imageBackGround = imageBackGround;
    }


    private String title,counterComment;

    public String getCounterComment() {
        return counterComment;
    }

    public void setCounterComment(String counterComment) {
        this.counterComment = counterComment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    private String comment;

    public int getImage() {

        return image;
    }
    public void setImage(int image) {
        this.image = image;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
}

