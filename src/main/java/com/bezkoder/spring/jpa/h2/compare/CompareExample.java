package com.bezkoder.spring.jpa.h2.compare;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CompareExample {
    public static  void main(String[] args){

        List<Person> personList = new ArrayList<>();
        personList.add(new Person("me", 10));
        personList.add(new Person("you", 9));
        personList.add(new Person("tert", 5));
        personList.add(new Person("qwe", 8));
        personList.add(new Person("asd", 7));
        personList.add(new Person("tert", 7));
        Collections.sort(personList);
        System.out.println(personList);
        Comparator<Person> comparePersonByAge = Comparator.comparingInt(Person::age).reversed();
        Comparator<Person> comparePersonByName = Comparator.comparing(Person::name);
        Comparator<Person> comparePersonByAgeAndName = comparePersonByAge.thenComparing(comparePersonByName);
        List<Person> sortedPersons = personList.stream().sorted(comparePersonByAgeAndName).toList();
        personList.sort(comparePersonByName);
        Collections.sort(personList, comparePersonByName);
        System.out.println(sortedPersons);
    }

}
