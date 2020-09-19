package com.man.fota.domain.repository;

import com.man.fota.domain.entity.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfigurationRepository extends JpaRepository<Configuration, String> {

}
