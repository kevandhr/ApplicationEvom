package com.milk.open.openmove21.model;

public class ModelMenuItem {

    private String seq;

    private String itemstr;

    private int imagerid;

    public ModelMenuItem(String seq, String itemstr, int imagerid){
        this.seq = seq;
        this.itemstr = itemstr;
        this.imagerid = imagerid;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getItemstr() {
        return itemstr;
    }

    public void setItemstr(String itemstr) {
        this.itemstr = itemstr;
    }

    public int getImagerid() {
        return imagerid;
    }

    public void setImagerid(int imagerid) {
        this.imagerid = imagerid;
    }
}
