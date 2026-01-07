package commands;

import interfaces.Adjustable;
import interfaces.Command;

public class SetTempCommand implements Command {
    private Adjustable device;
    private double temp;
    public SetTempCommand(Adjustable device, double temp) {
        this.device = device;
        this.temp = temp;
    }


    @Override
    public void execute() {
        device.setValue(temp);
    }

}
