package com.company.dataStructure.hashmap;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolutionTest {
    public class MyKey {
        private String name;
        private int id;

        public MyKey(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public int hashCode() {
            System.out.println("Calling hashCode()");
            return id;
        }

        @Override
        public boolean equals(Object o) {
            System.out.println("Calling equals() for key: " + o);
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            MyKey myKey = (MyKey) o;
            return id == myKey.id && Objects.equals(name, myKey.name);
        }
    }

    @Test
    public void whenCallsEqualsOnCollision_thenCorrect() {
        HashMap<MyKey, String> map = new HashMap<>();
        MyKey k1 = new MyKey(1, "firstKey");
        MyKey k2 = new MyKey(2, "secondKey");
        MyKey k3 = new MyKey(2, "thirdKey");

        System.out.println("storing value for k1");
        map.put(k1, "firstValue");
        System.out.println("storing value for k2");
        map.put(k2, "secondValue");
        System.out.println("storing value for k3");
        map.put(k3, "thirdValue");

        System.out.println("retrieving value for k1");
        String v1 = map.get(k1);
        System.out.println("retrieving value for k2");
        String v2 = map.get(k2);
        System.out.println("retrieving value for k3");
        String v3 = map.get(k3);

        assertEquals("firstValue", v1);
        assertEquals("secondValue", v2);
        assertEquals("thirdValue", v3);
    }

    @Test
    public void redBlackTree() {
        HashMap<MyKey, String> map = new HashMap<>();

        for (int i = 0; i < 30; i++) {
            map.put(new MyKey(1, String.valueOf(i)), String.valueOf(i));
        }

        for (int i = 0; i < 30; i++) {
            map.put(new MyKey(2, String.valueOf(i)), String.valueOf(i));
        }

        System.out.println(map.size());
    }
}