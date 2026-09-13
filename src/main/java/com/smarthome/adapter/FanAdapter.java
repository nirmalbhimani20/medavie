package com.smarthome.adapter;

import com.smarthome.device.Fan;
import com.smarthome.device.FanSpeed;
import com.smarthome.domain.Appliance;
import com.smarthome.domain.ApplianceException;
import com.smarthome.domain.ApplianceType;
import com.smarthome.domain.PowerState;

/**
 * Adapter for Fan.
 * Converts turnOff() into setting the speed to OFF (0).
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
    public ApplianceType getType() {
        return ApplianceType.FAN;
    }

    @Override
    public void turnOn() {
        try {
            if (fan.getSpeed() == FanSpeed.OFF) {
                fan.setSpeed(FanSpeed.LOW);
            }
        } catch (Exception e) {
            throw new ApplianceException("Failed to turn on Fan: " + e.getMessage(), e);
        }
    }

    @Override
    public void turnOff() {
        try {
            fan.setSpeed(FanSpeed.OFF);
        } catch (Exception e) {
            throw new ApplianceException("Failed to turn off Fan: " + e.getMessage(), e);
        }
    }

    @Override
    public PowerState getPowerState() {
        return fan.getSpeed() == FanSpeed.OFF ? PowerState.OFF : PowerState.ON;
    }
}
