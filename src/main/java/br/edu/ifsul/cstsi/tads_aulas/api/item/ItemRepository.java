package br.edu.ifsul.cstsi.tads_aulas.api.item;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}