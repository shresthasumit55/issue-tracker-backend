package edu.baylor.cs.se.hibernate.services;

import edu.baylor.cs.se.hibernate.dao.HeroDao;
import edu.baylor.cs.se.hibernate.model.ReferenceHero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class HeroService {


    @Autowired
    HeroDao heroDao;

    public ReferenceHero getHeroById(Long id){
        return heroDao.getHeroById(id);
    }

    public List<ReferenceHero> getAllHeroes(){
        return heroDao.getAllHeroes();
    }

    public List<ReferenceHero> getHeroesByCondition(String name, String asc){
        return null;
    }

    public void save(ReferenceHero room) {
        heroDao.save(room);
    }

    public void delete(Long id){
        heroDao.delete(id);
    }

    public void update(ReferenceHero room) {
        heroDao.update(room);
    }

    public void populate(){
        ReferenceHero hero1 = new ReferenceHero("Harry", "Asian", 21.44, true);
        heroDao.save(hero1);
        hero1 = new ReferenceHero("Jery", "Caucasian", 12.44, true);
        heroDao.save(hero1);
        hero1 = new ReferenceHero("Jesse", "Asian", 15.44, true);
        heroDao.save(hero1);
        hero1 = new ReferenceHero("Rob", "Indian", 17.44, true);
        heroDao.save(hero1);
        hero1 = new ReferenceHero("Roy", "Asian", 8.44, true);
        heroDao.save(hero1);

        hero1 = new ReferenceHero("Hwarry", "Indian", 21.44, false);
        heroDao.save(hero1);
        hero1 = new ReferenceHero("Jwery", "Asian", 12.44, false);
        heroDao.save(hero1);
        hero1 = new ReferenceHero("Jwesse", "Caucasian", 15.44, false);
        heroDao.save(hero1);
        hero1 = new ReferenceHero("Toby", "Asian", 17.44, false);
        heroDao.save(hero1);
        hero1 = new ReferenceHero("Kev", "Asian", 8.44, false);
        heroDao.save(hero1);

    }

    public ReferenceHero fight(ReferenceHero hero1, ReferenceHero hero2){

        if (hero1.isDarkSide() && hero2.isDarkSide()){
            return null;
        }


        if (hero1.getStrength()>hero2.getStrength()){
            delete(hero2.getId());
            return hero1;
        }
        else if (hero2.getStrength()>hero1.getStrength()){
            delete(hero1.getId());
            return hero2;
        }
        else{
            delete(hero1.getId());
            delete(hero2.getId());
            return null;
        }

        }

}
