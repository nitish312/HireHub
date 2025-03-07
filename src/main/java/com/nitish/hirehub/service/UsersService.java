package com.nitish.hirehub.service;

import com.nitish.hirehub.entity.JobSeekerProfile;
import com.nitish.hirehub.entity.RecruiterProfile;
import com.nitish.hirehub.entity.Users;
import com.nitish.hirehub.repo.JobSeekerProfileRepository;
import com.nitish.hirehub.repo.RecruiterProfileRepository;
import com.nitish.hirehub.repo.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UsersService {

    @Autowired
    private final UsersRepository usersRepository;
    @Autowired
    private final JobSeekerProfileRepository jobSeekerProfileRepository;
    @Autowired
    private final RecruiterProfileRepository recruiterProfileRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository, JobSeekerProfileRepository jobSeekerProfileRepository, RecruiterProfileRepository recruiterProfileRepository) {
        this.usersRepository = usersRepository;
        this.jobSeekerProfileRepository = jobSeekerProfileRepository;
        this.recruiterProfileRepository = recruiterProfileRepository;
    }

    public Users addNew(Users users) {
        users.setActive(true);
        users.setRegistrationDate(new Date(System.currentTimeMillis()));
        Users savedUser = usersRepository.save(users);
        int userTypeId = users.getUserTypeId().getUserTypeId();

        if (userTypeId == 1) {
            recruiterProfileRepository.save(new RecruiterProfile(savedUser));
        }
        else {
            jobSeekerProfileRepository.save(new JobSeekerProfile(savedUser));
        }

        return savedUser;
    }

    public Optional<Users> getUserByEmail(String email) {
        return usersRepository.findByEmail(email);
    }

}