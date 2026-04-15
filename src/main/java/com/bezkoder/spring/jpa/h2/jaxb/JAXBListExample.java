package com.bezkoder.spring.jpa.h2.jaxb;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/*
@XmlElementWrapper → Wraps the list inside a container element (<personList>).
@XmlElement → Defines the XML element name for each list item.
Wrapper Class (People) → JAXB needs a root element for lists.
Works with Jakarta JAXB (jakarta.xml.bind) for Java 11+;
for Java 8, replace imports with javax.xml.bind.
 */
// 1. Person class
@XmlRootElement(name = "person")
class Person {
    private String name;
    private int age;

    public Person() {} // Required by JAXB

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @XmlElement
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @XmlElement
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
}

// 2. Wrapper class for a list of Person objects
@XmlRootElement(name = "people")
class People {
    private List<PersonOther> persons = new ArrayList<>();

    @XmlElementWrapper(name = "personList") // Optional wrapper element
    @XmlElement(name = "person")
    public List<PersonOther> getPersons() {
        return persons;
    }
    public void setPersons(List<PersonOther> persons) {
        this.persons = persons;
    }
}

public class JAXBListExample {
    public static void main(String[] args) {
        try {
            // Create sample data
            PeopleOther people = new PeopleOther();
            people.getPersons().add(new PersonOther("Alice", 30));
            people.getPersons().add(new PersonOther("Bob", 25));
            people.getPersons().add(new PersonOther("Charlie", 35));

            // Create JAXB context
            JAXBContext context = JAXBContext.newInstance(PeopleOther.class);

            // Marshal (Java → XML)
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(people, new File("people.xml"));
            marshaller.marshal(people, System.out); // Print to console

            // Unmarshal (XML → Java)
            Unmarshaller unmarshaller = context.createUnmarshaller();
            PeopleOther loadedPeople = (PeopleOther) unmarshaller.unmarshal(new File("people.xml"));

            System.out.println("\nLoaded from XML:");
            for (PersonOther p : loadedPeople.getPersons()) {
                System.out.println(p.getName() + " - " + p.getAge());
            }

        } catch (JAXBException e) {
            System.err.println("Error processing XML: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

