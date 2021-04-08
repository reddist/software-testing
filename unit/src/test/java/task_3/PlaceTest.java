package task_3;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class PlaceTest {

    Human human1;
    Human human2;
    Place room;
    Place corridor;
    Place place1;
    Place place2;

    @Before
    public void setUp() {
        room = new Place("room", new ArrayList<>(Arrays.asList(300, 400, 700)));
        corridor = new Place("corridor", new ArrayList<>(Arrays.asList(300, 300, 2700)), new HashSet<>(Arrays.asList(room)));
        place1 = new Place("place1", new ArrayList<>(Arrays.asList(300, 400, 400)), new HashSet<>(Arrays.asList(corridor)));
        place2 = new Place("place2", new ArrayList<>(Arrays.asList(300, 400, 800)), new HashSet<>(Arrays.asList(corridor)));
        human1 = new Human(1, "human1", room, new ArrayList<>(Arrays.asList(new Head(Mood.SHINY))));
        human2 = new Human(1, "human2", corridor, new ArrayList<>(Arrays.asList(new Head(Mood.INFATUATED))));
    }

    @Test
    public void check_linking() {
        assertTrue(corridor.hasLinked(room) && corridor.hasLinked(place1) && corridor.hasLinked(place2));
        assertTrue(room.hasLinked(corridor));
        assertTrue(place1.hasLinked(corridor));
        assertTrue(place2.hasLinked(corridor));
    }

    @Test
    public void test_hasHuman() {
        assertTrue(room.hasHuman(human1));
        assertTrue(corridor.hasHuman(human2));
    }

    @Test
    public void addHuman_changes_human_location() {
        assertTrue(room.hasHuman(human1));
        room.addHuman(human2);
        assertTrue(room.hasHuman(human2));
        assertFalse(corridor.hasHuman(human2));
        assertEquals(2, room.getNumberOfHumans());
        assertEquals(0, corridor.getNumberOfHumans());
    }

    @Test
    public void unlinking_place_unlinkes_both () {
        room.unlinkPlace(corridor);
        assertFalse(room.hasLinked(corridor));
        assertFalse(corridor.hasLinked(room));
    }

    @Test
    public void linking_place_linkes_both () {
        room.linkPlace(place1);
        assertTrue(room.hasLinked(place1));
        assertTrue(place1.hasLinked(room));
    }
}
