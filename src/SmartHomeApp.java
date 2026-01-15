import core.CommandFactory;
import core.DeviceFactory;
import core.HomeAutomation;
import core.SmartHomeHub;
import interfaces.Adjustable;
import interfaces.Command;
import interfaces.SmartComponent;
import interfaces.Switchable;
import model.RoomGroup;
import sensors.MotionSensor;

public class SmartHomeApp {
    public static void main(String[] args) {
        System.out.println("SmartHomeApp initializing...");

        //1. Tworzymy fabryki i kontroler (Dependency Injection)
        DeviceFactory deviceFactory = new DeviceFactory();
        CommandFactory commandFactory = new CommandFactory();
        SmartHomeHub hub = new SmartHomeHub();

        //2. Tworzenie struktury - używamy instancji DeviceFactory
        RoomGroup salon = new RoomGroup("Salon");
        SmartComponent light1 = deviceFactory.createDevice("light", "Lampa Sufitowa");
        SmartComponent light2 = deviceFactory.createDevice("light", "Lampa Stojąca");
        SmartComponent thermo = deviceFactory.createDevice("thermostat", "Ogrzewanie Podłogowe");

        salon.addComponent(light1);
        salon.addComponent(light2);
        salon.addComponent(thermo);

        RoomGroup sypialnia = new RoomGroup("Sypialnia");
        sypialnia.addComponent(deviceFactory.createDevice("light", "Lampka Nocna"));

        hub.addRoom(salon);
        hub.addRoom(sypialnia);

        //3. Tworzenie systemu automatyzacji z wstrzykiwaniem zależności
        HomeAutomation automation = new HomeAutomation(hub, commandFactory);

        //4. Wyświetlenie stanu
        hub.showDashboard();

        //5. Ręczne sterowanie - używamy CommandFactory
        System.out.println("Użytkownik ręcznie włącza lampę sufitową:\n");
        if(light1 instanceof Switchable) {
            Command turnOnLight = commandFactory.createTurnOnCommand((Switchable) light1);
            hub.executeCommand(turnOnLight);
        }
        System.out.println("Użytkownik ustawia temperaturę:\n");
        if (thermo instanceof Adjustable) {
            Command setTemp = commandFactory.createSetTempCommand((Adjustable) thermo, 24);
            hub.executeCommand(setTemp);
        }

        hub.showDashboard();

        //6. Symulacja czujnika - HomeAutomation jako Observer
        System.out.println("Symulacja aktywacji czujnika:");
        MotionSensor motionSensor = new MotionSensor("Czujnik Ruchu Salon");
        motionSensor.addObserver(automation);  // automation

        //wywolanie zdarzenia
        motionSensor.detectMotion();

        //wyniki koncowe
        hub.showDashboard();
    }
}