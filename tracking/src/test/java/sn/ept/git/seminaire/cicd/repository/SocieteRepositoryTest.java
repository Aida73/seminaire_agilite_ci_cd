package sn.ept.git.seminaire.cicd.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import sn.ept.git.seminaire.cicd.data.SocieteDTOTestData;
import sn.ept.git.seminaire.cicd.data.TestData;
import sn.ept.git.seminaire.cicd.dto.SocieteDTO;
import sn.ept.git.seminaire.cicd.mappers.SocieteMapper;
import sn.ept.git.seminaire.cicd.models.Societe;
import sn.ept.git.seminaire.cicd.repositories.SocieteRepository;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class SocieteRepositoryTest extends RepositoryBaseTest {


    SocieteDTO dto;
    Societe entity;
    Optional<Societe> optionalSociete;
    @Autowired
    private SocieteMapper mapper;
    @Autowired
    private SocieteRepository repository;

    @BeforeEach
    void setUp() {
        dto = SocieteDTOTestData.defaultDTO();
        entity = mapper.asEntity(dto);
        repository.deleteAll();
        entity = repository.saveAndFlush(entity);
    }

    @Test
    void givenRepository_whenFindByName_thenResult() {
        optionalSociete = repository.findByName(entity.getName());
        assertThat(optionalSociete)
                .isNotNull()
                .isPresent()
                .map(item -> item.getName()).hasValue(TestData.Default.name);
    }

    @Test
    void givenRepository_whenFindByBadName_thenNotFound() {
        optionalSociete = repository.findByName(UUID.randomUUID().toString());
        assertThat(optionalSociete)
                .isNotNull()
                .isNotPresent();
    }

    @Test
    void givenRepository_whenFindDeleted_thenNotFound() {
        entity.setDeleted(true);
        entity = repository.saveAndFlush(entity);
        optionalSociete = repository.findByName(entity.getName());
        assertThat(optionalSociete)
                .isNotNull()
                .isNotPresent();
    }

    @Test
    void givenRepository_whenFindByEmail_thenResult() {
        optionalSociete = repository.findByEmail(entity.getEmail());
        assertThat(optionalSociete)
                .isNotNull()
                .isPresent()
                .map(item -> item.getEmail()).hasValue(TestData.Default.email);
    }

    @Test
    void givenRepository_whenFindByBadEmail_thenNotFound() {
        optionalSociete = repository.findByEmail(UUID.randomUUID().toString());
        assertThat(optionalSociete)
                .isNotNull()
                .isNotPresent();
    }


    @Test
    void givenRepository_whenFindByPhone_thenResult() {
        optionalSociete = repository.findByPhone(entity.getPhone());
        assertThat(optionalSociete)
                .isNotNull()
                .isPresent()
                .map(item -> item.getPhone()).hasValue(TestData.Default.phone);
    }

    @Test
    void givenRepository_whenFindByBadPhone_thenNotFound() {
        optionalSociete = repository.findByPhone(UUID.randomUUID().toString());
        assertThat(optionalSociete)
                .isNotNull()
                .isNotPresent();
    }


}