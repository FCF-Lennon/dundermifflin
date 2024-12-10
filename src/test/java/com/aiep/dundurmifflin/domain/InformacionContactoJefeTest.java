package com.aiep.dundurmifflin.domain;

import static com.aiep.dundurmifflin.domain.InformacionContactoJefeTestSamples.*;
import static com.aiep.dundurmifflin.domain.JefesTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.aiep.dundurmifflin.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class InformacionContactoJefeTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InformacionContactoJefe.class);
        InformacionContactoJefe informacionContactoJefe1 = getInformacionContactoJefeSample1();
        InformacionContactoJefe informacionContactoJefe2 = new InformacionContactoJefe();
        assertThat(informacionContactoJefe1).isNotEqualTo(informacionContactoJefe2);

        informacionContactoJefe2.setId(informacionContactoJefe1.getId());
        assertThat(informacionContactoJefe1).isEqualTo(informacionContactoJefe2);

        informacionContactoJefe2 = getInformacionContactoJefeSample2();
        assertThat(informacionContactoJefe1).isNotEqualTo(informacionContactoJefe2);
    }

    @Test
    void jefeTest() {
        InformacionContactoJefe informacionContactoJefe = getInformacionContactoJefeRandomSampleGenerator();
        Jefes jefesBack = getJefesRandomSampleGenerator();

        informacionContactoJefe.setJefe(jefesBack);
        assertThat(informacionContactoJefe.getJefe()).isEqualTo(jefesBack);

        informacionContactoJefe.jefe(null);
        assertThat(informacionContactoJefe.getJefe()).isNull();
    }
}
