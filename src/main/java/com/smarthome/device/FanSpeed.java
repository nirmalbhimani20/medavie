package com.smarthome.device;

import com.smarthome.domain.ApplianceException;

/**
 * Fan speeds from the assignment: 0 = off, 1 and 2 = on.
 * Each enum constant stores the number the device uses.
 */
public enum FanSpeed {
    OFF(0),
    LOW(1),
    HIGH(2);

    private final int value;

    FanSpeed(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static FanSpeed fromValue(int value) {
        for (FanSpeed speed : values()) {
            if (speed.value == value) {
                return speed;
            }
        }
        throw new ApplianceException("Fan speed must be 0, 1, or 2, got: " + value);
    }
}
