package sn.ept.git.seminaire.cicd.services;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sn.ept.git.seminaire.cicd.TrackingApplication;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {TrackingApplication.class})
@Transactional(propagation = Propagation.REQUIRED)
class ServiceBaseTest {


    public static final PageRequest PAGE = PageRequest.of(0, 10);

    @Test
    void toEntity() {
        assertThat(1L).isPositive();
    }

}