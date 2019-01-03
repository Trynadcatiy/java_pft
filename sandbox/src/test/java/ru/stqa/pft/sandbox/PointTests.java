package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

    @Test
    public void testDistanceP1ToP2() {
        Point p1 = new Point(-1.3, 3);
        Point p2 = new Point(1.7, 7);
        Assert.assertEquals(p1.distance(p2), 5.0);
    }

    @Test
    public void testDistanceP1ToP2EqualsP2ToP1() {
        Point p1 = new Point(-1.3, 3);
        Point p2 = new Point(1.7, 7);
        Assert.assertEquals(p1.distance(p2), p2.distance(p1));
    }

    @Test
    public void testDistanceP1ToP1EqualZero() {
        Point p = new Point(-1.3, 3);
        Assert.assertEquals(p.distance(p), 0.0);
    }
}
