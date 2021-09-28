package br.com.aaribeiro.icarros.repository;

import br.com.aaribeiro.icarros.model.Deal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DealRepository extends JpaRepository<Deal, Long> {
}
