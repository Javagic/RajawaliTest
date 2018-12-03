/*
 Created by Ilya Reznik
 reznikid@altarix.ru
 skype be3bapuahta
 on 23.08.18 20:36
 */
package com.homeprod.rajawalitest.ext

import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Flowable<T>.subscribeOnIo(): Flowable<T> = subscribeOn(Schedulers.io())
fun <T> Flowable<T>.subscribeOnMain(): Flowable<T> = subscribeOn(AndroidSchedulers.mainThread())

fun <T> Flowable<T>.observeOnMainThread(): Flowable<T> = observeOn(AndroidSchedulers.mainThread())

fun <T> Flowable<T>.observeOnIo(): Flowable<T> = observeOn(Schedulers.io())

fun <T> doInBackground(observable: Flowable<T>): Flowable<T> {
  return observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}