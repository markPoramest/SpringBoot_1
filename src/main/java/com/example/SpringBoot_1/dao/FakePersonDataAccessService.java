package com.example.SpringBoot_1.dao;

import com.example.SpringBoot_1.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao")
public class FakePersonDataAccessService implements  PersonDao {
    private  static List<Person> DB = new ArrayList<>();
    @Override
    public int insertPerson(UUID id, Person person) {
        DB.add(new Person(id,person.getName()));
        return 1;
    }

    @Override
    public List<Person> selectAllPeople() {
        return DB;
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        return DB.stream().filter(person -> person.getId().equals(id)).findFirst();
    }

    @Override
    public int deletePersonById(UUID id) {
        Optional<Person> person1 = selectPersonById(id);
        if(person1.isPresent()) {
            DB.remove(person1.get());
            return 1;
        }
        return 0;
    }

    @Override
    public int updatePersonById(UUID id, Person update) {
        return  selectPersonById(id)
                .map(person -> {int indexofPerson = DB.indexOf(person);
        if (indexofPerson >=0){
         DB.set(indexofPerson,new Person(id,update.getName()));
        return 1;}
        return 0;}).orElse(0);
    }
}
