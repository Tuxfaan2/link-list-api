package de.tuxfaan2.linklistapi.repositories;

import de.tuxfaan2.linklistapi.models.Link;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkRepository extends CrudRepository<Link, Long> {
}
