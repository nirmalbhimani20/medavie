package com.smarthome.adapter;

import com.smarthome.device.AirConditioner;
import com.smarthome.device.ThermostatMode;
import com.smarthome.domain.Appliance;
import com.smarthome.domain.ApplianceException;
import com.smarthome.domain.ApplianceType;
import com.smarthome.domain.PowerState;

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
    public ApplianceType getType() {
        return ApplianceType.AIR_CONDITIONER;
    }

    @Override
    public void turnOn() {
        try {
            if (airConditioner.getMode() == ThermostatMode.OFF) {
                airConditioner.setMode(ThermostatMode.COOL);
            }
        } catch (Exception e) {
            throw new ApplianceException("Failed to turn on Air Conditioner: " + e.getMessage(), e);
        }
    }

    @Override
    public void turnOff() {
        try {
            airConditioner.setMode(ThermostatMode.OFF);
        } catch (Exception e) {
            throw new ApplianceException("Failed to turn off Air Conditioner: " + e.getMessage(), e);
        }
    }

    @Override
    public PowerState getPowerState() {
        return airConditioner.getMode() == ThermostatMode.OFF ? PowerState.OFF : PowerState.ON;
    }
}
