package ru.job4j.serialization.java;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Farm {
    @XmlElement
    String name;
    @XmlAttribute
    int cowsQuantity;

    public Farm() {
    }

    public Farm(String name, int cowsQuantity) {
        this.name = name;
        this.cowsQuantity = cowsQuantity;
    }

    @Override
    public String toString() {
        return "Farm{"
                + "name='" + name + '\''
                + ", cowsQuantity=" + cowsQuantity
                + '}';
    }
}
