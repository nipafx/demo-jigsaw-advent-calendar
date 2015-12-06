> :arrow_upper_right: This README only covers one section of the demo.
> The [master branch](https://github.com/CodeFX-org/demo-jigsaw-advent-calendar/tree/master) contains more information.

# :zero: Before Jigsaw

The initial state of the project is by no means the best of what was possible before Jigsaw. Quite the contrary, it is a simplistic starting point.

It consists of a single module<sup>1</sup> that contains all required types:

* "Calendar API": `Calendar` and `CalendarSheet` to create the calendar
* "Surprise API": `Surprise` and `SurpriseFactory` (both interfaces)
* Surprises: a couple of surprise factories in the package 'surprises'
* `Main` to wire up and run the whole thing.

Compiling and running is straight forward (see `compileAndRun.sh` for details):

```bash
# compile:
javac -d classes/org.codefx.demo.advent ${list of source files}
# package:
jar -cfm jars/org.codefx.demo.advent.jar ${manifest file and compiled class files}
# run:
java -jar jars/org.codefx.demo.advent.jar
```

<sup>1</sup> in the abstract sense, not the Jigsaw interpretation
