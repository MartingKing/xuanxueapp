package com.bsyun.xuanxueapp;

public class PanEntity {

    private String dipangan;
    private String tianpangan;
    private String gongwei;
    private String jiuxing1; // for Tian Qin, can be null
    private String tiangan1; // for Tian Qin's stem, can be null
    private String jiuxing2; // main star
    private String bamen;
    private String bashen;
    private int makong; // for drawable resource ID, e.g., R.mipmap.empty_horse; could default to 0 or a specific 'no icon' value
    private String menke;
    private String xingke;
    private String changsheng;

    // Default constructor
    public PanEntity() {
    }

    // Getters and Setters
    public String getDipangan() {
        return dipangan;
    }

    public void setDipangan(String dipangan) {
        this.dipangan = dipangan;
    }

    public String getTianpangan() {
        return tianpangan;
    }

    public void setTianpangan(String tianpangan) {
        this.tianpangan = tianpangan;
    }

    public String getGongwei() {
        return gongwei;
    }

    public void setGongwei(String gongwei) {
        this.gongwei = gongwei;
    }

    public String getJiuxing1() {
        return jiuxing1;
    }

    public void setJiuxing1(String jiuxing1) {
        this.jiuxing1 = jiuxing1;
    }

    public String getTiangan1() {
        return tiangan1;
    }

    public void setTiangan1(String tiangan1) {
        this.tiangan1 = tiangan1;
    }

    public String getJiuxing2() {
        return jiuxing2;
    }

    public void setJiuxing2(String jiuxing2) {
        this.jiuxing2 = jiuxing2;
    }

    public String getBamen() {
        return bamen;
    }

    public void setBamen(String bamen) {
        this.bamen = bamen;
    }

    public String getBashen() {
        return bashen;
    }

    public void setBashen(String bashen) {
        this.bashen = bashen;
    }

    public int getMakong() {
        return makong;
    }

    public void setMakong(int makong) {
        this.makong = makong;
    }

    public String getMenke() {
        return menke;
    }

    public void setMenke(String menke) {
        this.menke = menke;
    }

    public String getXingke() {
        return xingke;
    }

    public void setXingke(String xingke) {
        this.xingke = xingke;
    }

    public String getChangsheng() {
        return changsheng;
    }

    public void setChangsheng(String changsheng) {
        this.changsheng = changsheng;
    }
}
