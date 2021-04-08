package task_3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public class Place {
    public List<Integer> sizes = new ArrayList<>();
    private Set<Place> linkedPlaces = new HashSet<>();
    public String name;
    private Set<Human> humansInside = new HashSet<>();

    Place(String name, ArrayList<Integer> sizes) {
        this.name = name;
        this.sizes.addAll(sizes);
    }

    Place(String name, ArrayList<Integer> sizes, Set<Place> linkedPlaces) {
        this(name, sizes);
        this.linkedPlaces.addAll(linkedPlaces);
        linkedPlaces.forEach(place -> place.linkPlace(this));
    }

    public boolean hasHuman(Human human) {
        return humansInside.contains(human);
    }

    public void addHuman (Human human) {
        human.changeLocation(this);
    }

    private void removeHuman (Human human) {
        this.humansInside.remove(human);
    }

    public int getNumberOfHumans () {
        return humansInside.size();
    }

    public void linkPlace (Place place) {
        linkedPlaces.add(place);
        if (!place.hasLinked(this)) place.linkPlace(this);
    }

    public void unlinkPlace (Place place) {
        linkedPlaces.remove(place);
        if (place.hasLinked(this)) place.unlinkPlace(this);
    }
    public boolean hasLinked (Place place) {
        return linkedPlaces.contains(place);
    }

    public void updateLocationAndMoodForHuman(Human human) {
        if (human.location != null) human.location.removeHuman(human);
        AtomicBoolean hasUnusualHumans = new AtomicBoolean(false);
        this.humansInside.forEach((insideHuman) -> {
            if (insideHuman.countOfHeads > 1 && human.countOfHeads == 1) {
                human.heads.get(0).mood = Mood.STUNNED;
                hasUnusualHumans.set(true);
            }
        });
        if (!hasUnusualHumans.get()) human.heads.get(0).mood = Mood.CALM;
        if (this.humansInside.size() > 5) human.heads.get(0).mood = Mood.IRRITATED;
        this.humansInside.add(human);
        human.location = this;
    }
}
