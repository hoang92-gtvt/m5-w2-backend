package com.example.connect.service;

import java.util.Optional;

public interface IGeneralService <T> {
    Iterable<T> findAll();
    Optional<T> findById(Long id);
    String save(T object);
    String delete(Long id);
}
