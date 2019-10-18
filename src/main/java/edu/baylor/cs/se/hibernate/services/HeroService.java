package edu.baylor.cs.se.hibernate.services;

import edu.baylor.cs.se.hibernate.dao.HeroDao;
import edu.baylor.cs.se.hibernate.model.Hero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class HeroService {


    @Autowired
    HeroDao heroDao;

    public Hero getHeroById(Long id){
        return heroDao.getHeroById(id);
    }

    public List<Hero> getAllHeroes(){
        return heroDao.getAllHeroes();
    }

    public List<Hero> getHeroesByCondition(String name, String asc){
        return null;
    }

    public void save(Hero room) {
        heroDao.save(room);
    }

    public void delete(Long id){
        heroDao.delete(id);
    }

    public void update(Hero room) {
        heroDao.update(room);
    }

    public void populate(){
        Hero hero1 = new Hero("Harry", "Asian", 21.44, true);
        heroDao.save(hero1);
        hero1 = new Hero("Jery", "Caucasian", 12.44, true);
        heroDao.save(hero1);
        hero1 = new Hero("Jesse", "Asian", 15.44, true);
        heroDao.save(hero1);
        hero1 = new Hero("Rob", "Indian", 17.44, true);
        heroDao.save(hero1);
        hero1 = new Hero("Roy", "Asian", 8.44, true);
        heroDao.save(hero1);

        hero1 = new Hero("Hwarry", "Indian", 21.44, false);
        heroDao.save(hero1);
        hero1 = new Hero("Jwery", "Asian", 12.44, false);
        heroDao.save(hero1);
        hero1 = new Hero("Jwesse", "Caucasian", 15.44, false);
        heroDao.save(hero1);
        hero1 = new Hero("Toby", "Asian", 17.44, false);
        heroDao.save(hero1);
        hero1 = new Hero("Kev", "Asian", 8.44, false);
        heroDao.save(hero1);

    }

    public Hero fight(Hero hero1, Hero hero2){

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
