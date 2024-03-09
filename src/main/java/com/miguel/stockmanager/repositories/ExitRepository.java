package com.miguel.stockmanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.miguel.stockmanager.models.ExitModel;

public interface ExitRepository extends JpaRepository<ExitModel, Long> {

}
