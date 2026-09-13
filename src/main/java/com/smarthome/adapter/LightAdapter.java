package com.smarthome.adapter;

import com.smarthome.device.Light;
import com.smarthome.device.SwitchPosition;
import com.smarthome.domain.Appliance;
import com.smarthome.domain.ApplianceException;
import com.smarthome.domain.ApplianceType;
import com.smarthome.domain.PowerState;

/**
 * Adapter for Light.
 * Converts turnOff() into toggling the switch to the OFF position.
 */
public class LightAdapter implements Appliance {

    private final Light light;

    public LightAdapter(Light light) {
        this.light = light;
    }

    @Override
    public String getName() {
        return "Light";
    }

    @Override
    public ApplianceType getType() {
        return ApplianceType.LIGHT;
    }

    @Override
    public void turnOn() {
        try {
            if (light.getPosition() == SwitchPosition.OFF) {
                light.toggle();
            }
        } catch (Exception e) {
            throw new ApplianceException("Failed to turn on Light: " + e.getMessage(), e);
        }
    }

    @Override
    public void turnOff() {
        try {
            if (light.getPosition() == SwitchPosition.ON) {
                light.toggle();
            }
        } catch (Exception e) {
            throw new ApplianceException("Failed to turn off Light: " + e.getMessage(), e);
        }
    }

    @Override
    public PowerState getPowerState() {
        return light.getPosition() == SwitchPosition.ON ? PowerState.ON : PowerState.OFF;
    }
}
