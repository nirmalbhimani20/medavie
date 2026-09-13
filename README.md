# Smart Home Appliance Control

Simple Spring Boot app that controls a Light, Fan, and Air Conditioner using the
**Adapter design pattern**, and turns them all off once a year for a system update.

## How to run

Needs JDK 17+ and Maven.

```bash
mvn spring-boot:run
```

On startup you will see each device turned off in its own way. After that the app
stays running and will do the same job again on **January 1st at 1:00 AM local time**.

## How to explain this in an interview

The problem: each device turns off differently.

| Device          | How it turns off                         |
| --------------- | ---------------------------------------- |
| Light           | Toggle the switch to the off position    |
| Fan             | Set speed to `0` (speed is `0`, `1`, `2`) |
| Air Conditioner | Set thermostat mode to `OFF`             |

If the scheduler talked to each device directly, it would need `if (light) ... else if (fan) ...`.
Every new device would force a change in the scheduler.

**Adapter pattern** solves that.

1. **`domain.Appliance`** — common interface: `turnOn()`, `turnOff()`, `getPowerState()`.
   This is the **Target**.
2. **`device`** — Light, Fan, AirConditioner with their own APIs.
   These are the **Adaptees**.
3. **`adapter`** — one class per device. Each implements `Appliance` and translates
   `turnOff()` into that device's real call. These are the **Adapters**.
4. **`scheduler`** — Spring injects `List<Appliance>` and only calls `turnOff()`.
   It never mentions Light, Fan, or Air Conditioner.

**Enums** keep invalid values out of the code:

| Enum              | Where    | Values                         | Why it exists                                      |
| ----------------- | -------- | ------------------------------ | -------------------------------------------------- |
| `PowerState`      | domain   | `ON`, `OFF`                    | One shared language after the adapter              |
| `ApplianceType`   | domain   | `LIGHT`, `FAN`, ...            | Type-safe name for logs instead of a raw string    |
| `SwitchPosition`  | device   | `ON`, `OFF`                    | Light only has a switch                            |
| `FanSpeed`        | device   | `OFF(0)`, `LOW(1)`, `HIGH(2)`  | Assignment speeds, stored as numbers on the device |
| `ThermostatMode`  | device   | `OFF`, `COOL`, `HEAT`          | AC is off only when mode is `OFF`                  |

The adapter's job is to map the device enum to `PowerState`. Example: `FanSpeed.OFF` becomes `PowerState.OFF`, and `FanSpeed.HIGH` becomes `PowerState.ON`.

`FanSpeed` is the one to talk about in an interview: it is an enum with a field (`0`, `1`, `2`) and a `fromValue()` method that throws `ApplianceException` if someone passes `3`.

**Exception handling** is also kept simple:

- Devices throw `ApplianceException` for bad input (unknown fan speed, null AC mode)
  or when a device is offline.
- Adapters catch device errors and wrap them in `ApplianceException`.
- The scheduler uses try/catch **per appliance**. One offline device is logged,
  and the others still turn off.

```java
for (Appliance appliance : appliances) {
    try {
        appliance.turnOff();
    } catch (ApplianceException e) {
        System.out.println("Could not turn off " + appliance.getName() + ": " + e.getMessage());
    }
}
```

To add a new device later: write a device class + an adapter. Do not change the scheduler.

```
scheduler  -->  Appliance  <--  LightAdapter           -->  Light.toggle() to SwitchPosition.OFF
                           <--  FanAdapter              -->  Fan.setSpeed(FanSpeed.OFF)
                           <--  AirConditionerAdapter   -->  AirConditioner.setMode(ThermostatMode.OFF)
```

The yearly job is one Spring annotation:

```java
@Scheduled(cron = "0 0 1 1 1 *")  // Jan 1, 1:00 AM local time
public void turnOffAllDevices() {
    for (Appliance appliance : appliances) {
        appliance.turnOff();
    }
}
```

Nothing else happens during the update. Devices are not turned back on.

## Project layout

```
domain/      Appliance, ApplianceException, PowerState, ApplianceType
device/      Light, Fan, AirConditioner, UnreachableDevice
             SwitchPosition, FanSpeed, ThermostatMode
adapter/     LightAdapter, FanAdapter, AirConditionerAdapter, UnreachableDeviceAdapter
scheduler/   AnnualUpdateScheduler
```

## Assumptions

- Devices start ON so the update is easy to see when the app starts.
- The startup demo calls the same `turnOffAllDevices()` method. The real yearly run is the `@Scheduled` cron.
- Local time means the computer's time zone.
- Devices are in-memory examples, not real hardware.

## AI-assisted development

Cursor was used to write and simplify this project. I chose the Adapter pattern, the
package layout, and the yearly `@Scheduled` job so I can explain the design myself.
