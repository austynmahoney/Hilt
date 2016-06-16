Hilt
====

Hilt implements the boilerplate classes that are necessary to provide injection of `Application` and `Activity` `Context`'s and other Android specific objects when using Dagger 1.

##Gradle##
Place `hilt-{version}.jar` into your `appModule/libs` folder.

Update your **build.gradle** to include the following:
```groovy
dependencies {
    compile files('libs/hilt-{version}.jar');
}
```


##Build Status##

[![Build Status](https://travis-ci.org/austynmahoney/Hilt.svg?branch=master)](https://travis-ci.org/austynmahoney/Hilt)
