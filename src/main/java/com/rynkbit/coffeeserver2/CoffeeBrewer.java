package com.rynkbit.coffeeserver2;

import com.pi4j.io.gpio.*;

import javax.ejb.Singleton;

/**
 * Created by michael on 20.09.17.
 */
@Singleton
public class CoffeeBrewer {
    private static GpioController gpioController = null;
    private static GpioPinDigitalOutput pin17 = null;

    public void brew(){
        if(gpioController == null){
            GpioFactory.setDefaultProvider(new RaspiGpioProvider(RaspiPinNumberingScheme.BROADCOM_PIN_NUMBERING));

            gpioController = GpioFactory.getInstance();
            pin17 = gpioController.provisionDigitalOutputPin(RaspiBcmPin.GPIO_17, "Coffee", PinState.LOW);
        }

        pin17.high();
        try {
            Thread.sleep(36000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pin17.low();
    }
}
