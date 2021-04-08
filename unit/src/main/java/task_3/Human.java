package task_3;

import java.util.ArrayList;
import java.util.List;

public class Human {
    public int countOfHeads;
    public String name;
    public Place location;
    public List<Head> heads = new ArrayList<>();

    public void changeLocation(Place place) {
        if (place.equals(this.location))
            throw new IllegalArgumentException("Human location can't be changed to the same location!");
        place.updateLocationAndMoodForHuman(this);
    }

    Human (int countOfHeads, String name, Place location, List<Head> heads) {
        this.countOfHeads = countOfHeads;
        this.name = name;
        this.heads.addAll(heads);
        location.addHuman(this);
    }
}
