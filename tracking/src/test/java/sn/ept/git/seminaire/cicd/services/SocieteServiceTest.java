package sn.ept.git.seminaire.cicd.services;

import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import sn.ept.git.seminaire.cicd.data.SocieteDTOTestData;
import sn.ept.git.seminaire.cicd.data.SocieteVMTestData;
import sn.ept.git.seminaire.cicd.dto.SocieteDTO;
import sn.ept.git.seminaire.cicd.dto.vm.SocieteVM;
import sn.ept.git.seminaire.cicd.mappers.SocieteMapper;
import sn.ept.git.seminaire.cicd.mappers.vm.SocieteVMMapper;
import sn.ept.git.seminaire.cicd.models.Societe;
import sn.ept.git.seminaire.cicd.repositories.SocieteRepository;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/*@SqlGroup({
        @Sql("classpath:0_societe_data_test.sql"),
})*/
class SocieteServiceTest extends ServiceBaseTest {

    @Autowired
    protected SocieteMapper mapper;
    @Autowired
    protected SocieteVMMapper vmMapper;
    @Autowired
    SocieteRepository societeRepository;
    @Autowired
    ISocieteService service;
    Optional<Societe> marque;
    SocieteVM vm = SocieteVMTestData.defaultVM();
    SocieteDTO dto;


    @Test
    void givenService_whenSaveSociete_thenSaved() {
        dto =service.save(vm);
        assertThat(dto).isNotNull();
        assertThat(dto.getId()).isNotNull();
    }


/*    D save(E vm);

    void delete(I i);

    Optional<D> findById(I i);

    List<D> findAll();

    Page<D> findAll(Pageable pageable);

    D update(I i, E vm);*/

}