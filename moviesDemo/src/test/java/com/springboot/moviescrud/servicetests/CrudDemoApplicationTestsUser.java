package com.springboot.moviescrud.servicetests;

import com.springboot.moviescrud.dao.UserRepository;
import com.springboot.moviescrud.entity.Authority;
import com.springboot.moviescrud.entity.Review;
import com.springboot.moviescrud.entity.User;
import com.springboot.moviescrud.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CrudDemoApplicationTestsUser {
    int id=0;
    static List<Review> reviews = CrudDemoApplicationTestsUser.getReviews();
    static Authority authority = new Authority(1);


    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void findAllTest() {
        when(userRepository.findAll())
                .thenReturn(Stream.of(
                        new User(1,"karthik",
                                "password123",1,reviews,authority),
                        new User(2,"sujay",
                                "password123",1,reviews,authority)).
                        collect(Collectors.toList()));

        assertEquals(2, userService.findAll().size());
    }

    @Test
    public void findByIdTest() {
        int id = 1;
       User user =  new User(1,"karthik",
                "password123",1,reviews,authority);
        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        assertEquals(user, userService.findById(1));

    }

    @Test
    public void save()
    {
        User user =  new User(1,"karthik",
                "password123",1,reviews,authority);
        userService.save(user);
        verify(userRepository,times(1)).save(user);
    }

    @Test
    public void deleteByIdTest(){
        User user =  new User(1,"karthik",
                "password123",1,reviews,authority);
        userService.deleteById(1);
        verify(userRepository,times(1)).deleteById(1);
    }

    /*@Test
    public void findUsernameTest(){
        User user =  new User(1,"karthik",
                "password123",1,reviews,authority);
        String username="karthik";

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
        assertEquals(user, userService.findByUsername((username)));


    }*/

    static private List<Review> getReviews()
    {
        Review review1 = new Review("amazing movie");
        Review review2 = new Review("wonderful movie");
        List<Review> reviews = new ArrayList<>();
        reviews.add(review1);
        reviews.add(review2);
        return reviews;
    }

}
