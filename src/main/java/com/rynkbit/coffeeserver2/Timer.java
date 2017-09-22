package com.rynkbit.coffeeserver2;

import com.rynkbit.coffeeserver2.facade.AlarmFacade;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by michael on 20.09.17.
 */
@Singleton
@Startup
public class Timer {
    @Inject
    AlarmFacade alarmFacade;
    @Inject
    CoffeeBrewer coffeeBrewer;

    @Schedule(hour = "*", minute = "*", second = "*/30")
    public void time(){
        System.out.println("Timer running");
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
        int currentMinute = calendar.get(Calendar.MINUTE);

        if(alarmFacade.alarmExists(currentHour, currentMinute, dayOfWeek) == true){
            coffeeBrewer.brew();
        }
    }
}
