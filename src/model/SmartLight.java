package model;

import interfaces.Switchable;

public class SmartLight extends SmartDevice implements Switchable {

    public SmartLight(String name) {
        super(name);
    }

    @Override
    public void displayStatus() {
        System.out.println(" - Światło: " + name + " | Stan: " + (isOn ? "ON" : "OFF"));
    }

    @Override
    public void turnOn() {
        isOn = true;
        System.out.println("[LIGHT] " + name + " is on");
    }

    @Override
    public void turnOff() {
        isOn = false;
        System.out.println("[LIGHT] " + name + " is off");
    }

}
