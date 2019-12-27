package Mummi.Enums;

public enum Capacity {
    SMALL(50, "Small"), //enough for a small family and a bunch of staff
    STANDARD(100, "Standard"), //sufficient for a familu of four
    LARGE(200, "Large"),; //comfortable for a large family even with a dozen of furniture sets

    private String description;
    private int size;
    Capacity( int size, String s){
        this.size = size;
        description = s;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return description;
    }
}
