package Mummi;

import Mummi.Enums.AModifier;
import Mummi.Enums.Mood;
import Mummi.Enums.Race;
import Mummi.Exceptions.NoActionMatchingException;
import Mummi.Exceptions.NoPlaceException;

public abstract class Human_being implements Sayable, Artifact_Ownable{

    private final String name;
    private final Race race;
    private final char sex;

    private Place location = null;
    private Mood mood = null;
    private Artifact[] artifact = new Artifact[new Parameters().CAPACITY];
    private String[] actionsReference = new String[10];
    private Functional_Action[] actions = new Functional_Action[10];
    private int size = 10;

    private AModifier modifier;

    static class Parameters{
        public final int MAX_AGE = 100;
        public final int MAX_HEIGHT = 230;
        public final int CAPACITY = 3;
    }

    public Human_being(String name, Race race, char sex){
        this.name = name;
        this.race = race;
        this.sex = sex;
        actionInit();

    }

    public abstract String describe();

    String getName(){return this.name;}
    Race getRace(){return this.race;}
    char getSex(){return this.sex;}

    public void moveTo(Place p) throws NoPlaceException {
        if (p==null) throw new NoPlaceException();
        if (this.location != null) location.sizeChange(+size);
        p.sizeChange(-size);
        this.location = p;
    }
    public Place currentLocation() { return this.location; }

    public void setEmotion(Mood m) {
        this.mood = m;
    }
    public Mood getEmotion() {
        return this.mood;
    }

    @Override
    public Artifact[] getArtifacts() {return this.artifact;}
    @Override
    public void addArtifact(Artifact a) {this.artifact[0] = a;}
    @Override
    public void removeArtifact(Artifact a) {if (this.artifact[0].equals(a)) this.artifact = null;}

    @Override
    public void say(String s) {
        System.out.println(this.name+" says: " + s);
    }

    @Override
    public String toString() {
        return race+" "+name+((sex=='M')?"male":"female");
    }
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Human_being)) return false;
        Human_being a = (Human_being) obj;
        return this.name.equals(a.name)&&this.sex==a.sex&&this.race==this.race;
    }
    @Override
    public int hashCode() {
        int res = 17;
        int s = 37;
        res = s*res + name.hashCode();
        res = s*res + race.hashCode();
        res = s*res + (int)sex;
        return res;
    }


    public Functional_Action getAction(String name) throws NoActionMatchingException{
        for (int i = 0; i < actions.length; i++)
            if (actionsReference[i].equals(name)){
                return actions[i];
            }
        throw new NoActionMatchingException("Action not found in Human_being.getAction, action name:" + name );
    }
    private void actionInit(){
        int n = 0;

        actions[n] = args -> System.out.println(this.getName() + ": turns to "+ args[0]);
        actionsReference[n++] = "turn";

        actions[n] = args -> System.out.println(this.getName() + " moves " + args[0] + " to " + args[1]);
        actionsReference[n++] = "move";

        actions[n] = args -> {
            checkModifiers();
            if (modifier.equals(AModifier.BLINDED))
                System.out.println(this.getName() + " tries to observe the " + " of " +
                        ((Place)args[0]).getName() + ", but he has the modifier " + modifier);
            else
                System.out.println(this.getName() + " observes " + ((Place)args[0]).getName() + ". Is's " +
                        ((((Place)args[0]).getModifier()==AModifier.DANGEROUS)?"dangerous":"safe"));
        };
        actionsReference[n++] = "observe";

        actions[n] = args -> System.out.println(this.getName() + ": looks at " + ((Place)args[0]).getName() + ": " +
                ((((Place)args[0]).getModifier()!=AModifier.DARK)?args[0]:"but he cant because " +
                ((Place)args[0]).getName() + " has modifier " + ((Place)args[0]).getModifier()) );
        actionsReference[n++] = "see";

        actions[n] = args -> {
            String message = this.getName() + ": listens to: ";
            for (Object i : args) message += i + "; ";
            System.out.println(message);

        };
        actionsReference[n++] = "listen";

    }
    public void execute(String s, Object... args){
        getAction(s).performAction(args);
    }

    private void checkModifiers(){

        Artifact[] tempoArt = location.getArtifacts();
        for (Artifact i : tempoArt) if ((i!=null) && (i.getName().equals("Sun"))) modifier = AModifier.BLINDED;
    }

}
