/*
 Created by Ilya Reznik
 reznikid@altarix.ru
 skype be3bapuahta
 on 23.08.18 20:36
 */
package com.homeprod.rajawalitest.ext

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Single<T>.subscribeOnIo(): Single<T> = subscribeOn(Schedulers.io())
fun <T> Single<T>.subscribeOnMain(): Single<T> = subscribeOn(AndroidSchedulers.mainThread())

fun <T> Single<T>.observeOnMainThread(): Single<T> = observeOn(AndroidSchedulers.mainThread())

fun <T> Single<T>.observeOnIo(): Single<T> = observeOn(Schedulers.io())

fun <T> doInBackground(observable: Single<T>): Single<T> {
  return observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}