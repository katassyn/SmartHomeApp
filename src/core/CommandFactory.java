package core;

import commands.SetTempCommand;
import commands.TurnOnCommand;
import interfaces.Adjustable;
import interfaces.Command;
import interfaces.Switchable;

/**
 * CommandFactory - zgodny z DIP (Dependency Inversion Principle)
 * Fabryka odpowiedzialna za tworzenie komend
 * Klasy wysokopoziomowe zależą od abstrakcji (Command), nie od konkretnych implementacji
 */
public class CommandFactory {

    /**
     * Tworzy komendę włączenia urządzenia
     */
    public Command createTurnOnCommand(Switchable device) {
        return new TurnOnCommand(device);
    }

    /**
     * Tworzy komendę ustawienia temperatury
     */
    public Command createSetTempCommand(Adjustable device, double temperature) {
        return new SetTempCommand(device, temperature);
    }
}
