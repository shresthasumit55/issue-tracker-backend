package edu.baylor.cs.se.hibernate.dao;

import edu.baylor.cs.se.hibernate.model.Hero;
import edu.baylor.cs.se.hibernate.model.Room;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class HeroDao {

    @PersistenceContext
    private EntityManager em;

    public Hero getHeroById(Long id){
        return (Hero) em.createQuery("SELECT c FROM Hero c WHERE c.id=:id").setParameter("id",id).getSingleResult();
    }

    public List<Hero> getAllHeroes(){
        return (List<Hero>) em.createQuery("SELECT c FROM Hero c").getResultList();
    }

    public void delete(Long id){
        Hero hero = getHeroById(id);
        em.remove(hero);

    }

    public void save(Hero hero) {
        em.persist(hero);
    }

    public void update(Hero hero) {
        em.merge(hero);
    }


}
