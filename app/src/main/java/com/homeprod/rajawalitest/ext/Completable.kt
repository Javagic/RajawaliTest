/*
 Created by Ilya Reznik
 reznikid@altarix.ru
 skype be3bapuahta
 on 23.08.18 20:14
 */
package com.homeprod.rajawalitest.ext

import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun Completable.subscribeOnIo(): Completable = subscribeOn(Schedulers.io())
fun Completable.subscribeOnMain(): Completable = subscribeOn(AndroidSchedulers.mainThread())

fun Completable.observeOnMainThread(): Completable = observeOn(AndroidSchedulers.mainThread())

fun Completable.observeOnIo(): Completable = observeOn(Schedulers.io())

fun doInBackground(observable: Completable): Completable {
  return observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}