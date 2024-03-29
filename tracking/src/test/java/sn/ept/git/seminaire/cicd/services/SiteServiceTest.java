package sn.ept.git.seminaire.cicd.services;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import sn.ept.git.seminaire.cicd.data.SiteVMTestData;
import sn.ept.git.seminaire.cicd.data.SocieteVMTestData;
import sn.ept.git.seminaire.cicd.data.TestData;
import sn.ept.git.seminaire.cicd.dto.SiteDTO;
import sn.ept.git.seminaire.cicd.dto.SocieteDTO;
import sn.ept.git.seminaire.cicd.dto.vm.SiteVM;
import sn.ept.git.seminaire.cicd.exceptions.ItemExistsException;
import sn.ept.git.seminaire.cicd.exceptions.ItemNotFoundException;
import sn.ept.git.seminaire.cicd.mappers.SiteMapper;
import sn.ept.git.seminaire.cicd.mappers.vm.SiteVMMapper;
import sn.ept.git.seminaire.cicd.models.Site;
import sn.ept.git.seminaire.cicd.repositories.SiteRepository;
import sn.ept.git.seminaire.cicd.services.impl.SocieteServiceImpl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/*@SqlGroup({
        @Sql("classpath:0_Site_data_test.sql"),
        @Sql("classpath:1_Site_data_test.sql"),
        @Sql("classpath:2_Site_data_test.sql"),
})*/
@Slf4j
class SiteServiceTest extends ServiceBaseTest {

    @Autowired
    protected SiteMapper mapper;
    @Autowired
    protected SiteVMMapper vmMapper;
    @Autowired
    SiteRepository siteRepository;
    @Autowired
    SocieteServiceImpl societeService;
    @Autowired
    ISiteService service;
    Optional<Site> site;
    static  SiteVM vm ;
    SiteDTO dto;





    @BeforeAll
    static void beforeAll(){
        log.info(" before all");
        vm = SiteVMTestData.defaultVM();

    }

    @BeforeEach
    void beforeEach(){
        log.info(" before each");
    }


    @Test
    void save_shouldReturnNewSite(){
        SocieteDTO societeDTO = societeService.save(SocieteVMTestData.defaultVM());
        vm.setIdSociete(societeDTO.getId());
        dto = service.save(vm);
        assertThat(dto)
                .isNotNull()
                .hasNoNullFieldsOrProperties();
    }

    @Test
    void save_withBadIdSociete_ShouldThrowException() {
        assertThrows(
                ItemNotFoundException.class,
                () ->service.save(vm)
        );

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
    void save_withSamePhone_shouldThrowException() {
        SocieteDTO societeDTO = societeService.save(SocieteVMTestData.defaultVM());
        vm.setIdSociete(societeDTO.getId());
        dto = service.save(vm);
        vm.setPhone(TestData.Update.phone);
        assertThrows(
                ItemExistsException.class,
                () -> service.save(vm)
        );
    }

    @Test
    void save_withSameEmail_shouldThrowException() {
        SocieteDTO societeDTO = societeService.save(SocieteVMTestData.defaultVM());
        vm.setIdSociete(societeDTO.getId());
        dto = service.save(vm);
        vm.setEmail(TestData.Update.email+UUID.randomUUID());
        assertThrows(
                ItemExistsException.class,
                () -> service.save(vm)
        );
    }


    @Test
    void findById_shouldReturnResult() {
        SocieteDTO societeDTO = societeService.save(SocieteVMTestData.defaultVM());
        vm.setIdSociete(societeDTO.getId());
        dto = service.save(vm);
        final Optional<SiteDTO> optional = service.findById(dto.getId());
        assertThat(optional)
                .isNotNull()
                .isPresent()
                .get()
                .hasNoNullFieldsOrProperties();
    }

    @Test
    void findById_withBadId_ShouldReturnNoResult() {
        final Optional<SiteDTO> optional = service.findById(UUID.randomUUID());
        assertThat(optional)
                .isNotNull()
                .isNotPresent();
    }

    @Test
    void delete_shouldDeleteSite() {
        SocieteDTO societeDTO = societeService.save(SocieteVMTestData.defaultVM());
        vm.setIdSociete(societeDTO.getId());
        dto = service.save(vm);
        long oldCount = siteRepository.count();
        service.delete(dto.getId());
        long newCount = siteRepository.count();
        assertThat(oldCount).isEqualTo(newCount+1);
    }


    @Test
    void delete_withBadId_ShouldThrowException() {
        assertThrows(
                ItemNotFoundException.class,
                () ->service.delete(UUID.randomUUID())
        );
    }

    @Test
    void findAllSite_ShouldReturnNOExercicess() {
        final List<SiteDTO> sites = service.findAll();
        assertThat(sites)
                .isEmpty();

    }

    @Test
    void findAllSite_ShouldReturnNoExercices() {

        final List<SiteDTO> sites = service.findAll();
        assertThat(sites)
                .isEmpty();
    }

    @Test
    void updateSite_verifyID(){
        SocieteDTO societeDTO = societeService.save(SocieteVMTestData.defaultVM());
        vm.setIdSociete(societeDTO.getId());
        dto = service.save(vm);
        final Optional<SiteDTO> optional = service.findById(dto.getId());
        assertThat(optional)
                .isNotNull();
    }


}