package Mummi.Enums;


import Mummi.Mummi;

public enum Color {
    RED("red"),
    GOLDEN("golden"),
    WHITE("white"),
    //NONE("")
    ;

    private String  name;
    private Color(String name){this.name = name;}

    @Override
    public String toString() {
        return this.name;
    }
}

