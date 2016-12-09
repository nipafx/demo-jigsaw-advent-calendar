> :arrow_upper_right: This README only covers one section of the demo.
> The [master branch](https://github.com/CodeFX-org/demo-jigsaw-advent-calendar/tree/master) contains more information.

# :five: Optional Dependency

With the `requires` clause modules must be present at compile _and_ run time.
But with `requires static` it is possible to write code against types that are not mandatory at run time.
This allows applications, libraries, and particularly frameworks
 to write code that adapts to the modules present at run time
 without creating a host of mandatory dependencies.

We will employ this feature to allow users to configure the advent calendar
 by choosing the modules with which the app launches.
Note that for this particular use case services are a better fit,
 which is why [we used them for that](https://github.com/CodeFX-org/demo-jigsaw-advent-calendar/tree/03-services),
 but it is still a good example.

_Under Construction_

In this scenario we have the following modules:

Both factories are only required at compile time.
That means code can reference them (as it does below to call their constructor)
 but such calls can fail at run time with 'NoClassDefFoundError' if the modules
 were not resolved to be part of the module graph.

* the '--add-modules' option is used to add modules that are not required by any other module
