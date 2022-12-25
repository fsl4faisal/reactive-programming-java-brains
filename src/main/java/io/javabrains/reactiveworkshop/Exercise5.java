package io.javabrains.reactiveworkshop;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;

import java.io.IOException;

public class Exercise5 {

    public static void main(String[] args) throws IOException {

        // Use ReactiveSources.intNumberMono() and ReactiveSources.userMono()

        // Subscribe to a flux using the error and completion hooks
        ReactiveSources.intNumbersFlux().subscribe(
                System.out::println,
                (e) -> System.out.println(e.getMessage()),
                () -> System.out.println("Complete.!"));


        // Subscribe to a flux using an implementation of BaseSubscriber
        var customBaseSubscriber = new CustomBaseSubscriber<>();
        ReactiveSources.intNumbersFlux().subscribe(customBaseSubscriber);

        System.out.println("Press a key to end");
        System.in.read();
    }

}

class CustomBaseSubscriber<T> extends BaseSubscriber<T> {
    protected void hookOnSubscribe(Subscription subscription) {
        System.out.println("Subcribed.!");
        request(1);
    }

    protected void hookOnNext(T value) {
        System.out.println("Processing :" + value.toString());
        request(1);
    }
}