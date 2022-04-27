package sn.ept.git.seminaire.cicd.repositories;

import org.springframework.data.repository.query.Param;
import sn.ept.git.seminaire.cicd.enums.StatusExercice;
import sn.ept.git.seminaire.cicd.models.Exercice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sn.ept.git.seminaire.cicd.models.Societe;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ExerciceRepository extends JpaRepository<Exercice, UUID> {

    @Query("select e from Exercice  e where :start between e.start and e.end or :end between e.start and e.end ")
    Optional<Exercice> findByDates(Instant start, Instant end );

    @Query("select  s from Exercice  s where s.name=:name")
    Optional<Exercice> findByName(@Param("name") String name);

}