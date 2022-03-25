package sn.ept.git.seminaire.cicd.repository;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import sn.ept.git.seminaire.cicd.TrackingApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {TrackingApplication.class})
@Transactional
class RepositoryBaseTest {

    @MockBean
    TestEntityManager tem;

    ApplicationContext context;

    @Test
    void defaultPageShouldBePositive() {
        assertThat(tem)
                .isNotNull();

    }

}