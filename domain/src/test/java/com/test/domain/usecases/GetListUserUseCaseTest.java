package com.test.domain.usecases;

import com.test.domain.entity.User;
import com.test.domain.repositories.UserRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.TestObserver;
import io.reactivex.schedulers.TestScheduler;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GetListUserUseCaseTest {

    @Mock
    private UserRepository userRepository;

    private GetListUserUseCase listUserUseCase;

    private TestScheduler testScheduler;


    //Запускается перед тестом, типа подгрузить настройки

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);

        testScheduler = new TestScheduler();

        listUserUseCase = new GetListUserUseCase(userRepository, testScheduler, testScheduler);

    }


    @Test
    public void validData() {

        final List<User> listData = new ArrayList<>();
        listData.add(new User());

        when(userRepository.getAll()).thenReturn(Observable.just(listData));

        TestObserver<List<User>> testSubscriber = new TestObserver<>();

        listUserUseCase
                .getUsers()
                .doOnNext(new Consumer<List<User>>() {
                    @Override
                    public void accept(List<User> users) throws Exception {
                        System.out.print(users.size());
                    }
                })
                .subscribe(testSubscriber);

        testScheduler.triggerActions();

        testSubscriber.assertValue(new Predicate<List<User>>() {
            @Override
            public boolean test(List<User> users) throws Exception {
                User user = users.get(0);

                if (user.getFirsname().equals("test")) {
                    return true;
                }
                return false;
            }
        });
    }



    @Test
    public void testError() {

        final List<User> listData = new ArrayList<>();
        listData.add(new User("test", "sur test", 15, Gender.M, "dsadas"));

        when(userRepository.getAll()).thenReturn(Observable.just(listData));

        TestObserver<List<User>> testSubscriber = new TestObserver<>();

        listUserUseCase
                .getUsers()
                .doOnNext(new Consumer<List<User>>() {
                    @Override
                    public void accept(List<User> users) throws Exception {
                        System.out.print(users.size());
                    }
                })
                .subscribe(testSubscriber);

        testScheduler.triggerActions();

        testSubscriber.assertValue(new Predicate<List<User>>() {
            @Override
            public boolean test(List<User> users) throws Exception {
                User user = users.get(0);

                if (user.getFirsname().equals("test5")) {
                    return true;
                }
                return false;
            }
        });
    }


}
