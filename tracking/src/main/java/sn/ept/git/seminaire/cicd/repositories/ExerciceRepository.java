package sn.ept.git.seminaire.cicd.repositories;

import org.springframework.data.repository.query.Param;
import sn.ept.git.seminaire.cicd.models.Exercice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sn.ept.git.seminaire.cicd.models.Societe;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ExerciceRepository extends JpaRepository<Exercice, UUID> {

    @Query("select e from Exercice  e where :start between e.start and e.end or :end between e.start and e.end ")
    Optional<Exercice> findByDates(Instant start, Instant end );

    @Query("select e from Exercice  e where e.name=:name")
    Optional<Exercice> findByName(String name);

    @Query("select e from Exercice  e where e.start=:start")
    Optional<Exercice> findByStart(Instant start);

    @Query("select e from Exercice  e where e.end=:end")
    Optional<Exercice> findByEnd(Instant end);


    @Query("select s from Societe  s where s.name=:name and s.id<>:id")
    Optional<Exercice> findByNameWithIdNotEqual(@Param("name") String name,@Param("id") UUID uuid);

}