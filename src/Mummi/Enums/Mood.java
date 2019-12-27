package Mummi.Enums;

import Mummi.Mummi;

//used in artifact and Human_being
public enum Mood {
    MERRY("merry"),
    SAD("sad"),
    SORROWFUL("sorrowful"),
    NONE ("");

    private String name;
    Mood(String name){this.name = name;}


    @Override
    public String toString() {
        return this.name;
    }
}
