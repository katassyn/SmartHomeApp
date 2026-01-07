import commands.SetTempCommand;
import commands.TurnOnCommand;
import core.DeviceFactory;
import core.SmartHomeHub;
import interfaces.Adjustable;
import interfaces.Command;
import interfaces.SmartComponent;
import interfaces.Switchable;
import model.RoomGroup;
import sensors.MotionSensor;

import java.lang.management.MonitorInfo;

public class SmartHomeApp {
    public static void main(String[] args) {
        System.out.println("SmartHomeApp initializing...");

        //1. tworzymy kontroler
        SmartHomeHub hub = new SmartHomeHub();

        //2. Tworzenie struktury
        RoomGroup salon = new RoomGroup("Salon");
        SmartComponent light1 = DeviceFactory.createDevice("light", "Lampa Sufitowa");
        SmartComponent light2 = DeviceFactory.createDevice("light", "Lampa Stojąca");
        SmartComponent thermo = DeviceFactory.createDevice("thermostat", "Ogrzewanie Podłogowe");

        salon.addComponent(light1);
        salon.addComponent(light2);
        salon.addComponent(thermo);

        RoomGroup sypialnia = new RoomGroup("Sypialnia");
        sypialnia.addComponent(DeviceFactory.createDevice("light", "Lampka Nocna"));

        hub.addRoom(salon);
        hub.addRoom(sypialnia);

        //3. Wyswietalnie stanu
        hub.showDashboard();

        //4. Reczne sterowanie
        System.out.println("Użytkownik ręcznie włącza lampę sufitową:\n");
        if(light1 instanceof Switchable) {
            Command turnOnLight = new TurnOnCommand((Switchable) light1);
            hub.executeCommand(turnOnLight);
        }
        System.out.println("Użytkownik ustawia temperaturę:\n");
        if (thermo instanceof Adjustable) {
            Command setTemp = new SetTempCommand((Adjustable) thermo, 24);
            hub.executeCommand(setTemp);
        }

        hub.showDashboard();

        //5. Symulacja czujnika
        System.out.println("Symulacja aktywacji czujnika:");
        MotionSensor motionSensor = new MotionSensor("Czujnik Ruchu Salon");
        motionSensor.addObserver(hub);

        //wywolanie zdarzenia
        motionSensor.detectMotion();

        //wyniki koncowe
        hub.showDashboard();
    }
}