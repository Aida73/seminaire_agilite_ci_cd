package sn.ept.git.seminaire.cicd.services;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import sn.ept.git.seminaire.cicd.data.ExerciceVMTestData;
import sn.ept.git.seminaire.cicd.data.SocieteVMTestData;
import sn.ept.git.seminaire.cicd.data.TestData;
import sn.ept.git.seminaire.cicd.dto.ExerciceDTO;
import sn.ept.git.seminaire.cicd.dto.SocieteDTO;
import sn.ept.git.seminaire.cicd.dto.vm.ExerciceVM;
import sn.ept.git.seminaire.cicd.exceptions.ItemExistsException;
import sn.ept.git.seminaire.cicd.exceptions.ItemNotFoundException;
import sn.ept.git.seminaire.cicd.mappers.ExerciceMapper;
import sn.ept.git.seminaire.cicd.mappers.vm.ExerciceVMMapper;
import sn.ept.git.seminaire.cicd.models.Exercice;
import sn.ept.git.seminaire.cicd.repositories.ExerciceRepository;
import sn.ept.git.seminaire.cicd.services.impl.SocieteServiceImpl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
public class ExerciceServiceTest extends ServiceBaseTest {

    @Autowired
    protected ExerciceMapper mapper;
    @Autowired
    protected ExerciceVMMapper vmMapper;
    @Autowired
    ExerciceRepository exerciceRepository;
    @Autowired
    SocieteServiceImpl societeService;
    @Autowired
    IExerciceService service;
    Optional<Exercice> exercice;
    static ExerciceVM vm;
    ExerciceDTO dto;

    @BeforeAll
    static void beforeAll() {
        log.info(" before all");
        vm = ExerciceVMTestData.defaultVM();
    }

    @BeforeEach
    void beforeEach() {
        log.info(" before each");
    }


    @Test
    void save_shouldSaveExercice() {
        SocieteDTO societeDTO = societeService.save(SocieteVMTestData.defaultVM());
        vm.setIdSociete(societeDTO.getId());
        dto = service.save(vm);
        assertThat(dto)
                .isNotNull()
                .hasNoNullFieldsOrProperties();
    }


    @Test
    void save_withSameName_shouldThrowException() {
        SocieteDTO societeDTO = societeService.save(SocieteVMTestData.defaultVM());
        vm.setIdSociete(societeDTO.getId());
        dto = service.save(vm);
        vm.setName(TestData.Update.name);
        assertThrows(
                ItemExistsException.class,
                () -> service.save(vm)
        );
    }

    @Test
    void delete_shouldDeleteExercice() {
        SocieteDTO societeDTO = societeService.save(SocieteVMTestData.defaultVM());
        vm.setIdSociete(societeDTO.getId());
        dto = service.save(vm);
        long oldCount = exerciceRepository.count();
        service.delete(dto.getId());
        long newCount = exerciceRepository.count();
        assertThat(oldCount).isEqualTo(newCount + 1);
    }



    @Test
    void delete_withBadId_ShouldThrowException() {
        assertThrows(
                ItemNotFoundException.class,
                () -> service.delete(UUID.randomUUID())
        );
    }


    @Test
    void findAllSociete_ShouldReturnNOExercicess() {
        final List<ExerciceDTO> exercices = service.findAll();
        assertThat(exercices)
                .isEmpty();

    }

    @Test
    void findAllSociete_ShouldReturnExercicess() {
        final List<ExerciceDTO> exercices = service.findAll();
        assertThat(exercices)
                .isEmpty();

    }



    @Test
    void updateSociete_verifyID(){
        SocieteDTO societeDTO = societeService.save(SocieteVMTestData.defaultVM());
        vm.setIdSociete(societeDTO.getId());
        dto = service.save(vm);
        final Optional<ExerciceDTO> optional = service.findById(dto.getId());
        assertThat(optional)
                .isNotNull();
    }
}