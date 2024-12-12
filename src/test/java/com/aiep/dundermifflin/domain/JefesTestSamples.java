package com.aiep.dundermifflin.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class JefesTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    public static Jefes getJefesSample1() {
        return new Jefes().id(1L).nombreJefe("nombreJefe1");
    }

    public static Jefes getJefesSample2() {
        return new Jefes().id(2L).nombreJefe("nombreJefe2");
    }

    public static Jefes getJefesRandomSampleGenerator() {
        return new Jefes().id(longCount.incrementAndGet()).nombreJefe(UUID.randomUUID().toString());
    }
}
