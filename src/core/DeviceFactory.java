package core;

import interfaces.SmartComponent;
import model.SmartLight;
import model.Thermostat;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * DeviceFactory - zgodny z OCP (Open/Closed Principle)
 * Otwarty na rozszerzenia (nowe urządzenia), zamknięty na modyfikacje
 */
public class DeviceFactory {
    private final Map<String, Function<String, SmartComponent>> deviceRegistry = new HashMap<>();

    public DeviceFactory() {
        // Rejestracja domyślnych urządzeń przy użyciu method references
        registerDevice("light", SmartLight::new);
        registerDevice("thermostat", Thermostat::new);
    }

    /**
     * Rejestracja nowego typu urządzenia bez modyfikacji kodu Factory
     * @param type typ urządzenia
     * @param constructor konstruktor urządzenia
     */
    public void registerDevice(String type, Function<String, SmartComponent> constructor) {
        deviceRegistry.put(type.toLowerCase(), constructor);
    }

    public SmartComponent createDevice(String type, String name) {
        Function<String, SmartComponent> constructor = deviceRegistry.get(type.toLowerCase());
        if (constructor == null) {
            throw new IllegalArgumentException("Unknown device type: " + type);
        }
        return constructor.apply(name);
    }
}
