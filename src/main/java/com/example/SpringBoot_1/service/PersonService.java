package com.example.SpringBoot_1.service;

import com.example.SpringBoot_1.dao.PersonDao;
import com.example.SpringBoot_1.model.Person;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    private  final PersonDao personDao;

    public PersonService(@Qualifier("fakeDao") PersonDao personDao) {
        this.personDao = personDao;
    }

    public int addPerson(Person person){
        return personDao.insertPerson(person);
    }
}
