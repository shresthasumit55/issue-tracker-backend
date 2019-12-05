package edu.baylor.cs.se.hibernate;

import edu.baylor.cs.se.hibernate.dto.IssueDto;
import edu.baylor.cs.se.hibernate.dto.ProjectDto;
import edu.baylor.cs.se.hibernate.model.Project;
import edu.baylor.cs.se.hibernate.services.IssueService;
import edu.baylor.cs.se.hibernate.utils.Encryption;
import org.jasypt.util.password.BasicPasswordEncryptor;
import org.jasypt.util.text.BasicTextEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class IssueDaoTests {

    @Autowired
    IssueService issueService;


    @Test
    public void checkTest(){

        String pwd = "Company123222";
        String encrypted = Encryption.encrypt(pwd);
        String decrypted = Encryption.decrypt(encrypted);
        System.out.println(decrypted);
    }
}
