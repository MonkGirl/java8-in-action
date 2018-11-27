package com.monkgirl.java8InAction.chapter8;

import org.junit.Test;
import static org.junit.Assert.*;

public class Tests{

     @Test
     public void testMoveRightBy() {
    	Point p1 = new Point(5, 5);
    	Point p2 = p1.moveRightBy(10);
	System.out.println(p2.getX());
		assertEquals("success",15, p2.getX());
     	assertEquals("fail", 25, p2.getY());
	fail("fail...");
     }
}
