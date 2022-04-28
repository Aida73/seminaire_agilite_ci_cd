package sn.ept.git.seminaire.cicd.repository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import sn.ept.git.seminaire.cicd.data.SiteDTOTestData;
import sn.ept.git.seminaire.cicd.data.SiteVMTestData;
import sn.ept.git.seminaire.cicd.data.SocieteVMTestData;
import sn.ept.git.seminaire.cicd.dto.SiteDTO;
import sn.ept.git.seminaire.cicd.dto.SocieteDTO;
import sn.ept.git.seminaire.cicd.dto.vm.SiteVM;
import sn.ept.git.seminaire.cicd.mappers.SiteMapper;
import sn.ept.git.seminaire.cicd.models.Site;
import sn.ept.git.seminaire.cicd.repositories.SiteRepository;
import sn.ept.git.seminaire.cicd.repositories.SocieteRepository;
import sn.ept.git.seminaire.cicd.services.ISiteService;
import sn.ept.git.seminaire.cicd.services.impl.SocieteServiceImpl;

import java.io.ObjectInputFilter;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;
import java.util.Properties;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.INSTANT;

class SiteRepositoryTest extends RepositoryBaseTest {

    @Autowired
    private SiteMapper mapper;
    @Autowired
    private SiteRepository repository;
    @Autowired
    private SocieteRepository societeRepository;
    @Autowired
    SocieteServiceImpl societeService;
    @Autowired
    ISiteService service;
    static SiteVM vm;

    static SiteDTO dto;
    Site entity;
    Optional<Site> optionalSite;

    @BeforeAll
    static void beforeAll(){

        dto = SiteDTOTestData.defaultDTO();
        vm = SiteVMTestData.defaultVM();
    }

    @BeforeEach
    void setUp() {
        SocieteDTO societeDTO = societeService.save(SocieteVMTestData.defaultVM());
        vm.setIdSociete(societeDTO.getId());
        dto = service.save(vm);
        entity = mapper.asEntity(dto);
        repository.deleteAll();
        entity = repository.saveAndFlush(entity);


    }


    @Test
    void findByName_shouldReturnResult() {

        optionalSite = repository.findByName(entity.getName());
        assertThat(optionalSite)
                .isNotNull()
                .isPresent()
                .get()
                .usingRecursiveComparison()
                .isEqualTo(entity);
    }

/*
    @Test
    void findByName_withBadName_shouldReturnNotFound() {
        optionalSite = repository.findByName(UUID.randomUUID().toString());
        assertThat(optionalSite)
                .isNotNull()
                .isNotPresent();
    }

    @Test
    void findByName_afterDelete_shouldReturnNotFound() {
        entity.setDeleted(true);
        entity = repository.saveAndFlush(entity);
        optionalSite = repository.findByName(entity.getName());
        assertThat(optionalSite)
                .isNotNull()
                .isNotPresent();
    }



    @Test
    void findByStart_shouldReturnResult() {
        optionalSite = repository.findByStart(entity.getStart());
        assertThat(optionalSite)
                .isNotNull()
                .isPresent()
                .get()
                .usingRecursiveComparison()
                .isEqualTo(entity);
    }


    @Test
    void findByStart_shouldReturnNOResult() {
        optionalSite = repository.findByStart(Instant.now());
        assertThat(optionalSite)
                .isNotNull();

    }

    @Test
    void findByEnd_shouldReturnResult() {
        optionalSite = repository.findByEnd(entity.getEnd());
        assertThat(optionalSite)
                .isNotNull()
                .isPresent()
                .get()
                .usingRecursiveComparison()
                .isEqualTo(entity);
    }



    @Test
    void  findByNameWithIdNotEqual_withSameId_shouldReturnNoResult () {
        optionalSite = repository.findByNameWithIdNotEqual(entity.getName()+"vbj",entity.getId());
        assertThat(optionalSite)
                .isNotNull()
                .isNotPresent();
    }



*/

}
