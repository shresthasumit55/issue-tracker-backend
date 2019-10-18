package edu.baylor.cs.se.hibernate;

import edu.baylor.cs.se.hibernate.dao.HeroDao;
import edu.baylor.cs.se.hibernate.model.Hero;
import edu.baylor.cs.se.hibernate.model.Room;
import edu.baylor.cs.se.hibernate.model.Teacher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ExampleTest {

    @Autowired
    private TestEntityManager entityManager;

    @Test
    //simple test
    public void testUniqueName(){
        Hero hero = new Hero("Harry", "Asian", 21.44, true);
        entityManager.persist(hero);
        Hero hero2 = new Hero("Harry", "Asian", 21.44, true);

        assertThatThrownBy(() -> { entityManager.persist(hero2); }).isInstanceOf(PersistenceException.class);
    }
}