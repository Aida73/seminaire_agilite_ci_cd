package sn.ept.git.seminaire.cicd.data;

import sn.ept.git.seminaire.cicd.dto.vm.ExerciceVM;
import sn.ept.git.seminaire.cicd.models.Exercice;

import java.time.Instant;

public final class ExerciceVMTestData extends TestData {

    public static ExerciceVM defaultVM() {
        return ExerciceVM
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

    public static ExerciceVM updatedVM() {
        return ExerciceVM
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
