package com.smarthome.adapter;

import com.smarthome.device.Fan;
import com.smarthome.domain.Appliance;
import com.smarthome.domain.ApplianceException;

/**
 * Adapter for Fan.
 * Converts turnOff() into setting the speed to 0.
 */
public class FanAdapter implements Appliance {

    private final Fan fan;

    public FanAdapter(Fan fan) {
        this.fan = fan;
    }

    @Override
    public String getName() {
        return "Fan";
    }

    @Override
    public void turnOn() {
        try {
            if (fan.getSpeed() == 0) {
                fan.setSpeed(1);
            }
        } catch (Exception e) {
            throw new ApplianceException("Failed to turn on Fan: " + e.getMessage(), e);
        }
    }

    @Override
    public void turnOff() {
        try {
            fan.setSpeed(0);
        } catch (Exception e) {
            throw new ApplianceException("Failed to turn off Fan: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean isOn() {
        return fan.getSpeed() > 0;
    }
}
