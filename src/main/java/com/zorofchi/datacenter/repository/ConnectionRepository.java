package com.zorofchi.datacenter.repository;

import com.zorofchi.datacenter.entity.Connection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConnectionRepository extends JpaRepository<Connection , Long> {

    boolean existsBySourcePortId(Long portId);
    boolean existsByDestinationPortId(Long portId);


}
