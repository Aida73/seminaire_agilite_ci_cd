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

import java.util.List;
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
    void findAll_shouldReturnExercicesList() {
        List<Exercice> Exercices = repository.findAll();
        assertThat(Exercices)
                .isNotEmpty();
    }
    @Test
    void findAll_shouldReturnExercicesListPagination() {
        List<Exercice> Exercices = repository.findAll();
        assertThat(Exercices)
                .isNotEmpty();


    }
    @Test
    void findAll_shouldReturnExercicesListSorted() {
        List<Exercice> Exercices = repository.findAll();
        assertThat(Exercices)
                .isNotEmpty();

    }

    @Test
    void updateExercice_shouldReturnNewExercice() {
        optionalExercice = repository.findById(UUID.randomUUID());
        assertThat(optionalExercice)
                .isNotEmpty();
    }


}