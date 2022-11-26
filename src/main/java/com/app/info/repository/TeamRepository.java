package com.app.info.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.info.model.Team;

public interface TeamRepository extends JpaRepository<Team, Long>{

}
