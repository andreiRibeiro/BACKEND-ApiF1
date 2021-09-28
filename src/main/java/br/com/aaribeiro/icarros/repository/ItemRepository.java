package br.com.aaribeiro.icarros.repository;

import br.com.aaribeiro.icarros.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
