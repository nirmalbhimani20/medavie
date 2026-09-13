package com.smarthome.adapter;

import com.smarthome.device.AirConditioner;
import com.smarthome.domain.Appliance;
import com.smarthome.domain.ApplianceException;

/**
 * Adapter for Air Conditioner.
 * Converts turnOff() into setting the thermostat mode to OFF.
 */
public class AirConditionerAdapter implements Appliance {

    private final AirConditioner airConditioner;

    public AirConditionerAdapter(AirConditioner airConditioner) {
        this.airConditioner = airConditioner;
    }

    @Override
    public String getName() {
        return "Air Conditioner";
    }

    @Override
    public void turnOn() {
        try {
            if (AirConditioner.MODE_OFF.equals(airConditioner.getMode())) {
                airConditioner.setMode(AirConditioner.MODE_COOL);
            }
        } catch (Exception e) {
            throw new ApplianceException("Failed to turn on Air Conditioner: " + e.getMessage(), e);
        }
    }

    @Override
    public void turnOff() {
        try {
            airConditioner.setMode(AirConditioner.MODE_OFF);
        } catch (Exception e) {
            throw new ApplianceException("Failed to turn off Air Conditioner: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean isOn() {
        return !AirConditioner.MODE_OFF.equals(airConditioner.getMode());
    }
}
