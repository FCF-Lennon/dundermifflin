package com.aiep.dundermifflin.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class InformacionContactoEmpleadosTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static InformacionContactoEmpleados getInformacionContactoEmpleadosSample1() {
        return new InformacionContactoEmpleados().id(1L).telefono("telefono1").tipoFono("tipoFono1");
    }

    public static InformacionContactoEmpleados getInformacionContactoEmpleadosSample2() {
        return new InformacionContactoEmpleados().id(2L).telefono("telefono2").tipoFono("tipoFono2");
    }

    public static InformacionContactoEmpleados getInformacionContactoEmpleadosRandomSampleGenerator() {
        return new InformacionContactoEmpleados()
            .id(longCount.incrementAndGet())
            .telefono(UUID.randomUUID().toString())
            .tipoFono(UUID.randomUUID().toString());
    }
}
