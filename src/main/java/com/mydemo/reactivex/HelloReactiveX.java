package com.mydemo.reactivex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observables.ConnectableObservable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HelloReactiveX {
	public static void main(String[] args) throws Exception {
//		basicExample1();
//		basicExample2();
//		System.out.println(Runtime.getRuntime().availableProcessors());
//		basicExample3();
//		doAsyncOp();
//		hotObservableExample();
//		schedulerExample();
		backpressureDemo();
	}

	private static void backpressureDemo() throws InterruptedException {
		Flowable.range(1,500)
		.doOnNext(num->System.out.println("Producing: "+num))
		.observeOn(Schedulers.io())
		.subscribe(new Subscriber<Integer>() {

			Subscription s;
			AtomicInteger count = new AtomicInteger(0);
			final int INCREMENT_VAL=20;
			@Override
			public void onSubscribe(Subscription s) {
				this.s=s;
				System.out.println("Requesting next 20: "+INCREMENT_VAL);
				s.request(INCREMENT_VAL);
			}

			@Override
			public void onNext(Integer t) {
				System.out.println("Consuming: "+t);
				if(count.getAndIncrement()%20==0) {
					System.out.println("Requesting next 20: "+count);
					s.request(INCREMENT_VAL);
				}
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onError(Throwable t) {
				System.out.println("Error:"+t);
			}

			@Override
			public void onComplete() {
				System.out.println("Completed");
			}
		});
		
		/*.subscribe(num->{
			System.out.println("Consuming: "+num);
			}
		);*/
		
		Thread.sleep(8000);
	}

	static void basicExample1(){
		Observable<String> source = Observable.create(r->{
			r.onNext("Hello");
			r.onNext("ReactiveX");
		});
		source.subscribe(r->System.out.println("Observer1:"+r));
		source.subscribe(r->System.out.println("Observer2:"+r));
	}
	
	// Test this using debugging to get idea of flow
	static void basicExample2(){
		Observable<String> source = Observable.create(new ObservableOnSubscribe<String>() {
			@Override
			public void subscribe(@NonNull ObservableEmitter<@NonNull String> emitter) throws Throwable {
				emitter.onNext("This is emitted by observable");
				emitter.onError(new Throwable("Some error occured"));
				emitter.onComplete();//unreachable
			}
		});
		source.subscribe(new Observer<Object>() {
			
			@Override
			public void onSubscribe(@NonNull Disposable d) {
				System.out.println("Subscribed to observable");
			}

			@Override
			public void onNext(@NonNull Object t) {
				System.out.println("onNext: "+t);
			}

			@Override
			public void onError(@NonNull Throwable e) {
				System.out.println("onError: " + e);
			}

			@Override
			public void onComplete() {
				System.out.println("onComplete");
			}
		});
	}
	
	static void basicExample3(){
		List<String> books = new ArrayList<String>();
//		Collections.unmodifiableList can't be used here.
//		List<String> books = Collections.unmodifiableList(booksTemp);

//		List returned by Arrays.asList() also can't be used directly since it creates a fixed size array, so no new elements can be added as 
//		required for this example.

		books.addAll(Arrays.asList("3 mistakes of my life", "2 States", "1 Night @ the call center", "Half Girlfriend"));
		Collections.reverse(books);
		Observable<String> source = Observable.fromIterable(books);
		source.subscribe(System.out::println);
	
		books.add("Five point someone");
		System.out.println("***");
		source.subscribe(System.out::println);
	}
	
	static void schedulerExample() throws InterruptedException {
		// All observable code runs on scheduler/thread type specified in subscribeOn(), regardless of placement of subscribeOn() in the chain.
		// But in case of multiple subscribeOn() calls, the one closest to the observable in the pipeline would be considered.
		// For this example, the io scheduler would be used by the JVM.
		Disposable disposable = Observable.just("One", "Two","Three", "Four", "Five") 
		.doOnComplete(()->System.out.println("-------Emissions complete."))
		.map(str->str+"_REACTIVE")
		.doOnNext(e->System.out.println("("+Thread.currentThread().getName()+"): Emitting: "+e))// Emits the string+<_REACTIVE>
		.subscribeOn(Schedulers.io())
		
		.map(str->str.hashCode())
		.doOnNext(e->System.out.println("("+Thread.currentThread().getName()+"): Emitting: "+e))// Emits the hashCode
		.subscribeOn(Schedulers.newThread())
		
		// Observer code runs on scheduler/thread type specified in observeOn()
		// Of these 2 observeOn() calls, the single thread backed scheduler would be used to schedule the observer task, since the 
		// observeOn(Schedulers.single()) is closest to the observer.
		.observeOn(Schedulers.computation())
		.observeOn(Schedulers.single())

		.subscribe(e->System.out.println("("+Thread.currentThread().getName()+"): Receiving: "+e),
				e->{},
				()->System.out.println("-------Reciepts completed"));

		System.out.println("subscriber is disposed?:"+disposable.isDisposed());
		
		Thread.sleep(6000);
		System.out.println("subscriber is disposed?:"+disposable.isDisposed());
	}
	
	static void doAsyncOp(){
		Observable.just("long", "longer", "longest")
        .flatMap(v ->
        	performLongOperation(v)
            .doOnNext(s -> System.out.println("processing item on thread " + Thread.currentThread().getName()))
            .subscribeOn(Schedulers.newThread()))
        .subscribe(length -> System.out.println("received item length " + length + " on thread " + Thread.currentThread().getName()));
	}
	
	protected static Observable<Integer> performLongOperation(String v) {
	    Random random = new Random();
	    try {
	        Thread.sleep(random.nextInt(3) * 1000);
	        return Observable.just(v.length());
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
	    return null;
	}	
	
	static void hotObservableExample() throws InterruptedException {
		ConnectableObservable<Integer> observable = Observable.just("One", "Two","Three", "Four", "Five")
		
		.doOnComplete(()->System.out.println("-------Emissions complete."))
		.map(str->str+"_REACTIVE")
		.doOnNext(e->System.out.println("("+Thread.currentThread().getName()+"): Emitting: "+e))// Emits the string+<_REACTIVE>
		.subscribeOn(Schedulers.io())
		
		.map(str->str.hashCode())
		.doOnNext(e->System.out.println("("+Thread.currentThread().getName()+"): Emitting: "+e))// Emits the hashCode
		.subscribeOn(Schedulers.newThread())
		.publish(); // Converts an Observable to ConnectableObservable
		observable.connect(); // Starts emitting the values to 0 or more Observers.
		
		// Comment this and see the Observable side of the messages (onNext and onComplete) still getting emitted.
		// That's why its called hot observable (one that emits values even if no subscriber exists)
		observable.subscribe(e->System.out.println("("+Thread.currentThread().getName()+"): Receiving: "+e),
				e->{},
				()->System.out.println("-------Reciepts completed"));

		Thread.sleep(6000);
	}
}

