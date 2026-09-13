package com.smarthome.domain;

/**
 * Common interface for every appliance.
 *
 * This is the Target in the Adapter pattern.
 * The rest of the app only calls turnOn() / turnOff()
 * and never cares how each device actually works.
 */
public interface Appliance {

    String getName();

    ApplianceType getType();

    void turnOn();

    void turnOff();

    PowerState getPowerState();

    default boolean isOn() {
        return getPowerState() == PowerState.ON;
    }
}
