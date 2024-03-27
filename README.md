<h1 align="center">Sunset Hadith - حديث الغروب</h1>

<p align="center">
  <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
  <a href="https://android-arsenal.com/api?level=23"><img alt="API" src="https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat"/></a>
  <a href="https://kotlinlang.org"><img alt="Kotlin" src="https://img.shields.io/badge/Kotlin-1.4.xxx-blue"/></a>
  <img alt="MVVM" src="https://img.shields.io/badge/MVVM-Architecture-orange"/>
</p>

![](https://i.ibb.co/hs8Z57C/Purple-Modern-App-Instagram-Ad.png)
<br />

## Overview 🕌
An interactive islamic app that lets users discover Biography of the Prophet Muhammed, Watch videos, set wallpaper Images, download and reading books.
- Ability to read articles and brochures.
- Ability to copy and share hadiths with others.
- Watch and view Islamic videos through Youtube player API.
- The ability to search in the hadiths of the Prophet.
- Download images and set them as wallpapers for your device, whether for the home screen or lock screen through the application
- A selection of books with the ability to read directly and download them
  <br />

<a href='https://play.google.com/store/apps/details?id=com.moataz.afternoonhadeeth&hl=ar&gl=US'><img alt='Get it on Google Play' src='https://play.google.com/intl/en_us/badges/images/generic/en_badge_web_generic.png' width="170px"/></a>
<br />

## App Images 🌹
Home | Videos | Hadiths 
--- | --- | --- | 
![](https://i.ibb.co/J7Dgpy4/homePage.jpg) | ![](https://i.ibb.co/pWxsw3h/videos.jpg) | ![](https://i.ibb.co/WzkKR7N/hadith.jpg) | 

| Wallpapers | Books | DisplayBook (Scrolled)
--- | --- | --- |
![](https://i.ibb.co/ChFc9jH/wallpapers.jpg) | ![](https://i.ibb.co/FbtQ69F/books.jpg) | ![](https://i.ibb.co/khq2hfK/displaybook.jpg)
<br />


## Project Architecture MVVM
![MVVM3](https://user-images.githubusercontent.com/1812129/68319232-446cf900-00be-11ea-92cf-cad817b2af2c.png)
- Yes , liveData is easy , powerful , but you should know how to use.
- For livedate which will emit data stream , it has to be in your
  data layer , and don't inform those observables any thing else like
  in which thread those will consume , cause it is another
- For livedata which will emit UI binding events, it has to be in your ViewModel Layer.
- Observers in UI Consume and react to live data values and bind it.
  responsibility , and according to `Single responsibility principle`
  in `SOLID (object-oriented design)` , so don't break this concept by
  mixing the responsibilities .

![mvvm2](https://user-images.githubusercontent.com/1812129/68319008-e9d39d00-00bd-11ea-9245-ebedd2a2c067.png)
<br />

## Built With 🛠
- [Kotlin](https://kotlinlang.org/) - First class and official programming language for Android development.
- [Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) - Asynchronous or non-blocking programming is an important part of the development landscape.
- [Flow](https://kotlinlang.org/docs/flow.html) - A suspending function asynchronously returns a single value, but how can we return multiple asynchronously computed values? This is where Kotlin Flows come in.
- [Ktor](https://ktor.io/) - Create asynchronous client and server applications. Anything from microservices to multiplatform HTTP client apps in a simple way. Open Source, free, and fun!.
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps.
- [Jetpack Navigation](https://developer.android.com/jetpack/compose/navigation) - Navigation refers to the interactions that allow users to navigate across, into, and back out from the different pieces of content within your app
- [Material Components for Android](https://github.com/material-components/material-components-android) - Modular and customizable Material Design UI components for Android.
  <br />