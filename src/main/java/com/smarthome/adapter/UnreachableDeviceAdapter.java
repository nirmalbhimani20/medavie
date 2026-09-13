package com.smarthome.adapter;

import com.smarthome.device.UnreachableDevice;
import com.smarthome.domain.Appliance;
import com.smarthome.domain.ApplianceException;
import com.smarthome.domain.ApplianceType;
import com.smarthome.domain.PowerState;

/**
 * Adapter for an offline device.
 * It still implements Appliance, so the scheduler treats it like the others.
 */
public class UnreachableDeviceAdapter implements Appliance {

    private final UnreachableDevice device;

    public UnreachableDeviceAdapter(UnreachableDevice device) {
        this.device = device;
    }

    @Override
    public String getName() {
        return device.getName();
    }

    @Override
    public ApplianceType getType() {
        return ApplianceType.SPACE_HEATER;
    }

    @Override
    public void turnOn() {
        throw new ApplianceException(device.getName() + " is offline and did not respond");
    }

    @Override
    public void turnOff() {
        try {
            device.turnOff();
        } catch (Exception e) {
            throw new ApplianceException("Failed to turn off " + getName() + ": " + e.getMessage(), e);
        }
    }

    @Override
    public PowerState getPowerState() {
        return PowerState.ON;
    }
}
