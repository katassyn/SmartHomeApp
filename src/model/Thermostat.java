package model;

import interfaces.Adjustable;
import interfaces.Switchable;

public class Thermostat extends SmartDevice implements Switchable, Adjustable {

    private double temperature;

    public Thermostat(String name) {
        super(name);
        this.temperature = 20.0;
    }

    @Override
    public void setValue(double value) {
        this.temperature = value;
        System.out.println("[THERMOSTAT] " + name + " is set to " + temperature);
    }

    @Override
    public void displayStatus() {
        System.out.println(" - Termostat: " + name + " | Stan: " + (isOn ? "ON" : "OFF") +
                " | Temp: " + temperature + "C");
    }

    @Override
    public void turnOn() {
        isOn = true;
        System.out.println("[THERMOSTAT] "+ name + " is on");
    }

    @Override
    public void turnOff() {
        isOn = false;
        System.out.println("[THERMOSTAT] "+ name + " is off");
    }
}
