package Mummi;

import Mummi.Enums.AModifier;
import Mummi.Enums.Capacity;
import Mummi.Enums.Color;
import Mummi.Enums.Mood;

public class The_World {
    private Place[] places = new Place[10];

    public The_World(){
        Init();
    }

    public Place[] getPlaces() {
        return places;
    }

    public void Init(){
        Place[] subs = new Place[]{
                new Place("Roof", null, "Shell"),
                new Place("Room", null, "Half-circle")
        };
        subs[1].setModifier(AModifier.DARK);
        subs[0].addArtifact(new Artifact("Mask", Color.GOLDEN, Mood.MERRY));
        subs[0].addArtifact(new Artifact("Mask", Color.GOLDEN, Mood.SAD));
        subs[1].addArtifact(new Artifact("Curtain", Color.RED, Mood.SORROWFUL));
        subs[1].addArtifact(new Artifact("Absent wall"));
        subs[1].addArtifact(new Artifact("Web", Color.WHITE, Mood.NONE));


        AModifier[] mods  = new AModifier[]{AModifier.DISTANCE_1, AModifier.DISTANCE_2, AModifier.DISTANCE_3};
        Artifact[] rems = new Artifact[]{
                new Artifact("House"), new Artifact("Big Shell"), new Artifact("Cane")
        };
        subs = new Place[]{new Place("Strange object", subs, mods, rems)};
        subs[0].setModifier(AModifier.DISTANCE_4);
        subs[0].setCapacity(Capacity.LARGE);
        places[0] = new Place("Mummi-dell", null, null);
        places[0].reinstate("Gulf", subs, "");
        places[0].addArtifact(new Artifact("Sun"));
    }
}
