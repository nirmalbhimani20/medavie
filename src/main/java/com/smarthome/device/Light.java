package com.smarthome.device;

/**
 * Light device (Adaptee).
 * It has no turnOn / turnOff. It only has a switch you can toggle.
 */
public class Light {

    private boolean on;

    public void toggle() {
        on = !on;
        System.out.println("Light switch toggled. Now " + (on ? "ON" : "OFF"));
    }

    public boolean isOn() {
        return on;
    }
}
