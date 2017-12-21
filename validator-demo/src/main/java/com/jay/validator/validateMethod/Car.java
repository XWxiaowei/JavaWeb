package com.jay.validator.validateMethod;

import com.jay.validator.model.Passenger;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author xiang.wei
 * @create 2017/11/29 18:55
 */
public class Car {
    public Car(@NotNull String manufacturer) {
        //...
    }

    public Car(String manufacturer, String team) {
        //...
    }

    public void drive(@Max(75) int speedInMph) {
        //...
    }

    @Size(min = 1)
    public List<Passenger> getPassengers() {
        //...
        return null;
    }
}
