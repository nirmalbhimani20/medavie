package com.smarthome.device;

import com.smarthome.domain.ApplianceException;

/**
 * A device that is offline. Used to show exception handling:
 * one failure should not stop the other appliances from turning off.
 */
public class UnreachableDevice {

    private final String name;

    public UnreachableDevice(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void turnOff() {
        throw new ApplianceException(name + " is offline and did not respond");
    }
}
