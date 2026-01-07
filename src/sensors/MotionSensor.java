package sensors;

import interfaces.Observer;

import java.util.ArrayList;
import java.util.List;

public class MotionSensor {
    private String name;
    private List<Observer> observers = new ArrayList<>();

    public MotionSensor(String name) {
        this.name = name;
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }
    public void detectMotion() {
        System.out.println("[SENSOR] " + name + " detected movement!");
        notifyObservers(true);
    }
    private void notifyObservers(boolean state) {
        for (Observer observer : observers) {
            observer.update(name,state);
        }
    }
}
