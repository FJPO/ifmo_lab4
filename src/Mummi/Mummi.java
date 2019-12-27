package Mummi;

import Mummi.Enums.Race;

public class Mummi extends Human_being {
    public Mummi(String name, char sex) {
        super(name, Race.MUMMI, sex);
    }

    @Override
    public String describe() {
        return "a short creature with white skin, common habitation is a self-titled cartoon";
    }

}
