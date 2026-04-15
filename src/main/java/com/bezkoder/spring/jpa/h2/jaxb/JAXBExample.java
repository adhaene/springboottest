package com.bezkoder.spring.jpa.h2.jaxb;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.File;

/*
Annotations:
@XmlRootElement → Marks the root XML element.
@XmlElement → Marks fields to be included in XML.
JAXBContext → Entry point for JAXB operations.
Marshaller → Converts Java objects to XML.
Unmarshaller → Converts XML back to Java objects.
Default Constructor → Required for unmarshalling.
 */
// 1. Define a Java class with JAXB annotations
@XmlRootElement(name = "person")
class Person1 {
    private String name;
    private int age;

    // Default constructor is required by JAXB
    public Person1() {}

    public Person1(String name, int age) {
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

public class JAXBExample {
    public static void main(String[] args) {
        try {
            // 2. Create a Person object
            PersonOther person = new PersonOther("Alice", 30);

            // 3. Create JAXB context for the Person class
            JAXBContext context = JAXBContext.newInstance(PersonOther.class);

            // 4. Marshal (Java → XML)
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(person, new File("person.xml"));
            marshaller.marshal(person, System.out); // Print to console

            // 5. Unmarshal (XML → Java)
            Unmarshaller unmarshaller = context.createUnmarshaller();
            PersonOther loadedPerson = (PersonOther) unmarshaller.unmarshal(new File("person.xml"));

            System.out.println("\nLoaded from XML: " + loadedPerson.getName() + ", " + loadedPerson.getAge());

        } catch (JAXBException e) {
            System.err.println("Error processing XML: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
