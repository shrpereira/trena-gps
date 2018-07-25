# Trena GPS 
[![Build Status](https://travis-ci.org/shrpereira/trena-gps-app.svg?branch=master)](https://travis-ci.org/shrpereira/trena-gps-app)

This app is an update of an old project I've done several years ago and since I don't have the 
source code anymore, I just choose to rewrite everything using new technologies and patterns.

The app offers to users the possibility to take distance measures in straight line using their
smartphones and save these data for later usage. It also, allows users to delete the measurements
simply sliding the list items to any side.

## Technologies
For this project, I'm using some libraries listed below:
* com.ninenine.reactivelocation:reactive-location
* android.arch.lifecycle:extensions
* org.koin:koin-android
* org.koin:koin-android-architecture
* uk.co.chrisjenx:calligraphy
* com.github.pedrovgs:renderers
* com.google.code.gson:gson
* org.jetbrains.anko:anko-commons 

## Architecture

The app was created using MVVM architecture with the help of Google Architecture Components such as
ViewModel and LiveData following the Reactive and Repository Patterns.

The dependency injection was achieved with the help of Koin library.