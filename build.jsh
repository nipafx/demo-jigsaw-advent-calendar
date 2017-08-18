//usr/bin/env jshell --show-version --execution local "$0" "$@"; exit $?

System.setProperty("bach.verbose", "true")

/*
 * Source built-in printing support.
 */
/open PRINTING
printf("%n > print working directory%n%n")
println(Paths.get(".").normalize().toAbsolutePath())

/*
 * Download "Bach.java" and "Bach.jsh" from github to local "target" directory.
 */
Path target = Files.createDirectories(Paths.get("target"))
URL context = new URL("https://raw.githubusercontent.com/sormuras/bach/master/")
for (Path script : Set.of(target.resolve("Bach.java"), target.resolve("Bach.jsh"))) {
  // if (Files.exists(script)) continue; // uncomment to preserve existing files
  try (InputStream stream = new URL(context, script.getFileName().toString()).openStream()) {
    Files.copy(stream, script, StandardCopyOption.REPLACE_EXISTING);
  }
}

/*
 * Source "Bach.java" and "Bach.jsh" into this jshell session.
 */
/open target/Bach.java
/open target/Bach.jsh

Path sources = Paths.get("src")
Path mods = Paths.get("target", "mods")

/*
 * Use it!
 */
printf("%n > print jdk version information%n%n")
java("--version")


printf("%n > compile multiple modules%n%n")
JdkTool.Javac javac = new JdkTool.Javac()
javac.moduleSourcePath = List.of(sources)
javac.destinationPath = mods
javac.run()


printf("%n > run from destination path %s%n%n", mods)
java("--module-path", mods, "--module", "org.codefx.demo.advent/org.codefx.demo.advent.Main")


/exit
