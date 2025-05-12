package com.example.collectionBox.repository;

import com.example.collectionBox.model.MoneyEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoneyEntryRepository extends JpaRepository<MoneyEntry, Long> {

}
