package edu.baylor.cs.se.hibernate;

import edu.baylor.cs.se.hibernate.dto.UserDto;
import edu.baylor.cs.se.hibernate.exception.InsertFailureException;
import edu.baylor.cs.se.hibernate.exception.UpdateFailureException;
import edu.baylor.cs.se.hibernate.model.User;
import edu.baylor.cs.se.hibernate.services.UserService;
import edu.baylor.cs.se.hibernate.utils.Encryption;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UserServiceTests {

    @Autowired
    UserService userService;

    private UserDto createUserData(){
        UserDto userDto = new UserDto();
        userDto.setEmail("testemail@gmail.com");
        userDto.setFirstname("Blake");
        userDto.setLastName("Jones");
        return userDto;
    }

    @Test
    public void checkPasswordEncryption(){
        String password = "Password123";
        String encrypted = Encryption.encrypt(password);
        String decrypted = Encryption.decrypt(encrypted);
        Assert.assertTrue(password.equals(decrypted));
    }

    @Test
    public void checkSaveUserFunctionality(){
        try {
            UserDto userDto = createUserData();
            userService.save(userDto);
            User fetchedUser = userService.getUserByEmail(userDto.getEmail());
            Assert.assertTrue(fetchedUser.getEmail().equals(userDto.getEmail()));
        }catch(InsertFailureException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void checkUpdateUserFunctionality(){

        try {
            UserDto userDto = createUserData();
            userDto.setEmail("test3@test.com");
            userService.save(userDto);

            String newEmail = "test333@test.com";
            User fetchedUser = userService.getUserByEmail(userDto.getEmail());

            fetchedUser.setEmail(newEmail);
            userService.update(fetchedUser);

            User updatedUser = userService.getUserById(fetchedUser.getId());
            Assert.assertTrue(updatedUser.getEmail().equals(newEmail));
        }catch(UpdateFailureException e){
            System.out.println(e.getMessage());
        }catch(InsertFailureException e){
            System.out.println(e.getMessage());
        }

    }


}
