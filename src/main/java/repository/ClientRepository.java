package repository;

import model.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {

    @Query("SELECT c.id FROM Client c WHERE c.isStudent=true")
    List<Client> findByStudentTrue();

    @Query("SELECT c.lastName FROM Client c ORDER BY c.age ASC")
    List<Client> findLastNameOrderByAge();

    @Query("SELECT c.lastName FROM Client c WHERE c.age <= :wantedAge")
    List<Client> findLastNameAgeLessEqualThan(@Param("wantedAge") int wantedAge);

    @Query("SELECT c FROM Client c WHERE c.id = :wantedId")
    Client findFirstById(@Param("wantedId") Long wantedId);

    List<Client> findAllByFirstNameLike(String pattern);

    @Query("SELECT c.firstName FROM Client c WHERE c.firstName LIKE concat(:wantedLetter, '%')")
    List<Client> findAllByFirstNameStartingWithLetter(@Param("wantedLetter") char wantedLetter);

}
