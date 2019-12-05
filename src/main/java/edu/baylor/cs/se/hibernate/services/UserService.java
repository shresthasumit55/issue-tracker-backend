package edu.baylor.cs.se.hibernate.services;

import edu.baylor.cs.se.hibernate.dao.ProjectDao;
import edu.baylor.cs.se.hibernate.dao.RoleDao;
import edu.baylor.cs.se.hibernate.dao.UserDao;
import edu.baylor.cs.se.hibernate.dto.LoginDto;
import edu.baylor.cs.se.hibernate.dto.UserDto;
import edu.baylor.cs.se.hibernate.exception.InsertFailureException;
import edu.baylor.cs.se.hibernate.exception.UpdateFailureException;
import edu.baylor.cs.se.hibernate.model.Project;
import edu.baylor.cs.se.hibernate.model.Role;
import edu.baylor.cs.se.hibernate.model.User;
import edu.baylor.cs.se.hibernate.model.UserRoleMapping;
import edu.baylor.cs.se.hibernate.utils.Encryption;
import org.apache.log4j.Logger;
import org.jasypt.util.password.BasicPasswordEncryptor;
import org.jasypt.util.text.BasicTextEncryptor;
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

    private static final Logger logger = Logger.getLogger(UserService.class);


    public User save(UserDto userDto) throws InsertFailureException{
        try {
            User user = new User();
            user.setFirstname(userDto.getFirstname());
            user.setMiddleName(userDto.getMiddleName());
            user.setLastName(userDto.getLastName());
            user.setEmail(userDto.getEmail());

            if (userDto.getProjectId() != null) {

                Project project = projectDao.getProjectById(userDto.getProjectId());
                Set<Project> projectSet = new HashSet<>();
                projectSet.add(project);
                user.setProjectsInvolved(projectSet);

                Role role = roleDao.getRoleById(userDto.getRoleId());

                UserRoleMapping roleMapping = new UserRoleMapping(user, role, project);
                Set<UserRoleMapping> roleMappingSet = new HashSet<>();
                roleMappingSet.add(roleMapping);
                user.setAvailableRoles(roleMappingSet);
            }
            user.setPassword(Encryption.encrypt("Company123"));
            user.setActiveStatus(true);

            userDao.save(user);

            logger.info("New User Added. user Id: " + user.getId().toString());
            return user;
        }catch (Exception e){
            logger.error("User could not be inserted");
            throw new InsertFailureException("User could not be updated");

        }

    }

    public void delete(Long id){
        try {
            userDao.delete(id);
            logger.info("User with id: " + id.toString() + " deleted");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void update(User user) throws UpdateFailureException {
        try {
            userDao.update(user);
            logger.info("User with id: " + user.getId().toString() + " updated.");
        }catch(Exception e){
            logger.error("User could not be updated");
            throw new UpdateFailureException("User could not be updated");
        }
    }

    public List<User> getAllUsers() {
        try {
            return userDao.getAllUsers();
        } catch (Exception e) {
            logger.error("User could not be fetched");
            e.printStackTrace();
            return null;
        }
    }

    public User getUserById(Long id) {
        try {
            User user = userDao.getUserById(id);
            return user;
        }catch(Exception e){
            logger.error("User could not be fetched");
            e.printStackTrace();
            return null;
        }
    }

    public User getUserByEmail(String email) {
        try {
            User user = userDao.getUserByEmail(email);
            return user;
        }catch(Exception e){
            logger.error("User could not be fetched");
            e.printStackTrace();
            return null;
        }
    }

    public User updateUserRoles(UserDto userDto){
        try {
            User user = userDao.getUserById(userDto.getId());
            Project project = projectDao.getProjectById(userDto.getProjectId());
            Role role = roleDao.getRoleById(userDto.getRoleId());

            Set<Project> projectSet = user.getProjectsInvolved();
            projectSet.add(project);

            project.getMembers().add(user);

            Set<UserRoleMapping> userRoleMappings = user.getAvailableRoles();
            userRoleMappings.add(new UserRoleMapping(user, role, project));

            userDao.update(user);
            logger.info("User role updated for user with id " + user.getId().toString());
            return user;
        }catch(Exception e){
            logger.error("Role could not be updated");
            e.printStackTrace();
            return null;
        }
    }


    public User authenticate(LoginDto loginDto){

        User user = getUserByEmail(loginDto.getEmail());

        if (user==null){
            logger.error("User with the email could not be found");
        }
        String plainText = Encryption.decrypt(user.getPassword());
        if (plainText.equals(loginDto.getPassword())){
            logger.info("User authenticated Successfully");
            return user;
        }else{
            logger.error("Password mismatch");
            return null;
        }

    }

    public User changePassword(LoginDto loginDto){

        User user = getUserByEmail(loginDto.getEmail());

        if (user==null){
            logger.error("User with the email could not be found");
        }

        if (Encryption.encrypt(loginDto.getPassword()).equals(user.getPassword())){
            String newEncryptedPassword = Encryption.encrypt(loginDto.getNewPassword());
            user.setPassword(newEncryptedPassword);
            userDao.update(user);
            return user;

        }else{
            logger.error("Old password was incorrect");
            return null;
        }

    }

}
