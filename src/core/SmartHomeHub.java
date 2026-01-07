package core;

import commands.TurnOnCommand;
import interfaces.Command;
import interfaces.Observer;
import interfaces.SmartComponent;
import interfaces.Switchable;
import model.RoomGroup;

import java.util.ArrayList;
import java.util.List;

public class SmartHomeHub implements Observer {
    private List<RoomGroup> rooms = new ArrayList<>();

    public void addRoom(RoomGroup room) {
        rooms.add(room);
    }

    public void executeCommand(Command command) {
        command.execute();
    }

    @Override
    public void update(String sensorName, boolean state) {
        if(state) {
            System.out.println("[HUB]: Signal received from " + sensorName + ". starting the welcome procedure.");
            //Automatyzacja: wlaczamy wszystko w salonie
            for(RoomGroup room : rooms) {
                if(room.getName().equals("Salon")) {
                    for(SmartComponent component : room.getComponents()) {
                        if(component instanceof Switchable) {
                            new TurnOnCommand((Switchable) component).execute();
                        }
                    }
                }
            }
        }
    }

    public void showDashboard(){
        System.out.println("\n--- HOME SYSTEM STATUS ---");
            for (RoomGroup room : rooms) {
                room.displayStatus();
            }
        System.out.println("--------------------------\n");
    }

}
