package model;

import interfaces.SmartComponent;

public abstract class SmartDevice implements SmartComponent {
    protected String name;
    protected boolean isOn;

    public SmartDevice(String name) {
        this.name = name;
        this.isOn = false;
    }

    @Override
    public String getName() {
        return name;
    }
}
