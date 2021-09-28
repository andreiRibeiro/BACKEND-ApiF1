package br.com.aaribeiro.icarros.repository;

import br.com.aaribeiro.icarros.model.Historic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface HistoricRepository extends JpaRepository<Historic, Long> {

    @Query("SELECT h FROM Historic h " +
            "ORDER BY id DESC")
    List<Historic> findAllOrderByIdDesc();
}
