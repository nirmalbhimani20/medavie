package com.smarthome.device;

/**
 * Light device (Adaptee).
 * It has no turnOn / turnOff. It only has a switch you can toggle.
 */
public class Light {

    private SwitchPosition position = SwitchPosition.OFF;

    public void toggle() {
        position = (position == SwitchPosition.ON) ? SwitchPosition.OFF : SwitchPosition.ON;
        System.out.println("Light switch toggled. Now " + position);
    }

    public SwitchPosition getPosition() {
        return position;
    }
}
