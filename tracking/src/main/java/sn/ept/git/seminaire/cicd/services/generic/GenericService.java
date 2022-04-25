package sn.ept.git.seminaire.cicd.services.generic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sn.ept.git.seminaire.cicd.dto.SocieteDTO;

import java.util.List;
import java.util.Optional;

public interface GenericService<D,V, I> {

    D save(V vm);

    void delete(I id);

    Optional<D> findById(I id);


    List<D> findAll();

    Page<D> findAll(Pageable pageable);

    D update(I id, V vm);

    void deleteAll();

   /* Optional<D> findByName(String name);

    Optional<D> findByPhone(String phone);

    Optional<D> findByEmail(String email);

    Optional<D> findByAddresse(String addresse);

    Optional<D> findByLatitude(float latitude);

    Optional<D> findByLongitude(float longitude);*/
}