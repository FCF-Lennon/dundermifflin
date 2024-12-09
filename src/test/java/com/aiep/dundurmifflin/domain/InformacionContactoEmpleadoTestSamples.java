package com.aiep.dundurmifflin.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class InformacionContactoEmpleadoTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static InformacionContactoEmpleado getInformacionContactoEmpleadoSample1() {
        return new InformacionContactoEmpleado().id(1L).telefono("telefono1");
    }

    public static InformacionContactoEmpleado getInformacionContactoEmpleadoSample2() {
        return new InformacionContactoEmpleado().id(2L).telefono("telefono2");
    }

    public static InformacionContactoEmpleado getInformacionContactoEmpleadoRandomSampleGenerator() {
        return new InformacionContactoEmpleado().id(longCount.incrementAndGet()).telefono(UUID.randomUUID().toString());
    }
}
