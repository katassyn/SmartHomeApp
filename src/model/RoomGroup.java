package model;

import interfaces.SmartComponent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RoomGroup implements SmartComponent {

    private String name;
    private List<SmartComponent> components = new ArrayList<>();


    public RoomGroup(String name) {
        this.name = name;
    }

    public void addComponent(SmartComponent component) {
        components.add(component);
    }

    public List<SmartComponent> getComponents() {
        return components;
    }

    @Override
    public void displayStatus() {
        System.out.println("Room: " + name);
        for (SmartComponent component : components) {
            component.displayStatus();
        }
    }

    @Override
    public String getName() {
        return name;
    }
}
