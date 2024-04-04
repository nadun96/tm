package com.nadun.tm.service;

import com.nadun.tm.entity.Team;
import com.nadun.tm.entity.User;
import com.nadun.tm.repository.IUserRepository;
import com.nadun.tm.repository.ITeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final IUserRepository userRepository;
    private final ITeamRepository ITeamRepository;

    @Autowired
    public UserService(IUserRepository userRepository, ITeamRepository ITeamRepository) {
        this.userRepository = userRepository;
        this.ITeamRepository = ITeamRepository;
    }

    public User assignTeamToUser(Long userId, Long teamId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        Team team = ITeamRepository.findById(teamId)
                .orElseThrow(() -> new ResourceNotFoundException("Team not found with id: " + teamId));
        user.setTeam(team);
        return userRepository.save(user);
    }
}

