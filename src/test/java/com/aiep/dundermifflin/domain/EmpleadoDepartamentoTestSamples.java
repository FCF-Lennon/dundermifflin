package com.aiep.dundermifflin.domain;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class EmpleadoDepartamentoTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static EmpleadoDepartamento getEmpleadoDepartamentoSample1() {
        return new EmpleadoDepartamento().id(1L);
    }

    public static EmpleadoDepartamento getEmpleadoDepartamentoSample2() {
        return new EmpleadoDepartamento().id(2L);
    }

    public static EmpleadoDepartamento getEmpleadoDepartamentoRandomSampleGenerator() {
        return new EmpleadoDepartamento().id(longCount.incrementAndGet());
    }
}
