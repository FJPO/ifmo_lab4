package Mummi.Enums;

import Mummi.Mummi;

public enum Race {
    MUMMI("Mummi"),
    HUMAN("Human"),
    NONE("");
    protected String name;
    Race(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
