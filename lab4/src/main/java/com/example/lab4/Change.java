package com.example.lab4;

import java.io.Serializable;

public class Change implements Serializable {
    private int b1000, b500, b100, b20, b10, b5, b1;
    public Change(){
        this(0,0,0,0,0,0,0);
    }
    public Change(int b1000,int b500,int b100,int b20,int b10,int b5,int b1){
        this.b1000 = b1000;
        this.b500 = b500;
        this.b100 = b100;
        this.b20 = b20;
        this.b10 = b10;
        this.b5 = b5;
        this.b1 = b1;
    }

    public int getB1000() {
        return b1000;
    }

    public int getB500() {
        return b500;
    }

    public int getB100() {
        return b100;
    }

    public int getB20() {
        return b20;
    }

    public int getB10() {
        return b10;
    }

    public int getB5() {
        return b5;
    }

    public int getB1() {
        return b1;
    }


//    ---------
    public void setB1000(int b) {
        this.b1000 = b;
    }

    public void setB500(int b) {
        this.b500 = b;
    }

    public void setB100(int b) {
        this.b100 = b;
    }

    public void setB20(int b) {
        this.b20 = b;
    }

    public void setB10(int b) {
        this.b10 = b;
    }

    public void setB5(int b) {
        this.b5 = b;
    }

    public void setB1(int b) {
        this.b1 = b;
    }
}
