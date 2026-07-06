package core;

import interfaces.Command;
import interfaces.Observer;
import interfaces.SmartComponent;
import interfaces.Switchable;
import model.RoomGroup;

import java.util.List;

public class HomeAutomation implements Observer {
    private final SmartHomeHub hub;
    private final CommandFactory commandFactory;

    // Wstrzykiwanie zależności przez konstruktor

    public HomeAutomation(SmartHomeHub hub, CommandFactory commandFactory) {
        this.hub = hub;
        this.commandFactory = commandFactory;
    }

    @Override
    public void update(String sensorName, boolean state) {
        if(state) {
            System.out.println("[AUTOMATION]: Sygnał z " + sensorName + ".");

            // Prosta logika mapowania: Jeśli czujnik ma w nazwie "Sypialnia", celujemy w pokój "Sypialnia"
            String targetRoomName = "";
            if (sensorName.contains("Salon")) {
                targetRoomName = "Salon";
            } else if (sensorName.contains("Sypialnia")) {
                targetRoomName = "Sypialnia";
            }

            // Iterujemy po pokojach i włączamy światła w tym właściwym
            List<RoomGroup> rooms = hub.getRooms();
            for(RoomGroup room : rooms) {
                // Porównujemy nazwę pokoju z naszym celem
                if(room.getName().equals(targetRoomName)) {
                    System.out.println(" -> Uruchamiam scenariusz dla pokoju: " + targetRoomName);
                    for(SmartComponent component : room.getComponents()) {
                        if(component instanceof Switchable) {
                            Command turnOn = commandFactory.createTurnOnCommand((Switchable) component);
                            hub.executeCommand(turnOn);
                        }
                    }
                }
            }
        }
    }
}
