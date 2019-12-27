package Mummi;

import Mummi.Exceptions.LocationOverLoadedException;
import Mummi.Exceptions.NoActionMatchingException;
import Mummi.Exceptions.NoPlaceException;

import java.util.Locale;

public class Main {

    public static Place place = (new The_World()).getPlaces()[0];
    public static Family family = new Family("zero", place);

    public static void main(String[] args) {

        family.getMembers()[0].execute("move",
                family.getMembers()[0].getArtifacts()[0],
                new Place("the back of the head", null, null)); // поправил шляпу
        place.execute("previously"); // раньше залив был мумми-доллом
        place.getSubLocations()[0].execute("float", "sea"); // объект плывет
        family.getMembers()[0].execute("observe", place.getSubLocations()[0]); // таит ли опасность?
        testCapability((new The_World()).getPlaces()[0], 10, 5); //вместимостью в ...
        System.out.println(place.getSubLocations()[0].getName() + " in a form of " + place.getSubLocations()[0].getReminds());//в форме

        place.getSubLocations()[0].execute("float", "faraway"); // объект плывет
        System.out.println(place.getSubLocations()[0].getName() + " in a form of " + place.getSubLocations()[0].getReminds());//в форме

        family.getMembers()[0].execute("turn", family); // оборачивается к семье
        family.getMembers()[0].execute("see", place.getSubLocations()[0]);
        family.getMembers()[0].execute("see", place.getSubLocations()[0].getSubLocations()[1]);

        family.getMembers()[0].execute("listen", new Artifact("Dust"), new Artifact("Doors"));
    }

    public static void testCapability(Place placeToTest, int FurnitureSets, int Persons){
        System.out.println("Testing the capablity of " + placeToTest.getName() + " with " + FurnitureSets +
                " sets of furniture and " + Persons + " persons");

        try{
            Artifact furniture = new Artifact("Furniture");
            furniture.setSize(15);

            for (int i = 0; i < FurnitureSets   ; i++) placeToTest.addArtifact(furniture);
            for (int i = 0; i < Persons; i++)  {
                Mummi mummi = new Mummi("Tester guy", 'm');
                mummi.moveTo(placeToTest);
            }

            System.out.println("test passed Successfully");

        } catch(NoPlaceException | LocationOverLoadedException e){
            System.out.println("test failed");

        }
        placeToTest = (new The_World()).getPlaces()[0];
    }

}