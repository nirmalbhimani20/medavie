package com.smarthome.scheduler;

import com.smarthome.domain.Appliance;
import com.smarthome.domain.ApplianceException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Once a year, on January 1st at 1:00 AM local time,
 * turn off every appliance. No other actions.
 *
 * Each device is turned off in its own try/catch so one failure
 * does not stop the rest of the house from shutting down.
 */
@Component
public class AnnualUpdateScheduler {

    private final List<Appliance> appliances;

    public AnnualUpdateScheduler(List<Appliance> appliances) {
        this.appliances = appliances;
    }

    // second minute hour day-of-month month day-of-week
    // 0      0      1    1            1     *
    @Scheduled(cron = "0 0 1 1 1 *")
    public void turnOffAllDevices() {
        System.out.println("Annual system update: turning off all devices");
        for (Appliance appliance : appliances) {
            try {
                appliance.turnOff();
                System.out.println(appliance.getType() + " " + appliance.getName()
                        + " is now " + appliance.getPowerState());
            } catch (ApplianceException e) {
                System.out.println("Could not turn off " + appliance.getName() + ": " + e.getMessage());
            }
        }
    }
}
