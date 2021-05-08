package task_3;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.*;

public class HumanTest {

    Human artur;
    Human ford;
    Human zafol;
    Place room;
    Place corridor;
    Place place1;

    @Before
    public void setUp() {
        room = new Place("room", new ArrayList<>(Arrays.asList(300, 400, 700)));
        corridor = new Place("corridor", new ArrayList<>(Arrays.asList(300, 300, 2700)), new HashSet<>(Arrays.asList(room)));
        place1 = new Place("place1", new ArrayList<>(Arrays.asList(300, 400, 400)), new HashSet<>(Arrays.asList(corridor)));
        artur = new Human(1, "Artur", corridor, new ArrayList<>(Arrays.asList(new Head(Mood.IRRITATED))));
        ford = new Human(1, "Ford", room, new ArrayList<>(Arrays.asList(new Head(Mood.CALM))));
        zafol = new Human(2, "Ford", room, new ArrayList<>(Arrays.asList(
            new Head(Mood.SHINY), new Head(Mood.INFATUATED)
        )));
    }

    @Test
    public void seeing_unusual_human_change_mood_to_stunned () {
        artur.changeLocation(room);
        assertSame(Mood.STUNNED, artur.getMood());
    }

    @Test
    public void watching_not_many_humans_makes_first_head_calm() {
        zafol.changeLocation(corridor);
        assertSame(zafol.getMood(), Mood.CALM);
        assertSame(zafol.heads.get(1).mood, Mood.INFATUATED);
    }

    @Test
    public void seeing_more_than_5_people_make_human_irritated () {
        Human human1 = new Human(1, "human1", place1, new ArrayList<>(Arrays.asList(new Head(Mood.CALM))));
        Human human2 = new Human(1, "human2", place1, new ArrayList<>(Arrays.asList(new Head(Mood.CALM))));
        Human human3 = new Human(1, "human3", place1, new ArrayList<>(Arrays.asList(new Head(Mood.CALM))));
        Human human4 = new Human(1, "human4", place1, new ArrayList<>(Arrays.asList(new Head(Mood.CALM))));
        Human human5 = new Human(1, "human5", place1, new ArrayList<>(Arrays.asList(new Head(Mood.CALM))));
        Human human6 = new Human(1, "human6", corridor, new ArrayList<>(Arrays.asList(new Head(Mood.CALM))));
        artur.changeLocation(place1);
        assertSame(Mood.CALM, artur.getMood());
        artur.changeLocation(corridor);
        human6.changeLocation(place1);
        artur.changeLocation(place1);
        assertSame(Mood.IRRITATED, artur.getMood());
    }
}
