package core;

import interfaces.Command;
import interfaces.Observer;
import interfaces.SmartComponent;
import interfaces.Switchable;
import model.RoomGroup;

import java.util.List;

/**
 * HomeAutomation - zgodny z SRP i DIP
 * Jedyna odpowiedzialność: automatyzacja (reakcja na zdarzenia z czujników)
 * Zależności są wstrzykiwane przez konstruktor (Dependency Injection)
 * Używa CommandFactory zamiast bezpośrednio tworzyć komendy (DIP)
 */
public class HomeAutomation implements Observer {
    private final SmartHomeHub hub;
    private final CommandFactory commandFactory;

    /**
     * Wstrzykiwanie zależności przez konstruktor
     */
    public HomeAutomation(SmartHomeHub hub, CommandFactory commandFactory) {
        this.hub = hub;
        this.commandFactory = commandFactory;
    }

    @Override
    public void update(String sensorName, boolean state) {
        if(state) {
            System.out.println("[AUTOMATION]: Signal received from " + sensorName + ". starting the welcome procedure.");

            // Automatyzacja: włączamy wszystko w salonie
            List<RoomGroup> rooms = hub.getRooms();
            for(RoomGroup room : rooms) {
                if(room.getName().equals("Salon")) {
                    for(SmartComponent component : room.getComponents()) {
                        if(component instanceof Switchable) {
                            // Używamy CommandFactory zamiast bezpośrednio tworzyć komendy
                            Command turnOnCommand = commandFactory.createTurnOnCommand((Switchable) component);
                            hub.executeCommand(turnOnCommand);
                        }
                    }
                }
            }
        }
    }
}
