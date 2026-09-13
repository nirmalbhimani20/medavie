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

    void turnOn();

    void turnOff();

    boolean isOn();
}
