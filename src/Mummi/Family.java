package Mummi;

import Mummi.Exceptions.NoPlaceException;

import java.lang.reflect.*;

public class Family {
    private Human_being[] members;
    private Place place;
    public Family(String scenarioName, Place place){
        Init(scenarioName);
        this.place = place;
    }

    public Human_being[] getMembers() {
        return members;
    }

    public void Init(String ScenarioName) {
        class Scenarios {
            Place place;
            public Scenarios(Place place){
                this.place = place;
            }
            public void zero() {
                int num = 0;
                members = new Human_being[3];

                members[0] = new Mummi("Father", 'M');
                members[0].addArtifact(new Artifact("Hat"));
                members[1] = new Mummi("Mother", 'F');
                members[2] = new Mummi("Son", 'M');

                try {
                    place = null;
                    members[0].moveTo(place);
                    members[1].moveTo(place);
                    members[2].moveTo(place);
                } catch (NoPlaceException e) {
                    place = (new The_World()).getPlaces()[0];
                    try {
                        members[0].moveTo(place);
                        members[1].moveTo(place);
                        members[2].moveTo(place);
                    } catch (NoPlaceException o) {
                    }
                }

            }
        }
        Scenarios scenarios = new Scenarios(place);
        try{
            Method required = scenarios.getClass().getDeclaredMethod(ScenarioName);

            required.invoke(scenarios);
        } catch(NoSuchMethodException | InvocationTargetException | IllegalAccessException e){System.out.println("Initialisation exception");}
    }
    @Override
    public String toString() {
        String res = "Family of ";
        for (int i=0; i<members.length; i++){
            res += members[i].getName();
            if (i+1 < members.length) res += ", ";
        }
        return res;
    }
}
