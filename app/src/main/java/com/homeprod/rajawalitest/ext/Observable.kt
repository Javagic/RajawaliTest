/*
 Created by Ilya Reznik
 reznikid@altarix.ru
 skype be3bapuahta
 on 20.08.18 22:06
 */
package com.homeprod.rajawalitest.ext

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


fun <T> Observable<T>.subscribeOnIo(): Observable<T> = subscribeOn(Schedulers.io())
fun <T> Observable<T>.subscribeOnMain(): Observable<T> = subscribeOn(AndroidSchedulers.mainThread())

fun <T> Observable<T>.observeOnMainThread(): Observable<T> = observeOn(AndroidSchedulers.mainThread())

fun <T> Observable<T>.observeOnIo(): Observable<T> = observeOn(Schedulers.io())

fun <T> doInBackground(observable: Observable<T>): Observable<T> {
  return observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}