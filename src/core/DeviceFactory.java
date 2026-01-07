package core;

import interfaces.SmartComponent;
import model.SmartLight;
import model.Thermostat;

public class DeviceFactory {
    public static SmartComponent createDevice(String type, String name) {
        switch (type.toLowerCase()) {
            case "light": return new SmartLight(name);
            case "thermostat": return new Thermostat(name);
            default: throw new IllegalArgumentException("Unknown device type: " + type);
        }
    }
}
