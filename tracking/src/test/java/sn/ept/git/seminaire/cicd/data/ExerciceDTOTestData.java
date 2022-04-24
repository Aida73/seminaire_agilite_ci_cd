package sn.ept.git.seminaire.cicd.data;

import sn.ept.git.seminaire.cicd.dto.ExerciceDTO;
import sn.ept.git.seminaire.cicd.dto.SiteDTO;
import sn.ept.git.seminaire.cicd.dto.SocieteDTO;

import java.time.Instant;
import java.util.Date;

public final class ExerciceDTOTestData extends TestData{

    public static ExerciceDTO defaultDTO() {

        return ExerciceDTO
                .builder()
                .id(Default.id)
                .lastModifiedDate(Default.lastModifiedDate)
                .version(Default.version)
                .deleted(Default.deleted)
                .enabled(Default.enabled)
                .name(Default.name)
                .createdDate(Default.createdDate)
                .start(Instant.now())
                .end(Instant.now())
                .status(null)
                .build();

    }
    public static ExerciceDTO updatedDTO() {
        return ExerciceDTO
                .builder()
                .id(Default.id)
                .lastModifiedDate(Default.lastModifiedDate)
                .version(Default.version)
                .deleted(Default.deleted)
                .enabled(Default.enabled)
                .name(Default.name)
                .createdDate(Default.createdDate)
                .start(Instant.now())
                .end(Instant.now())
                .status(null)
                .build();
    }


}
