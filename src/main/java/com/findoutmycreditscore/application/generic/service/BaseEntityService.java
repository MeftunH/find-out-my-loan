package com.findoutmycreditscore.application.generic.service;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

import com.findoutmycreditscore.application.generic.errorMessage.GenericErrorMessage;
import com.findoutmycreditscore.application.generic.exceptions.ItemNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public abstract class BaseEntityService<Entity, Repository extends JpaRepository<Entity, Long>> {

    private final Repository repository;
    public List<Entity> findAll() {
        return repository.findAll();
    }

    public Optional<Entity> findById(Long id) {
        return repository.findById(id);
    }
    public Entity save(Entity entity) {
        return repository.save(entity);
    }
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
    public void delete(Entity entity) {
        repository.delete(entity);
    }

    public Entity getByIdWithControl(Long id) {
        Optional<Entity> entityOptional = findById(id);
        Entity _entity;
        if (entityOptional.isPresent()) {
            _entity = entityOptional.get();
        } else {
            throw new ItemNotFoundException(GenericErrorMessage.ITEM_NOT_FOUND);
        }
        return _entity;
    }
}
