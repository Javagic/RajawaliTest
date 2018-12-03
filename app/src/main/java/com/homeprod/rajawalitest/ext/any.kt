/*
 Created by Ilya Reznik
 reznikid@altarix.ru
 skype be3bapuahta
 on 21.08.18 21:58
 */
package com.homeprod.rajawalitest.ext

import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.Single



fun <I : Any, R : Any> Observable<I>.concatMappers(vararg mappers: (I) -> ObservableSource<out R>): Observable<R> = concatMap { item -> Observable.concat(mappers.map { it(item) }) }

fun <I : Any, R : Any> Observable<I>.flatMappers(vararg mappers: (I) -> ObservableSource<R>): Observable<R> = concatMap { item -> Observable.merge(mappers.map { it(item) }) }

fun < T : Any> T.rxObservable(): Observable<T> = Observable.just(this)

fun <T : Any> T.rxCallableObservable(): Observable<T> = Observable.fromCallable { this }

fun <T : Any> T.rxSingle(): Single<T> = Single.just(this)

fun <T : Any> T.rxFlowable(): Flowable<T> = Flowable.just(this)

fun <T : Any> Throwable.rxObservableError() = Observable.error<T>(this)

fun <T : Any> Throwable.rxFlowableError() = Flowable.error<T>(this)

fun <T : Any> Throwable.rxSingleError() = Single.error<T>(this)