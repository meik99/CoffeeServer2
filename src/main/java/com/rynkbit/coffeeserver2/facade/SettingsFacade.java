package com.rynkbit.coffeeserver2.facade;

import javax.ejb.Stateless;

@Stateless
public class SettingsFacade {
    public void setBrewingDuration(int duration){
        duration = duration < 0 ? 0 : duration; //sets duration to 0 if it is less than zero


    }
}
