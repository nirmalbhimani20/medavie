package com.smarthome.device;

import com.smarthome.domain.ApplianceException;

/**
 * Fan device (Adaptee).
 * It has no power button. Speed 0 = off, 1 and 2 = on.
 */
public class Fan {

    private int speed;

    public void setSpeed(int speed) {
        if (speed < 0 || speed > 2) {
            throw new ApplianceException("Fan speed must be 0, 1, or 2, got: " + speed);
        }
        this.speed = speed;
        System.out.println("Fan speed set to " + speed);
    }

    public int getSpeed() {
        return speed;
    }
}
