package Mummi;

import Mummi.Enums.AModifier;
import Mummi.Enums.Capacity;
import Mummi.Exceptions.LocationOverLoadedException;
import Mummi.Exceptions.NoActionMatchingException;

public class Place implements Artifact_Ownable{

    private Capacity capacity = Capacity.STANDARD;
    private int sizeLimit = capacity.getSize();
    private String name;
    private Place[] subLocations;
    private Place.Reminder reminds;
    private Artifact[] artifacts = new Artifact[100];

    private String[] actionsReference = new String[10];
    private Functional_Action[] actions = new Functional_Action[10];

    private AModifier modifier = null;

    private Place historically = null;

    class Reminder{//с разными модификаторами объект напоминает разные вещи
        private Artifact[] remindsOvertime;
        private AModifier[] modsOvertime;
        public Reminder(AModifier[] mods, Artifact[] forms){
                this.remindsOvertime = forms;
                this.modsOvertime = mods;
        }
        public Reminder(String forms){
            this.remindsOvertime = new Artifact[]{new Artifact(forms)};
            this.modsOvertime = new AModifier[]{AModifier.DISTANCE_1};
        }

        public Artifact getReminds(){
            for (int i = 0; i < remindsOvertime.length; i++)
                if (modsOvertime[i].equals(modifier)||modsOvertime.length==1) return remindsOvertime[i];
            return null;
        }
        @Override
        public String toString() {
            return getReminds().toString();
        }
    }


    public Place(String name, Place[] subs, String reminds){
        this.name = name;
        subLocations = subs;
        if (reminds!=null)this.reminds = new Reminder(reminds);
        else this.reminds = null;
        actionInit();
    }

    public Place(String name, Place[] subs, AModifier[] mods, Artifact[] reminds){
        this.name = name;
        subLocations = subs;
        this.reminds = new Reminder(mods, reminds);
        actionInit();
    }

    public String getName() {
        return name;
    }
    public Artifact getReminds() {
        return reminds.getReminds();
    }
    public Place[] getSubLocations() {return subLocations;}
    public AModifier getModifier() {
        return modifier;
    }
    public Capacity getCapacity() {
        return capacity;
    }

    public void setModifier(AModifier modifier) {
        this.modifier = modifier;
    }
    public void setCapacity(Capacity capacity) {
        this.capacity = capacity;
        sizeLimit = capacity.getSize();
    }

    @Override
    public Artifact[] getArtifacts() {return artifacts;}
    @Override
    public void addArtifact(Artifact a) {
        for (int i = 0; i < artifacts.length; i++)if(artifacts[i]==null) {
            sizeChange(-a.getSize());
            artifacts[i] = a;
            break;
        }
    }
    @Override
    public void removeArtifact(Artifact a){
        for (int i = 0; i < artifacts.length; i++)if(artifacts[i].equals(a)){
            artifacts[i] = null;
            sizeChange(a.getSize());
            if (sizeLimit > capacity.getSize()) sizeLimit = capacity.getSize();
            break;
        }
    }

    @Override
    public boolean equals(Object obj) {
        Place a = (Place) obj;
        return (this.name==a.name&&this.reminds==a.reminds);
    }
    @Override
    public String toString() {
        String artD = "";
        for (int i = 0; i < artifacts.length; i++){
            if (artifacts[i]==null) continue;
            if(artD!="") artD += ", ";
            artD += artifacts[i];
        }

        String subD = "";
        if (subLocations!=null){
            for (int i=0; i < subLocations.length; i++){
                subD += subLocations[i];
                if (i!=subLocations.length-1) subD+="; ";
            }
        }
        return this.name + ((reminds==null)?"":(" in a form of a " + this.reminds.toString())) +
                ((artD!="")?(" with "  + artD):"")+"."+((subD!="")?(" Consists of "+subD):"");
    }
    @Override
    public int hashCode() {
        int res = 17;
        int s = 37;
        res = s*res + name.hashCode();
        for (Place i: subLocations) res = s*res + i.hashCode();
        res = s*res + reminds.hashCode();
        return res;
    }


    public Functional_Action getAction(String name) throws NoActionMatchingException{
        for (int i = 0; i < actions.length; i++)
            if (name.equals(actionsReference[i])){
                return actions[i];
            }
        throw new NoActionMatchingException("Action not found in Place.getAction, action name:" + name );
    }
    private void actionInit(){
        int n = 0;
        if (name=="Strange object"){
            actions[n] = args -> {
                System.out.println(this.getName() + " floats from the " + args[0]);
                char[] distName = modifier.toString().toCharArray();distName[distName.length-1] = (char)(distName[distName.length-1]-1);modifier = AModifier.valueOf(new String(distName));
            };
            actionsReference[n++] = "float";
        }

        actions[n] = args -> System.out.println(this.getName()+" once was "+ this.getHistorically());
        actionsReference[n++] = "previously";

    }
    public void execute(String s, Object... args){
        getAction(s).performAction(args);
    }

    public void reinstate(String name, Place[] subs, String reminds){
        historically = new Place(this.name, this.subLocations, ((this.reminds!=null)?this.reminds.toString():null));
        this.name = name; subLocations = subs; this.reminds = new Reminder((reminds==null)?"":(reminds));
    }
    public Place getHistorically(){
        return historically;
    }

    public void sizeChange(int n){
        sizeLimit += n;
        if (sizeLimit<0) {
            System.out.println("reached limit");
            throw new LocationOverLoadedException();
        }
        if (sizeLimit > capacity.getSize()) sizeLimit = capacity.getSize();

    }
}
