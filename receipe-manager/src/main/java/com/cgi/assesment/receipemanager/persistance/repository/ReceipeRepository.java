package com.cgi.assesment.receipemanager.persistance.repository;

import com.cgi.assesment.receipemanager.model.Receipe;
import com.cgi.assesment.receipemanager.persistance.entity.ReceipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceipeRepository extends JpaRepository<ReceipeEntity, Long> {
    List<ReceipeEntity> findByIngredientsContainingAll(List<String> ingredients);
}
