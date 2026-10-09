package no.uib.inf101.carcassonne.model;

import org.junit.jupiter.api.Test;

import no.uib.inf101.model.tile.terrain.City;
import no.uib.inf101.model.tile.terrain.Monastery;
import no.uib.inf101.model.tile.terrain.EmptyTerrain;
import no.uib.inf101.model.tile.terrain.Field;
import no.uib.inf101.model.tile.terrain.Road;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TerrainEqualityTest {

    @Test 
    void CityEqualsTest() {
        City city1 = new City();
        City city2 = new City();

        Road road1 = new Road();
        Road road2 = new Road();

        Field field1 = new Field();
        Field field2 = new Field();

        EmptyTerrain empty1 = new EmptyTerrain();
        EmptyTerrain empty2 = new EmptyTerrain();

        Monastery cloister1 = new Monastery();
        Monastery cloister2 = new Monastery();

        assertTrue(city1.equals(city2));
        assertTrue(road1.equals(road2));
        assertTrue(field1.equals(field2));
        assertTrue(empty1.equals(empty2));
        assertTrue(cloister1.equals(cloister2));

    }
    
}
