> :arrow_upper_right: This README only covers one section of the demo.
> The [master branch](https://github.com/CodeFX-org/demo-jigsaw-advent-calendar/tree/master) contains more information.

# :one: Creating A Module

The next step is small but important.
It changes nothing about the code or its organization but moves it into a Jigsaw module.

To be able to create a module, the project needs a `module-info.java`:

```java
module org.codefx.demo.advent {
	// no imports or exports
}
```

Its name (here `org.codefx.demo.advent`) can be arbitrary but to ensure uniqueness it is recommended to stick to the inverse URL naming schema of packages.
So while this is not necessary it will often mean that the module name is a prefix of the packages it contains.

The biggest change is the script<sup>1</sup> to compile and run:

```bash
# compile (add module-info.java):
javac -d classes/org.codefx.demo.advent ${list of source files}
# package (add module-info.class and specify main class):
jar -c --file=mods/org.codefx.demo.advent.jar \
	--main-class=org.codefx.demo.advent.Main \
	${compiled class files}
# run (specify a module path and simply name to module to run):
java -p mods -m org.codefx.demo.advent
```

To create a "modular jar" we only need to include a `module-info.class` in the list of files for `jar`.
We can also specify the main class to run when the module is executed.
To run the module we provide a module path to `java` (via `-p`) that points to the directory containing all our modules (just one at the moment).
For execution it suffices to name the module as the JVM will look for it in the module path and find out that a main class was defined.

<sup>1</sup> the commands must come from the JDK 9 `bin` directory;
`compileAndRun.sh` appends `9` to them and ony my machine the resulting commands (e.g. `javac9`) are symlinked to the Java 9 variants. 
