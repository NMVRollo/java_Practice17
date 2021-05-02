package com.example.Practice17.service;

import com.example.Practice17.models.Dog;
import com.example.Practice17.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class DogService {

    @Autowired
    private SessionFactory sessionFactory;
    private Session session;

    @PostConstruct
    public void init() {
        session = sessionFactory.openSession();
    }

    public User getUserByDog(Long dogId) {
        return session.createQuery("from Dog where id =:id", Dog.class)
                .setParameter("id", dogId).getSingleResult().getUser();
    }

    public List<Dog> filteredDogList(String field, String value) {
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Dog> query = criteriaBuilder.createQuery(Dog.class);
        Root<Dog> from = query.from(Dog.class);
        query.select(from).where(from.get(field).in(value));

        return session.createQuery(query).getResultList();
    }

    public List<Dog> getAllDogs() {
        return session.createQuery("from Dog", Dog.class).list();
    }
}
