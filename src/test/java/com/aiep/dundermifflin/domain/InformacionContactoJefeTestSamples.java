package com.aiep.dundermifflin.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class InformacionContactoJefeTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static InformacionContactoJefe getInformacionContactoJefeSample1() {
        return new InformacionContactoJefe().id(1L).telefono("telefono1").tipoFono("tipoFono1");
    }

    public static InformacionContactoJefe getInformacionContactoJefeSample2() {
        return new InformacionContactoJefe().id(2L).telefono("telefono2").tipoFono("tipoFono2");
    }

    public static InformacionContactoJefe getInformacionContactoJefeRandomSampleGenerator() {
        return new InformacionContactoJefe()
            .id(longCount.incrementAndGet())
            .telefono(UUID.randomUUID().toString())
            .tipoFono(UUID.randomUUID().toString());
    }
}
