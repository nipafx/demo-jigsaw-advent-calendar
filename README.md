# Jigsaw Advent Calendar :christmas_tree:

This demo project contains the code for [a post for the _Java Advent Calendar_](http://www.javaadvent.com/2015/12/10) (which I published in [on my blog](http://blog.codefx.org/java/dev/jigsaw-hands-on-guide/) as well).
It showcases a couple of features of [Project Jigsaw](http://blog.codefx.org/tag/project-jigsaw/).

The code is released into the public domain via [CC0](https://creativecommons.org/publicdomain/zero/1.0/) so it can be used without any limitations.



## The Advent Calendar

Even though I do my best to ignore the whole christmas kerfuffle, it seemed prudent to have the demo uphold "the spirit of the season".
So it models an advent calendar:

* There is a `Calendar`, which has 24 `CalendarSheet`s.
* Each sheet knows its day of the month and contains a `Surprise`.
* The death march towards Christmas is symbolized by printing the sheets (and thus the surprises) to the console.

Of course the calendar needs to be created first.
It can do that by itself but it needs the means to create surprises.
To this end it requires a `List<SurpriseFactory>`.



## Sections

The demo consists of several sections, each building on the previous ones.
There is a branch for each section.

* :zero: [Before Jigsaw](https://github.com/CodeFX-org/demo-jigsaw-advent-calendar/tree/00-before-jigsaw):
describes the application's organization before Jigsaw
* :one: [Creating A Module](https://github.com/CodeFX-org/demo-jigsaw-advent-calendar/tree/01-creating-a-module):
moves the application into Jigsaw land by moving it into a single module
* :two: [Splitting Into Modules](https://github.com/CodeFX-org/demo-jigsaw-advent-calendar/tree/02-splitting-into-modules):
explores more of Jigsaw's core features by splitting the application into several modules
* :three: [Services](https://github.com/CodeFX-org/demo-jigsaw-advent-calendar/tree/03-services):
introduces services and the `ServiceLocator` for loose coupling between modules
* :four: [Automatic Modules](https://github.com/CodeFX-org/demo-jigsaw-advent-calendar/tree/04-automatic-modules):
shows how modules can depend on non-modularized JARs
* :five: [Optional Dependencies](https://github.com/CodeFX-org/demo-jigsaw-advent-calendar/tree/05-optional-dependency):
demonstrates dependencies that are mandatory at compile time but are not required at run time



## Setup

This demo was developed against build 138 of the [Jigsaw early access prototype](https://jdk9.java.net/jigsaw/).
For it to work the environment variable `JIGSAW_BIN` has to point to the `bin` directory in such a Java 9 install, e.g.:

```
export JIGSAW_HOME=/opt/java/jdk9
export JIGSAW_BIN=/opt/java/jdk9/bin
```

The root directory contains a `compileAndRun.sh`, which - surprise - compiles and runs the example.
