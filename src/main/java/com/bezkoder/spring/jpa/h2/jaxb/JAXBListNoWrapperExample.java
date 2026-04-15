package com.bezkoder.spring.jpa.h2.jaxb;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/*
Removed @XmlElementWrapper — now @XmlElement(name = "person") is applied directly to the list.
XML is flatter and cleaner — useful for REST APIs or simpler XML structures.
Still fully supports marshalling and unmarshalling.
 */
// 1. Person class
@XmlRootElement(name = "person")
class PersonOther {
    private String name;
    private int age;

    public PersonOther() {} // Required by JAXB

    public PersonOther(String name, int age) {
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

// 2. Root class for list of persons (no wrapper)
@XmlRootElement(name = "people")
class PeopleOther {
    private List<PersonOther> persons = new ArrayList<>();

    @XmlElement(name = "person") // Directly under <people>
    public List<PersonOther> getPersons() {
        return persons;
    }
    public void setPersons(List<PersonOther> persons) {
        this.persons = persons;
    }
}

public class JAXBListNoWrapperExample {
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
            marshaller.marshal(people, new File("people_no_wrapper.xml"));
            marshaller.marshal(people, System.out); // Print to console

            // Unmarshal (XML → Java)
            Unmarshaller unmarshaller = context.createUnmarshaller();
            PeopleOther loadedPeople = (PeopleOther) unmarshaller.unmarshal(new File("people_no_wrapper.xml"));

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
