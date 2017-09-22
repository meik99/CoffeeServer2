package com.rynkbit.coffeeserver2;

import com.rynkbit.coffeeserver2.entity.Alarm;
import com.rynkbit.coffeeserver2.facade.AlarmFacade;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 * Created by michael on 20.09.17.
 */
@ManagedBean(name = "addAlarm")
@RequestScoped
public class AddAlarmPage {
    @Inject
    private AlarmFacade alarmFacade;

    private String name;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void save() {
        if(name != null && name.isEmpty() == false){
            if(description == null){
                description = "";
            }

            Alarm alarm = new Alarm();
            alarm.setName(name);
            alarm.setDescription(description);
            alarm.setHour(-1);
            alarm.setMinute(-1);
            alarmFacade.update(alarm);

            FacesContext
                    .getCurrentInstance()
                    .getApplication()
                    .getNavigationHandler()
                    .handleNavigation(
                            FacesContext.getCurrentInstance(),
                            null,
                            "index");

        }
    }

    public String back() {
        name = "";
        description = "";
        return "/index.xhtml";
    }
}
