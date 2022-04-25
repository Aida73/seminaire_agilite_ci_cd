package sn.ept.git.seminaire.cicd.repository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import sn.ept.git.seminaire.cicd.dto.SocieteDTO;
import sn.ept.git.seminaire.cicd.mappers.SocieteMapper;
import sn.ept.git.seminaire.cicd.models.Societe;
import sn.ept.git.seminaire.cicd.data.SiteDTOTestData;
import sn.ept.git.seminaire.cicd.dto.SiteDTO;
import sn.ept.git.seminaire.cicd.mappers.SiteMapper;
import sn.ept.git.seminaire.cicd.models.Site;
import sn.ept.git.seminaire.cicd.repositories.SiteRepository;
import sn.ept.git.seminaire.cicd.repositories.SocieteRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class SiteRepositoryTest extends RepositoryBaseTest {

    @Autowired
    private SiteMapper mapper;
    @Autowired
    private SiteRepository repository;

    @Autowired
    private SocieteRepository societeRepository;

    Societe societeEntity;

    static SiteDTO dto;
    static SocieteDTO societeDTO;

    @Autowired
    private SocieteMapper societeMapper;

    Site entity;
    Optional<Site> optionalSite;

    @BeforeAll
    static  void beforeAll() {
        dto = SiteDTOTestData.defaultDTO();
    }

    @BeforeEach
    void setUp() {
        repository.deleteAll();
        entity = mapper.asEntity(dto);
        entity = repository.saveAndFlush(entity);
    }

    @Test
    void FindByName_thenResult() {
        optionalSite = repository.findByName(entity.getName());
        assertThat(optionalSite)
                .isNotNull()
                .isPresent()
                .get()
                .usingRecursiveComparison()
                .isEqualTo(entity);
    }
    @Test
    void FindByPhone_thenResult() {
        optionalSite = repository.findByName(entity.getPhone());
        assertThat(optionalSite)
                .isNotNull()
                .isPresent()
                .get()
                .usingRecursiveComparison()
                .isEqualTo(entity);
    }

    @Test
    void FindByEmail_thenResult() {
        optionalSite = repository.findByName(entity.getEmail());
        assertThat(optionalSite)
                .isNotNull()
                .isPresent()
                .get()
                .usingRecursiveComparison()
                .isEqualTo(entity);
    }
    @Test
    void FindBySociete_thenResult() {
        optionalSite = repository.findByName(String.valueOf(entity.getSociete()));
        assertThat(optionalSite)
                .isNotNull()
                .isPresent()
                .get()
                .usingRecursiveComparison()
                .isEqualTo(entity);
    }

    @Test
    void FindByLatitude_thenResult() {
        optionalSite = repository.findByName(String.valueOf(entity.getLatitude()));
        assertThat(optionalSite)
                .isNotNull()
                .isPresent()
                .get()
                .usingRecursiveComparison()
                .isEqualTo(entity);
    }
    @Test
    void FindByLongitude_thenResult() {
        optionalSite = repository.findByName(String.valueOf(entity.getLatitude()));
        assertThat(optionalSite)
                .isNotNull()
                .isPresent()
                .get()
                .usingRecursiveComparison()
                .isEqualTo(entity);
    }

    @Test
    void FindByBadName_thenNotFound() {
        optionalSite = repository.findByName(UUID.randomUUID().toString());
        assertThat(optionalSite)
                .isNotNull()
                .isNotPresent();
    }

    @Test
    void FindDeleted_thenNotFound() {
        entity.setDeleted(true);
        entity = repository.saveAndFlush(entity);
        optionalSite = repository.findByName(entity.getName());
        assertThat(optionalSite)
                .isNotNull()
                .isNotPresent();
    }

    @Test
    void findByNameWithIdDifferent_thenResult() {
        optionalSite = repository.findByNameWithIdDifferent(entity.getName(),UUID.randomUUID());
        assertThat(optionalSite)
                .isNotNull()
                .isPresent()
                .get()
                .usingRecursiveComparison()
                .isEqualTo(entity);
    }

    @Test
    void findByNameWithIdDifferent_withSameId_shouldReturnNoResult() {
        optionalSite = repository.findByNameWithIdDifferent(entity.getName(),entity.getId());
        assertThat(optionalSite)
                .isNotNull()
                .isNotPresent();
    }

    @Test
    void findBySociete() {
        List<Site> optionalsite = repository.findBySociete(entity.getSociete());
        assertThat(optionalSite)
                .isNotEmpty();
    }
    @Test
    void findAllSites() {
        List<Site> optionalsite = repository.findAll();
        assertThat(optionalSite)
                .isNotEmpty();

    }
}