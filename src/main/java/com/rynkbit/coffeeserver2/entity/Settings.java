package com.rynkbit.coffeeserver2.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
public class Settings implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
}
