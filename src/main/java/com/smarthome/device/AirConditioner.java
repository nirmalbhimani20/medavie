package com.smarthome.device;

import com.smarthome.domain.ApplianceException;

/**
 * Air conditioner device (Adaptee).
 * It has no power button. It is off when the thermostat mode is OFF.
 */
public class AirConditioner {

    public static final String MODE_OFF = "OFF";
    public static final String MODE_COOL = "COOL";

    private String mode = MODE_OFF;

    public void setMode(String mode) {
        if (mode == null || (!MODE_OFF.equals(mode) && !MODE_COOL.equals(mode))) {
            throw new ApplianceException("Air conditioner mode must be OFF or COOL, got: " + mode);
        }
        this.mode = mode;
        System.out.println("Air conditioner mode set to " + mode);
    }

    public String getMode() {
        return mode;
    }
}
