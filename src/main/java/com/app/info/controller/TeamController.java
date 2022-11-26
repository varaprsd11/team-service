package com.app.info.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.info.exception.ResourceNotFoundException;
import com.app.info.model.Team;
import com.app.info.repository.TeamRepository;

@RestController
@RequestMapping("/api")
public class TeamController {
	
	@Autowired
	private TeamRepository teamRepository;
	
	@RequestMapping("/test")
	public String test() {
		return "App UP and Running..!";
	}
	

	// get all users
	@GetMapping("/getAllTeam")
	public List<Team> getAllUsers() {
		return this.teamRepository.findAll();
	}

	// get user by id
	@GetMapping("byPerson/{id}")
	public Team getUserById(@PathVariable (value = "id") long userId) {
		return this.teamRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + userId));
	}

	// create user
	@PostMapping("/save")
	public Team createUser(@RequestBody Team team) {
		return this.teamRepository.save(team);
	}
	
	// update user
	@PutMapping("update/{id}")
	public Team updateUser(@RequestBody Team team, @PathVariable ("id") long userId) {
		Team existingTeam = this.teamRepository.findById(userId)
			.orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + userId));
		existingTeam.setId(userId);
		existingTeam.setAddress(team.getAddress());
		existingTeam.setDesign(team.getDesign());
		existingTeam.setEmp_id(team.getEmp_id());
		existingTeam.setManager_name(team.getManager_name());
		existingTeam.setName(team.getName());
		 return this.teamRepository.save(existingTeam);
	}
	
	// delete user by id
	@DeleteMapping("delete/{id}")
	public ResponseEntity<Team> deleteUser(@PathVariable ("id") long userId){
		Team existingTeam = this.teamRepository.findById(userId)
					.orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + userId));
		 this.teamRepository.delete(existingTeam);
		 return ResponseEntity.ok().build();
	}

}
