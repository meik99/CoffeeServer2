package com.rynkbit.coffeeserver2.entity;

import com.rynkbit.coffeeserver2.entity.converter.BooleanArrayConverter;

import javax.persistence.*;
import java.io.Serializable;
import java.text.DateFormatSymbols;
import java.util.*;

/**
 * Created by michael on 16.09.17.
 */
@Entity(name="ALARM")
@Table
@NamedQueries({
        @NamedQuery(
                name="Alarm.findAll",
                query = "select a from ALARM a"
        ),
        @NamedQuery(
                name = "Alarm.findByTime",
                query = "select a from ALARM a where a.hour = :hour and a.minute = :minute"
        )
})
public class Alarm implements Serializable{
    private static final DateFormatSymbols symbols = new DateFormatSymbols(Locale.getDefault());
    public static final String[] dayNames = symbols.getShortWeekdays();

    private long id;
    private int hour;
    private int minute;
    private String name;
    private String description;
    private Boolean[] days;

    public Alarm() {
        days = new Boolean[]{
            false, false, false, false, false, false, false
        };
    }

    public Alarm(long id, byte hour, byte minute, String name, String description, Boolean[] days) {
        this.id = id;
        this.hour = hour;
        this.minute = minute;
        this.name = name;
        this.description = description;
        this.days = days;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

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

    @Convert(converter = BooleanArrayConverter.class)
    public Boolean[] getDays() {
        return days;
    }

    public void setDays(Boolean[] days) {
        this.days = days;
    }
}
