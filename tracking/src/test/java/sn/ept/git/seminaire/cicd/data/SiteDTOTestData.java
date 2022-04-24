package sn.ept.git.seminaire.cicd.data;

import sn.ept.git.seminaire.cicd.dto.SiteDTO;
import sn.ept.git.seminaire.cicd.dto.SocieteDTO;

public class SiteDTOTestData extends TestData{


    public static SiteDTO defaultDTO() {
        return SiteDTO
                .builder()
                .id(Default.id)
                .latitude(Default.latitude)
                .longitude(Default.longitude)
                .build();
    }


}
