package edu.baylor.cs.se.hibernate.services;

import edu.baylor.cs.se.hibernate.dao.ProjectDao;
import edu.baylor.cs.se.hibernate.dao.RoleDao;
import edu.baylor.cs.se.hibernate.dao.UserDao;
import edu.baylor.cs.se.hibernate.dto.UserDto;
import edu.baylor.cs.se.hibernate.model.Project;
import edu.baylor.cs.se.hibernate.model.Role;
import edu.baylor.cs.se.hibernate.model.User;
import edu.baylor.cs.se.hibernate.model.UserRoleMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Transactional
@Service
public class UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    ProjectDao projectDao;

    @Autowired
    RoleDao roleDao;

    public User save(UserDto userDto) {
        User user = new User();
        user.setFirstname(userDto.getFirstname());
        user.setMiddleName(userDto.getMiddleName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());

        Project project = projectDao.getProjectById(userDto.getProjectId());
        Set<Project> projectSet = new HashSet<>();
        projectSet.add(project);
        user.setProjectsInvolved(projectSet);

        Role role = roleDao.getRoleById(userDto.getRoleId());

        UserRoleMapping roleMapping = new UserRoleMapping(user,role,project);
        Set<UserRoleMapping> roleMappingSet = new HashSet<>();
        roleMappingSet.add(roleMapping);
        user.setAvailableRoles(roleMappingSet);

        userDao.save(user);
        return user;
    }

    public void delete(Long id) { userDao.delete(id);}

    public void update(User user) { userDao.update(user);}

    public List<User> getAllUsers() { return userDao.getAllUsers();}

    public User getUserById(Long id) {
        User user =   userDao.getUserById(id);
        return user;
    }

    public User updateUserRoles(UserDto userDto){
        User user = userDao.getUserById(userDto.getId());
        Project project = projectDao.getProjectById(userDto.getProjectId());
        Role role = roleDao.getRoleById(userDto.getRoleId());

        Set<Project> projectSet = user.getProjectsInvolved();
        projectSet.add(project);

        project.getMembers().add(user);

        Set<UserRoleMapping> userRoleMappings = user.getAvailableRoles();
        userRoleMappings.add(new UserRoleMapping(user,role,project));

        userDao.update(user);
        return user;
    }

}
