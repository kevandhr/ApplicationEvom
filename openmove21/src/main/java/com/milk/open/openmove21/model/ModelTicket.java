package com.milk.open.openmove21.model;

public class ModelTicket {

    private String ticketid;
    private String state;
    private String scope;
    private String timelimit;
    private double money;

    public ModelTicket(String ticketid, String state, String scope, String timelimit, double money){
        this.ticketid = ticketid;
        this.state = state;
        this.scope = scope;
        this.timelimit = timelimit;
        this.money = money;
    }

    public String getTicketid(){
        return ticketid;
    }

    public void setTicketid(String ticketid){
        this.ticketid = ticketid;
    }
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getTimelimit() {
        return timelimit;
    }

    public void setTimelimit(String timelimit) {
        this.timelimit = timelimit;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getMoneystr() {
        String result = String.format("%.2f €", money);
        return result;
    }
}
