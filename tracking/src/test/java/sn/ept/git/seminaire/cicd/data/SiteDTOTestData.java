package sn.ept.git.seminaire.cicd.data;

import sn.ept.git.seminaire.cicd.dto.SiteDTO;

public final class SiteDTOTestData extends TestData{

    public static SiteDTO defaultDTO() {

        return SiteDTO
                .builder()
                .id(Default.id)
                .phone(Default.phone)
                .lastModifiedDate(Default.lastModifiedDate)
                .version(Default.version)
                .deleted(Default.deleted)
                .enabled(Default.enabled)
                .longitude(Default.longitude)
                .latitude(Default.latitude)
                .name(Default.name)
                .createdDate(Default.createdDate)
                .email(Default.email)
                .societe(Default.societe)
                .build();

    }
    public static SiteDTO updatedDTO() {
        return SiteDTO
                .builder()
                .id(Default.id)
                .createdDate(Update.createdDate)
                .lastModifiedDate(Update.lastModifiedDate)
                .version(Update.version)
                .deleted(Update.deleted)
                .enabled(Update.enabled)
                .name(Update.name)
                .phone(Update.phone)
                .email(Update.email)
                .longitude(Update.longitude)
                .latitude(Update.latitude)
                .build();
    }


}