package ru.itmentor.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.repository.RoleRepository;

import java.util.List;

@Service
public class RoleServiseImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiseImpl(RoleRepository roleRepository) {

        this.roleRepository = roleRepository;
    }


    @Override
    public List<Role> findAllRolles() {
        return roleRepository.findAll();
    }
}
