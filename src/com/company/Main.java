package com.company;

public class Main {

    public static void main(String[] args) {

        MySet<String> names = new MyHashSet<>();

        System.out.println(names.isEmpty());
        System.out.println(names.size());
        System.out.println("==================");

        names.add("Marina");
        names.add("Olya");
        names.add("Natasha");
        names.add("Tanya");
        names.add("Rita");
        names.remove("Natasha");

        System.out.println(names.isEmpty());
        System.out.println(names.size());
        System.out.println("==================");
        System.out.println(names);
        Object[] array = names.toArray();
        for (Object o: array) {
            System.out.println(o);
        }
    }

}