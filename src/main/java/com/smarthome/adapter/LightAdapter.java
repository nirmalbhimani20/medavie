package com.smarthome.adapter;

import com.smarthome.device.Light;
import com.smarthome.domain.Appliance;
import com.smarthome.domain.ApplianceException;

/**
 * Adapter for Light.
 * Converts turnOff() into toggling the switch to the off position.
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
    public void turnOn() {
        try {
            if (!light.isOn()) {
                light.toggle();
            }
        } catch (Exception e) {
            throw new ApplianceException("Failed to turn on Light: " + e.getMessage(), e);
        }
    }

    @Override
    public void turnOff() {
        try {
            if (light.isOn()) {
                light.toggle();
            }
        } catch (Exception e) {
            throw new ApplianceException("Failed to turn off Light: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean isOn() {
        return light.isOn();
    }
}
