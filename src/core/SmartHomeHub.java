package core;

import interfaces.Command;
import model.RoomGroup;

import java.util.ArrayList;
import java.util.List;

public class SmartHomeHub {
    private List<RoomGroup> rooms = new ArrayList<>();

    public void addRoom(RoomGroup room) {
        rooms.add(room);
    }

    public void executeCommand(Command command) {
        command.execute();
    }

     // Dostęp do listy pokoi dla innych komponentów (np. HomeAutomation)

    public List<RoomGroup> getRooms() {
        return rooms;
    }

    public void showDashboard(){
        System.out.println("\n--- HOME SYSTEM STATUS ---");
            for (RoomGroup room : rooms) {
                room.displayStatus();
            }
        System.out.println("--------------------------\n");
    }

}
