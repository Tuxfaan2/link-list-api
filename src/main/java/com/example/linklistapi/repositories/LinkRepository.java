package com.example.linklistapi.repositories;

import com.example.linklistapi.models.Link;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkRepository extends CrudRepository<Link, Long> {
}
