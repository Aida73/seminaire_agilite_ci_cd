package sn.ept.git.seminaire.cicd.mapper;

import sn.ept.git.seminaire.cicd.data.ExerciceDTOTestData;
import sn.ept.git.seminaire.cicd.dto.ExerciceDTO;
import sn.ept.git.seminaire.cicd.mappers.ExerciceMapper;
import sn.ept.git.seminaire.cicd.models.Exercice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.assertj.core.api.Assertions.assertThat;

class ExerciceDTOMapperTest extends  MapperBaseTest{

    ExerciceDTO dto;
    Exercice entity;

    @Autowired
    private ExerciceMapper mapper;


    @BeforeEach
    void setUp() {
        dto = ExerciceDTOTestData.defaultDTO();
    }

    @Test
    void toEntityShouldReturnCorrectEntity() {
        entity = mapper.asEntity(dto);
        assertThat(entity)
                .isNotNull()
                .usingRecursiveComparison()
                .ignoringFields("interventions", "societe")
                .isEqualTo(dto);
    }

    @Test
    void toDTOShouldReturnCorrectDTO() {
        entity = mapper.asEntity(dto);
        dto =mapper.asDTO(entity);
        assertThat(dto)
                .isNotNull()
                .hasNoNullFieldsOrProperties()
                .usingRecursiveComparison()
                .ignoringFieldsMatchingRegexes("^_")//just to discover
                .withEqualsForFields((idOne,idTwo)-> {
                    //use lambda
                    return  idOne instanceof String && idTwo.toString().equalsIgnoreCase(idOne.toString());
                },"id") //just to discover
                .isEqualTo(entity);

    }
}