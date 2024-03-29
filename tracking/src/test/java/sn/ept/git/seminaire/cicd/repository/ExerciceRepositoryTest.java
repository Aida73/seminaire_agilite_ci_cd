package sn.ept.git.seminaire.cicd.repository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import sn.ept.git.seminaire.cicd.data.ExerciceDTOTestData;
import sn.ept.git.seminaire.cicd.data.ExerciceVMTestData;
import sn.ept.git.seminaire.cicd.data.SocieteVMTestData;
import sn.ept.git.seminaire.cicd.dto.ExerciceDTO;
import sn.ept.git.seminaire.cicd.dto.SocieteDTO;
import sn.ept.git.seminaire.cicd.dto.vm.ExerciceVM;
import sn.ept.git.seminaire.cicd.mappers.ExerciceMapper;
import sn.ept.git.seminaire.cicd.models.Exercice;
import sn.ept.git.seminaire.cicd.repositories.ExerciceRepository;
import sn.ept.git.seminaire.cicd.repositories.SocieteRepository;
import sn.ept.git.seminaire.cicd.services.IExerciceService;
import sn.ept.git.seminaire.cicd.services.impl.SocieteServiceImpl;

import java.io.ObjectInputFilter;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;
import java.util.Properties;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.INSTANT;

class ExerciceRepositoryTest extends RepositoryBaseTest {

    @Autowired
    private ExerciceMapper mapper;
    @Autowired
    private ExerciceRepository repository;
    @Autowired
    private SocieteRepository societeRepository;
    @Autowired
    SocieteServiceImpl societeService;
    @Autowired
    IExerciceService service;
    static ExerciceVM vm;

    static ExerciceDTO dto;
    Exercice entity;
    Optional<Exercice> optionalExercice;

    @BeforeAll
    static void beforeAll(){

        dto = ExerciceDTOTestData.defaultDTO();
        vm = ExerciceVMTestData.defaultVM();
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

        optionalExercice = repository.findByName(entity.getName());
        assertThat(optionalExercice)
                .isNotNull()
                .isPresent()
                .get()
                .usingRecursiveComparison()
                .isEqualTo(entity);
    }


    @Test
    void findByName_withBadName_shouldReturnNotFound() {
        optionalExercice = repository.findByName(UUID.randomUUID().toString());
        assertThat(optionalExercice)
                .isNotNull()
                .isNotPresent();
    }

    @Test
    void findByName_afterDelete_shouldReturnNotFound() {
        entity.setDeleted(true);
        entity = repository.saveAndFlush(entity);
        optionalExercice = repository.findByName(entity.getName());
        assertThat(optionalExercice)
                .isNotNull()
                .isNotPresent();
    }



    @Test
    void findByStart_shouldReturnResult() {
        optionalExercice = repository.findByStart(entity.getStart());
        assertThat(optionalExercice)
                .isNotNull()
                .isPresent()
                .get()
                .usingRecursiveComparison()
                .isEqualTo(entity);
    }


    @Test
    void findByStart_shouldReturnNOResult() {
        optionalExercice = repository.findByStart(Instant.now());
        assertThat(optionalExercice)
                .isNotNull();

    }

    @Test
    void findByEnd_shouldReturnResult() {
        optionalExercice = repository.findByEnd(entity.getEnd());
        assertThat(optionalExercice)
                .isNotNull()
                .isPresent()
                .get()
                .usingRecursiveComparison()
                .isEqualTo(entity);
    }



    @Test
    void  findByNameWithIdNotEqual_withSameId_shouldReturnNoResult () {
        optionalExercice = repository.findByNameWithIdNotEqual(entity.getName()+"vbj",entity.getId());
        assertThat(optionalExercice)
                .isNotNull()
                .isNotPresent();
    }





}
