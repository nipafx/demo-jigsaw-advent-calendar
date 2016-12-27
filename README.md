> :arrow_upper_right: This README only covers one section of the demo.
> The [master branch](https://github.com/CodeFX-org/demo-jigsaw-advent-calendar/tree/master) contains more information.

# :three: Services

Jigsaw enables loose coupling by implementing the service locator pattern, where the module system itself acts as the locator.

## Rationale

Somebody who recently read a blog post about how cool loose coupling is looked at our code from [the previous section](https://github.com/CodeFX-org/demo-jigsaw-advent-calendar/tree/02-splitting-into-modules) and complained about the tight relationship between _main_ and _factories_.
To be more precise she critized that _main_ even knows _factories_.
And just because it has to instantiate some implementations of a perfectly fine abstraction (the `SurpriseFactory`)!

Having a man in the middle providing us with these implementing instances would remove the direct dependency.
Even better, if said middleman would be able to find all implementations on the module path, the calendar's surprises could easily be configured by adding or removing modules before launching.

This is indeed possible with Jigsaw.
We can have a module specify that it provides implementations of an interface.
Another module can express that it uses said interface and find all implementations with the [`ServiceLocator`] (for details see [_main_](#main) below).

We use this opportunity to split _factories_ into _chocolate_ and _quote_ and end up with these modules and dependencies:

* _surprise_ - `Surprise` and `SurpriseFactory`
* _calendar_ - the calendar, which uses the surprise API
* _chocolate_ - the `ChocolateFactory` as a service
* _quote_ - the `QuoteFactory`  as a service
* _main_ - the application; no longer requires individual factories

<img src="http://yuml.me/b392c5b0.png"></img>
<!-- // http://yuml.me/edit/b392c5b0
[surprise{bg:green}]
[note:SurpriseFactory{bg:limegreen}]<exposes-[surprise]
[chocolate{bg:blue}]-<>[note:SurpriseFactory]
[quote{bg:blue}]-<>[note:SurpriseFactory]
[calendar{bg:yellow}]->[surprise]
[main{bg:red}]-uses>[note:SurpriseFactory]
[main]->[calendar]
-->

## Implementation

The first step is to reorganize the source code.
The only change [from before](https://github.com/CodeFX-org/demo-jigsaw-advent-calendar/tree/02-splitting-into-modules#implementation) is that `src/org.codefx.demo.advent.factories` is replaced by `src/org.codefx.demo.advent.factory.chocolate` and `src/org.codefx.demo.advent.factory.quote`.

Lets look at the individual modules.

### _surprise_

Nothing changed [from before](https://github.com/CodeFX-org/demo-jigsaw-advent-calendar/tree/02-splitting-into-modules#surprise).

### _calendar_

[Dito](https://github.com/CodeFX-org/demo-jigsaw-advent-calendar/tree/02-splitting-into-modules#calendar).

### _chocolate_

As [before with _factories_](https://github.com/CodeFX-org/demo-jigsaw-advent-calendar/tree/02-splitting-into-modules#factories) this module must `require transitive` the _surprise_ module.

More interesting are its exports.
It provides an implementation of `SurpriseFactory`, namely `ChocolateFactory`, which is specified as follows:

```java
provides org.codefx.demo.advent.surprise.SurpriseFactory
	with org.codefx.demo.advent.factory.chocolate.ChocolateFactory;
```

Since this class is the entirety of its public API it does not need to export anything else.
Hence no other export clause is necessary.

We end up with:

```java
module org.codefx.demo.advent.factory.chocolate {
	// list the required modules
	requires transitive org.codefx.demo.advent.surprise;
	// specify which class provides which service
	provides org.codefx.demo.advent.surprise.SurpriseFactory
		with org.codefx.demo.advent.factory.chocolate.ChocolateFactory;
}
```

Compilation and packaging is straight forward:

```java
javac -p mods -d classes/org.codefx.demo.advent.factory.chocolate ${source files}
jar -c --file mods/org.codefx.demo.advent.factory.chocolate.jar  ${compiled class files}
```

### _quote_

Analog to _chocolate_.

### _main_

The most interesting part about _main_ is how it uses the `ServiceLocator` to find implementation of `SurpriseFactory`.
From [its `main` method](https://github.com/CodeFX-org/demo-jigsaw-advent-calendar/blob/03-services/src/org.codefx.demo.advent/org/codefx/demo/advent/Main.java#L13-L14):

```java
List<SurpriseFactory> surpriseFactories = new ArrayList<>();
ServiceLoader.load(SurpriseFactory.class).forEach(surpriseFactories::add);
```

Our application now only requires _calendar_ but must specifiy that it uses `SurpriseFactory`.
It has no API to export.

```java
module org.codefx.demo.advent {
	// list the required modules
	requires org.codefx.demo.advent.calendar;
	// list the used services
	uses org.codefx.demo.advent.surprise.SurpriseFactory;
	// exports no functionality
}
```

Compilation and execution are [like before](https://github.com/CodeFX-org/demo-jigsaw-advent-calendar/tree/02-splitting-into-modules#main).
