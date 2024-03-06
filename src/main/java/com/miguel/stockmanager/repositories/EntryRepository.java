package com.miguel.stockmanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.miguel.stockmanager.models.EntryModel;

public interface EntryRepository extends JpaRepository<EntryModel, Long> {

}
