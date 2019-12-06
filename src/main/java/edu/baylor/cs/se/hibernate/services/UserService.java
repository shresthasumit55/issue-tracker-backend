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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Service Component for User that includes the business logic
 * To perform CRUD operation on User
 */
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


    /**
     * Method to save a new user
     * It is used to add a project for the user and the role of the user in that project
     * @param userDto, user detail submitted from UI
     * @return User
     * @throws InsertFailureException
     */
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

    /**
     * Method to delete an existing user based on the id provided
     * @param id, id of the user that needs to be deleted
     */
    public void delete(Long id){
        try {
            userDao.delete(id);
            logger.info("User with id: " + id.toString() + " deleted");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Method to update existing user
     * @param user, user that needs to be updated
     * @throws UpdateFailureException
     */
    public void update(User user) throws UpdateFailureException {
        try {
            userDao.update(user);
            logger.info("User with id: " + user.getId().toString() + " updated.");
        }catch(Exception e){
            logger.error("User could not be updated");
            throw new UpdateFailureException("User could not be updated");
        }
    }

    /**
     * Method to get the list of all users
     * @return List of users
     */
    public List<User> getAllUsers() {
        try {
            return userDao.getAllUsers();
        } catch (Exception e) {
            logger.error("User could not be fetched");
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Method to get a particular user based on the ID provided
     * @param id, id of a particular user
     * @return User
     */
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

    /**
     * Method to get a particular user based on the email provided
     * It is used to change the password of the user
     * @param email, email of the user
     * @return User
     */
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

    /**
     * Method to update the role of the user
     * It is used during edit roles where the role of the user can be changed based on each project
     * @param userDto, user detail submitted from UI
     * @return User
     */
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

    /**
     * Method to check whether the email and password provided during login is valid
     * It decrypts the password provided during login and compares it with the stored laptop
     * @param loginDto, login detail submitted from UI
     * @return user, user whose login is provided
     */
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

    /**
     * Method to change the password of User
     * It first confirms that the existing password provided by the user is valid
     * ANd encrypts and stores the new Password provided by the user
     * @param loginDto, login detail submitted from UI
     * @return User
     */
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
