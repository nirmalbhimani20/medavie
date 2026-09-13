package com.smarthome;

import com.smarthome.adapter.AirConditionerAdapter;
import com.smarthome.adapter.FanAdapter;
import com.smarthome.adapter.LightAdapter;
import com.smarthome.adapter.UnreachableDeviceAdapter;
import com.smarthome.device.AirConditioner;
import com.smarthome.device.Fan;
import com.smarthome.device.FanSpeed;
import com.smarthome.device.Light;
import com.smarthome.device.ThermostatMode;
import com.smarthome.device.UnreachableDevice;
import com.smarthome.domain.Appliance;
import com.smarthome.scheduler.AnnualUpdateScheduler;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.List;

@SpringBootApplication
@EnableScheduling
public class SmartHomeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartHomeApplication.class, args);
    }

    @Bean
    public Appliance light() {
        Light light = new Light();
        light.toggle();
        return new LightAdapter(light);
    }

    @Bean
    public Appliance fan() {
        Fan fan = new Fan();
        fan.setSpeed(FanSpeed.HIGH);
        return new FanAdapter(fan);
    }

    @Bean
    public Appliance airConditioner() {
        AirConditioner airConditioner = new AirConditioner();
        airConditioner.setMode(ThermostatMode.COOL);
        return new AirConditionerAdapter(airConditioner);
    }

    @Bean
    public Appliance garageHeater() {
        return new UnreachableDeviceAdapter(new UnreachableDevice("Garage heater"));
    }

    /**
     * Runs once at startup so you can see the update without waiting until January 1st.
     * The real yearly job is still {@code @Scheduled} in {@link AnnualUpdateScheduler}.
     */
    @Bean
    public CommandLineRunner demo(List<Appliance> appliances, AnnualUpdateScheduler scheduler) {
        return args -> {
            System.out.println("=== Before annual update ===");
            appliances.forEach(appliance ->
                    System.out.println(appliance.getType() + " " + appliance.getName()
                            + " = " + appliance.getPowerState()));

            scheduler.turnOffAllDevices();

            System.out.println("=== After annual update ===");
            appliances.forEach(appliance ->
                    System.out.println(appliance.getType() + " " + appliance.getName()
                            + " = " + appliance.getPowerState()));
        };
    }
}
