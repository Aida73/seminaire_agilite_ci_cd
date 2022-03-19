package sn.ept.git.seminaire.cicd.resources;

import org.springframework.beans.factory.annotation.Autowired;
import sn.ept.git.seminaire.cicd.dto.SocieteDTO;
import sn.ept.git.seminaire.cicd.dto.vm.SocieteVM;
import sn.ept.git.seminaire.cicd.services.ISocieteService;

class SocieteResourceTest extends BasicResourceTest {


    @Autowired
    private ISocieteService service;
    private SocieteVM vm;
    private SocieteDTO dto;



}