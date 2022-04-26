package sn.ept.git.seminaire.cicd.services;

import sn.ept.git.seminaire.cicd.dto.SocieteDTO;
import sn.ept.git.seminaire.cicd.dto.vm.SocieteVM;
import sn.ept.git.seminaire.cicd.services.generic.GenericService;

import java.util.Optional;
import java.util.UUID;


public interface ISocieteService extends GenericService<SocieteDTO, SocieteVM, UUID> {

    Optional<SocieteDTO> findByName(String name);

    Optional<SocieteDTO> findByPhone(String phone);

    Optional<SocieteDTO> findByEmail(String email);

    Optional<SocieteDTO> findByAddress(String s);

    Optional<SocieteDTO> findByLatitude(float v);

    Optional<SocieteDTO> findByLongitude(float v);

}
