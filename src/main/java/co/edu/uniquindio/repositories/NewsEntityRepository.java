package co.edu.uniquindio.repositories;

import co.edu.uniquindio.model.PayRollNews;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsEntityRepository extends CrudRepository<PayRollNews, Integer> {
}
