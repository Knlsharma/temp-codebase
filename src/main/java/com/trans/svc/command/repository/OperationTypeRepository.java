package com.trans.svc.command.repository;

import com.trans.svc.command.model.OperationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OperationTypeRepository extends JpaRepository<OperationType, Integer> {
}
