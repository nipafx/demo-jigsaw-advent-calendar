> :arrow_upper_right: This README only covers one section of the demo.
> The [master branch](https://github.com/CodeFX-org/demo-jigsaw-advent-calendar/tree/master) contains more information.

# :zero: Before Jigsaw

The initial state of the project is by no means the best of what was possible before Jigsaw. Quite the contrary, it is a simplistic starting point.

It consists of a single module<sup>1</sup> that contains all required types:

* "Calendar API": `Calendar` and `CalendarSheet` to create the calendar
* "Surprise API": `Surprise` and `SurpriseFactory` (both interfaces)
* Surprises: a couple of surprise factories in the package 'surprises'
* `Main` to wire up and run the whole thing.

The directory structure is a little unusual:

```
src
  - org.codefx.demo.advent: the application itself
      - org: the packages ...
       - ... and eventually the source files
      manifest.mf
.gitignore
compileAndRun.sh
LICENSE
README
```

This is in preparation for the upcoming move to Jigsaw and uses the same directory structure as the [official quick start guide](http://openjdk.java.net/projects/jigsaw/quick-start).

Compiling and running is straight forward (see `compileAndRun.sh` for details; the script uses Java 9's new long form options):

```bash
# compile:
javac -d classes/org.codefx.demo.advent
    ${list of source files}
# package:
jar --create
    --file jars/org.codefx.demo.advent.jar
    --manifest ${manifest file}
    ${compiled class files}
# run:
java -jar jars/org.codefx.demo.advent.jar
```

<sup>1</sup> in the abstract sense, not the Jigsaw interpretation
