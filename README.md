> :arrow_upper_right: This README only covers one section of the demo.
> The [master branch](https://github.com/CodeFX-org/demo-jigsaw-advent-calendar/tree/master) contains more information.

# :two: Splitting Into Modules

Now it's time to really get to know Jigsaw and split that monolith up into separate modules.

## Rationale

The "surprise API", i.e. `Surprise` and `SurpriseFactory`, is a great success and we want to separate it from the monolith.

Then there are the factories that create the surprises, which turn out to be very dynamic.
A lot of work is being done here, they change frequently and which factories are used changes from release to release.
So we want to isolate them.

At the same time we plan to create a large Christmas application of which the calendar is only one part. So we'd like to have a separate module for that as well.

We end up with these modules:

* _surprise_ - `Surprise` and `SurpriseFactory`
* _calendar_ - the calendar, which uses the surprise API
* _factories_ - the `SurpriseFactory` implementations
* _main_ - the original application, now hollowed out to the `Main` class

Looking at their dependencies we see that _surprise_ depends on no other module of ours.
Both _calendar_ and _factories_ make use of its types so they must depend on it.
Finally, _main_ uses the factories to create the calendar so it depends on both.

<img src="http://yuml.me/cd7e6a17.png"></img>
<!-- // http://yuml.me/edit/cd7e6a17
[surprise{bg:green}]
[factories{bg:yellow}]->[surprise]
[calendar{bg:yellow}]->[surprise]
[main{bg:red}]->[factories]
[main]->[calendar]
-->

## Implementation

The first step is to reorganize the source code:

```
src
  - org.codefx.demo.advent.calendar: the "calendar" module
      - org ...
      module-info.java
  - org.codefx.demo.advent.factories: the "factories" module
      - org ...
      module-info.java
  - org.codefx.demo.advent.surprise: the "surprise" module
      - org ...
      module-info.java
  - org.codefx.demo.advent: the "main" module
      - org ...
      module-info.java
.gitignore
compileAndRun.sh
LICENSE
README
```

This is the same directory structure as used by the [official quick start guide](http://openjdk.java.net/projects/jigsaw/quick-start). The depcition is not complete, though, and does not contain the folders below `org`, which are the individual packages and eventually the source files.

### _surprise_

Let's start with _surprise_.

There are no `required` clauses as it has no dependencies.
(Except for `java.base`, which is always implicitly required.)
It exports the package `org.codefx.demo.advent.surprise` because that contains the two classes `Surprise` and `SurpriseFactory`.

So the `module-info.java` looks as follows:

```java
module org.codefx.demo.advent.surprise {
	// requires no other modules
	// publicly accessible packages
	exports org.codefx.demo.advent.surprise;
}
```

Compiling and packaging is very similar to the previous section.
It is in fact even easier because _surprise_ contains no main class:

```bash
# compile
javac -d classes/org.codefx.demo.advent.surprise ${list of source files}
# package
jar -c --file=mods/org.codefx.demo.advent.surprise.jar ${compiled class files}
```

### _calendar_

The calendar has fields and parameters with types from the surprise API so the module must depend on _surprise_.
Adding `requires org.codefx.demo.advent.surprise` to the module achieves this.

But there is an additional twist:
The publicly accessible method `Calendar::createWithSurprises` declares a parameter of type `List<SurpriseFactory>`.
So all modules using this API must also read _surprise_.
Otherwise Jigsaw would prevent them from accessing these types, which would lead to compile and runtime errors.
Marking the `requires` clause as `transitive` fixes this.
With it any module that depends on _calendar_ can also access _surprise_ (called _implied readability_).

This module's API consists of the class `Calendar`.
For it to be publicly accessible the containing package `org.codefx.demo.advent.calendar` must be exported.
Note that `CalendarSheet`, private to the same package, will not be visible outside the module.
This is analog to before where a package-private class from another package was also not visible.

The final module-info looks as follows:

```java
module org.codefx.demo.advent.calendar {
	// required modules
	requires transitive org.codefx.demo.advent.surprise;
	// publicly accessible packages
	exports org.codefx.demo.advent.calendar;
}
```

Compilation is almost like before but the dependency on _surprise_ must of course be reflected here.
For that it suffices to point the compiler to the directory `mods` as it contains the required module (the sum of such directories is called the _module path_):

```bash
# compile
javac -p mods -d classes/org.codefx.demo.advent.calendar ${list of source files}
# package
jar -c --file=mods/org.codefx.demo.advent.calendar.jar ${compiled class files}
```

### _factories_

The factories implement `SurpriseFactory` so this module must obviously depend on theirs.
And since they return instances of `Surprise` from published methods the same line of though as above leads to a `requires public` clause.

The factories can be found in the package `org.codefx.demo.advent.factories` so that must be exported.
Note that the package visible class `AbstractSurpriseFactory` is **not** accessible outside this module.
As it is currently implemented Jigsaw will also not allow reflection to access it.
The only way around this are command line flags.

Together:

```java
module org.codefx.demo.advent.factories {
	// required modules
	requires transitive org.codefx.demo.advent.surprise;
	// publicly accessible packages
	exports org.codefx.demo.advent.factories;
}
```

Compilation and packaging is analog to _calendar_.

### _main_

Our application requires the two modules _calendar_ and _factories_ to compile and run.
It has no API to export.

```java
module org.codefx.demo.advent {
	// required modules
	requires org.codefx.demo.advent.calendar;
	requires org.codefx.demo.advent.factories;
	// no exports
}
```

Compiling and packaging is like with last section's single module except that the compiler needs to know where to look for required modules:

```bash
javac -p mods -d classes/org.codefx.demo.advent ${list of source files}
jar -c \
	--file=mods/org.codefx.demo.advent.jar \
	--main-class=org.codefx.demo.advent.Main \
	${compiled class files}
java -p mods -m org.codefx.demo.advent
```
