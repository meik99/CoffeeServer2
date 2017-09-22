package com.rynkbit.coffeeserver2.facade;

import com.rynkbit.coffeeserver2.entity.Alarm;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by michael on 16.09.17.
 */
@Stateless
public class AlarmFacade {
    @PersistenceContext
    private EntityManager em;

    public List<Alarm> getAlarms(){
        return em.createNamedQuery("Alarm.findAll", Alarm.class).getResultList();
    }

//    @PostConstruct
//    public void insertTestAlarm() {
//        Alarm alarm = new Alarm();
//
//        alarm.setName("Test Alarm");
//        alarm.setDescription("This is my test alarm");
//        alarm.setHour(10);
//        alarm.setMinute(10);
//
//        em.merge(alarm);
//    }

    public void update(Alarm alarm) {
        em.merge(alarm);
    }

    public void remove(Alarm alarm) {
        Alarm toDelete = em.find(Alarm.class, alarm.getId());
        em.remove(toDelete);
    }

    public boolean alarmExists(int currentHour, int currentMinute, int dayOfWeek) {
        List<Alarm> alarms = em.createNamedQuery("Alarm.findByTime", Alarm.class)
                .setParameter("hour", currentHour)
                .setParameter("minute", currentMinute)
                .getResultList();

        if(alarms.size() <= 0){
            return false;
        }

        for(int i = 0; i < alarms.size(); i++){
            if(alarms.get(i).getDays()[dayOfWeek-1] == true){
                return true;
            }
        }

        return false;
    }
}
