package com.smarthome.device;

import com.smarthome.domain.ApplianceException;

/**
 * Air conditioner device (Adaptee).
 * It has no power button. It is off when the thermostat mode is OFF.
 */
public class AirConditioner {

    private ThermostatMode mode = ThermostatMode.OFF;

    public void setMode(ThermostatMode mode) {
        if (mode == null) {
            throw new ApplianceException("Air conditioner mode must not be null");
        }
        this.mode = mode;
        System.out.println("Air conditioner mode set to " + mode);
    }

    public ThermostatMode getMode() {
        return mode;
    }
}
