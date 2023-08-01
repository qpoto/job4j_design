package ru.job4j.serialization.java;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement
public class Milk {
    @XmlAttribute
    private boolean pasteurized;
    @XmlAttribute
    private int volume;
    @XmlAttribute
    private String name;
    @XmlElement
    private Farm farm;
    @XmlElement
    private int[] pfc = new int[2];

    public Milk() {
    }

    public Milk(boolean pasteurized, int volume, String name, Farm farm, int... pfc) {
        this.pasteurized = pasteurized;
        this.volume = volume;
        this.name = name;
        this.farm = farm;
        this.pfc = pfc;
    }

    @Override
    public String toString() {
        return "Milk{"
                + "pasteurized=" + pasteurized
                + ", volume=" + volume
                + ", name='" + name
                + '\''
                + ", farm=" + farm
                + ", pfc=" + Arrays.toString(pfc)
                + '}';
    }

    public static void main(String[] args) throws JAXBException, IOException {
        Milk milk = new Milk(true, 1, "Milk", new Farm("Kozino", 100), 3, 3, 3);
        JAXBContext context = JAXBContext.newInstance(Milk.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(milk, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Milk result = (Milk) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
        System.out.println("------------------------------");
        Gson gson = new GsonBuilder().create();
        String milkJson = gson.toJson(milk);
        System.out.println(milkJson);
        System.out.println("------------------------------");
        JSONObject jsonMilk = new JSONObject(milkJson);
        System.out.println(jsonMilk);
    }
}
