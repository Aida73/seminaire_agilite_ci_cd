package sn.ept.git.seminaire.cicd.repository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import sn.ept.git.seminaire.cicd.data.ExerciceDTOTestData;
import sn.ept.git.seminaire.cicd.dto.ExerciceDTO;
import sn.ept.git.seminaire.cicd.mappers.ExerciceMapper;
import sn.ept.git.seminaire.cicd.models.Exercice;
import sn.ept.git.seminaire.cicd.repositories.ExerciceRepository;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class ExerciceRepositoryTest extends RepositoryBaseTest {

    @Autowired
    private ExerciceMapper mapper;
    @Autowired
    private ExerciceRepository repository;

    static ExerciceDTO dto;
    Exercice entity;
    Optional<Exercice> optionalExercice;

    @BeforeAll
    static void beforeAll(){
        dto = ExerciceDTOTestData.defaultDTO();
    }

    @BeforeEach
    void setUp() {
        entity = mapper.asEntity(dto);
        repository.deleteAll();
        entity = repository.saveAndFlush(entity);
    }
/*
    @Test
    void findByName_shouldRetrunResult() {
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
    void findByEmail_shouldReturnResult() {
        optionalExercice = repository.findByEmail(entity.getEmail());
        assertThat(optionalExercice)
                .isNotNull()
                .isPresent()
                .get()
                .usingRecursiveComparison()
                .isEqualTo(entity);
    }

    @Test
    void findByEmail_withBadEmail_shouldReturnNotFound() {
        optionalExercice = repository.findByEmail(UUID.randomUUID().toString());
        assertThat(optionalExercice)
                .isNotNull()
                .isNotPresent();
    }


    @Test
    void findByPhone_shouldReturnResult() {
        optionalExercice = repository.findByPhone(entity.getPhone());
        assertThat(optionalExercice)
                .isNotNull()
                .isPresent()
                .get()
                .usingRecursiveComparison()
                .isEqualTo(entity);
    }

    @Test
    void findByPhone_withBadPhone_shouldReturnResult() {
        optionalExercice = repository.findByPhone(UUID.randomUUID().toString());
        assertThat(optionalExercice)
                .isNotNull()
                .isNotPresent();
    }


    @Test
    void findByNameWithIdNotEqual_shouldReturnResult() {
        optionalExercice = repository.findByNameWithIdNotEqual(entity.getName(),UUID.randomUUID());
        assertThat(optionalExercice)
                .isNotNull()
                .isPresent()
                .get()
                .usingRecursiveComparison()
                .isEqualTo(entity);
    }

    @Test
    void  findByNameWithIdNotEqual_withSameId_shouldReturnNoResult () {
        optionalExercice = repository.findByNameWithIdNotEqual(entity.getName(),entity.getId());
        assertThat(optionalExercice)
                .isNotNull()
                .isNotPresent();
    }




    @Test
    void findByPhoneWithIdNotEqual_shouldReturnResult() {
        optionalExercice = repository.findByPhoneWithIdNotEqual(entity.getPhone(),UUID.randomUUID());
        assertThat(optionalExercice)
                .isNotNull()
                .isPresent()
                .get()
                .usingRecursiveComparison()
                .isEqualTo(entity);
    }

    @Test
    void findByPhoneWithIdNotEqual_withSameId_shouldReturnNoResult() {
        optionalExercice = repository.findByPhoneWithIdNotEqual(entity.getPhone(),entity.getId());
        assertThat(optionalExercice)
                .isNotNull()
                .isNotPresent();
    }


    @Test
    void findByEmailWithIdNotEqual_shouldReturnResult() {
        optionalExercice = repository.findByEmailWithIdNotEqual(entity.getEmail(),UUID.randomUUID());
        assertThat(optionalExercice)
                .isNotNull()
                .isPresent()
                .get()
                .usingRecursiveComparison()
                .isEqualTo(entity);
    }

    @Test
    void findByEmailWithIdNotEqual_withSameId_shouldReturnNoResult() {
        optionalExercice = repository.findByEmailWithIdNotEqual(entity.getEmail(),entity.getId());
        assertThat(optionalExercice)
                .isNotNull()
                .isNotPresent();
    }
*/
}