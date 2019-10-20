package edu.baylor.cs.se.hibernate.dao;

import edu.baylor.cs.se.hibernate.model.ReferenceHero;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class HeroDao {

    @PersistenceContext
    private EntityManager em;

    public ReferenceHero getHeroById(Long id){
        return (ReferenceHero) em.createQuery("SELECT c FROM ReferenceHero c WHERE c.id=:id").setParameter("id",id).getSingleResult();
    }

    public List<ReferenceHero> getAllHeroes(){
        return (List<ReferenceHero>) em.createQuery("SELECT c FROM ReferenceHero c").getResultList();
    }

    public void delete(Long id){
        ReferenceHero hero = getHeroById(id);
        em.remove(hero);

    }

    public void save(ReferenceHero hero) {
        em.persist(hero);
    }

    public void update(ReferenceHero hero) {
        em.merge(hero);
    }


}
