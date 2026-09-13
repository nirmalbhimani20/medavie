package com.smarthome.device;

import com.smarthome.domain.ApplianceException;

/**
 * Fan device (Adaptee).
 * It has no power button. Speed OFF means the fan is stopped.
 */
public class Fan {

    private FanSpeed speed = FanSpeed.OFF;

    public void setSpeed(FanSpeed speed) {
        if (speed == null) {
            throw new ApplianceException("Fan speed must not be null");
        }
        this.speed = speed;
        System.out.println("Fan speed set to " + speed + " (" + speed.getValue() + ")");
    }

    public void setSpeed(int value) {
        setSpeed(FanSpeed.fromValue(value));
    }

    public FanSpeed getSpeed() {
        return speed;
    }
}
