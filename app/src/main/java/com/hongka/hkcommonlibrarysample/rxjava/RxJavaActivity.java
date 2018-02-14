package com.hongka.hkcommonlibrarysample.rxjava;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.ArraySet;
import android.view.MenuItem;

import com.hongka.hkcommonlibrarysample.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Single;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.AsyncSubject;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.ReplaySubject;

/**
 * Created by jusung.kim@sk.com on 2018/02/08
 */

public class RxJavaActivity extends AppCompatActivity {

    public static Intent makeIntent(Context context) {
        Intent intent = new Intent(context, RxJavaActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);

//        emit();
//        fromObserableToSingle();
        subject();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home :
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void subject() {
//        AsyncSubject<String> subject = AsyncSubject.create();
//        subject.subscribe(it -> System.out.println("Subscriber #1 => " + it));
//        subject.onNext("1");
//        subject.onNext("3");
//        subject.subscribe(it -> System.out.println("Subscriber #2 => " + it));
//        subject.onNext("5");
//        subject.onComplete();


//        BehaviorSubject<String> subject = BehaviorSubject.createDefault("6");
//        subject.subscribe(data -> System.out.println("Subscriber #1 => " + data));
//        subject.onNext("1");
//        subject.onNext("3");
//        subject.subscribe(data -> System.out.println("Subscriber #2 => " + data));
//        subject.onNext("5");
//        subject.onComplete();

//        PublishSubject<String> subject = PublishSubject.create();
//        subject.subscribe(data -> System.out.println("Subscribr #1 => " + data));
//        subject.onNext("1");
//        subject.onNext("3");
//        subject.subscribe(data -> System.out.println("Subscribr #2 => " + data));
//        subject.onNext("5");
//        subject.onComplete();

        ReplaySubject<String> subject = ReplaySubject.create();
        subject.subscribe(data -> System.out.println("Subscribr #1 => " + data));
        subject.onNext("1");
        subject.onNext("3");
        subject.subscribe(data -> System.out.println("Subscribr #2 => " + data));
        subject.onNext("5");
        subject.onComplete();
    }

    private void fromObserableToSingle() {
        // 1. 기존 Obserable에서 Single 객체로 반환하기
        Observable<String> source = Observable.just("Hello Single #1");
        Single.fromObservable(source).subscribe(System.out::println);

        // 2. single() 함수를 호출해 Single 객체 생성하기
        Observable.just("Hello Single #2").single("default item").subscribe(System.out::println);

        // 3. first() 함수를 호출해 Single 객체 생성하기.
        String[] colors = {"BLUE", "RED", "GOLD"};
        Observable.fromArray(colors).first("default value").subscribe(System.out::println);

        // 4. empty Obserable에서 Single 객체 생성하기.
        Observable.empty().single("default value").subscribe(System.out::println);

        // 5. take() 함수에서 Single 객체 생성하기
        Observable.just(new Order("ORD-1"), new Order("ORD-2"), new Order("ORD-3")).take(1).single(new Order("default order")).subscribe(System.out::println);
    }
    private void emit() {
//        Observable.just("Hello", "RxJava 2!!").subscribe(System.out::println);
//        Observable.just(1, 2, 3, 4, 5, 6).subscribe(it -> System.out.println(it));
//        Observable.just(1, 2, 3, 4, 5, 6, 7, 8).subscribe(it -> System.out.println(it), err -> System.out.println(err), () -> System.out.println("onComplete"));

//        Observable<Integer> source = Observable.create(
//          it -> {
//              it.onNext(100);
//              it.onNext(200);
//              it.onNext(300);
//              it.onComplete();
//          }
//        );
//        source.subscribe(it->System.out.println(it), err -> System.out.println(err), () -> System.out.println(""));
//        source.subscribe(it->System.out.println("Result : " + it));


//        Integer[] arr = {100, 200, 300};
//        Observable<Integer> source = Observable.fromArray(arr);
//        source.subscribe(System.out::println);


//        List<String> names = new ArrayList<>();
//        names.add("김주성");
//        names.add("우희경");
//        names.add("김도윤");
//        names.add("김도윤");

//        Set<String> names = new HashSet<>();
//        names.add("김주성");
//        names.add("김도윤");
//        names.add("우희경");
//        names.add("김주성");

//        BlockingQueue<String> names = new ArrayBlockingQueue<String>(5);
//        names.add("김도윤");
//        names.add("우희경");
//        names.add("김주성");
//
//        Observable<String> source = Observable.fromIterable(names);
//        source.subscribe(System.out::println);

//        Callable<String> callable = () -> {
//            Thread.sleep(1000);
//            return "Hello Callable";
//        };
//        Observable<String> source = Observable.fromCallable(callable);
//        source.subscribe(System.out::println);

        Future<String> future = Executors.newSingleThreadExecutor().submit(() -> {
            Thread.sleep(5000);
            return "Hello Future";
        });

        Observable<String> source = Observable.fromFuture(future);
        source.subscribe(System.out::println);
    }
}
