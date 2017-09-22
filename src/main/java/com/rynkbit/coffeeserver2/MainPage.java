package com.rynkbit.coffeeserver2;

import com.rynkbit.coffeeserver2.entity.Alarm;
import com.rynkbit.coffeeserver2.facade.AlarmFacade;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by michael on 16.09.17.
 */
@ManagedBean(name="main")
@RequestScoped
public class MainPage {
    @Inject
    AlarmFacade alarmFacade;

    private List<Alarm> alarmCache;

    @PostConstruct
    public void initialize(){
        alarmCache = alarmFacade.getAlarms();
    }

    public List<Alarm> getAlarms(){
        return alarmCache;
    }

    public String getDayName(Integer dayIndex){
        return Alarm.dayNames[dayIndex+1];
    }

    public void toggleDay(Alarm alarm, Integer dayIndex){
        alarm.getDays()[dayIndex] = !alarm.getDays()[dayIndex];

        alarmFacade.update(alarm);
        alarmCache = alarmFacade.getAlarms();
    }

    public void updateAlarm(Alarm alarm){
        alarmFacade.update(alarm);
        alarmCache = alarmFacade.getAlarms();
    }

    public void deleteAlarm(Alarm alarm){
        alarmFacade.remove(alarm);
        alarmCache = alarmFacade.getAlarms();
    }

    public String addAlarm() {
        return "/AddAlarm.xhtml";
    }
}
