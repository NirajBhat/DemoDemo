package com.nexttools.model;

import java.util.List;

/**
 * Created by next on 8/5/17.
 */
public class Tabs {
String title;
    String icon;
    String info;
    List<Cards> cardInfo;

    public Tabs(String title, String icon, String info, List<Cards> cardInfo) {
        this.title = title;
        this.icon = icon;
        this.info = info;
        this.cardInfo = cardInfo;
    }

    public Tabs() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<Cards> getCardInfo() {
        return cardInfo;
    }

    public void setCardInfo(List<Cards> cardInfo) {
        this.cardInfo = cardInfo;
    }
}
