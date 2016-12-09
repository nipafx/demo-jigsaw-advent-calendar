#!/bin/bash

echo " > creating clean directories"
rm -r classes
mkdir classes
rm -r mods
mkdir mods

echo " > compiling and packaging Surprise"
mkdir classes/org.codefx.demo.advent.surprise
javac9 \
	-d classes/org.codefx.demo.advent.surprise \
	$(find src/org.codefx.demo.advent.surprise -name '*.java')
jar9 \
	-c \
	--file mods/org.codefx.demo.advent.surprise.jar \
	-C classes/org.codefx.demo.advent.surprise/ .

echo " > compiling and packaging Calendar"
mkdir classes/org.codefx.demo.advent.calendar
javac9 \
	-p mods \
	-d classes/org.codefx.demo.advent.calendar \
	$(find src/org.codefx.demo.advent.calendar -name '*.java')
jar9 \
	-c \
	--file mods/org.codefx.demo.advent.calendar.jar \
	-C classes/org.codefx.demo.advent.calendar/ .

echo " > compiling and packaging Factories"
mkdir classes/org.codefx.demo.advent.factories
javac9 \
	-p mods \
	-d classes/org.codefx.demo.advent.factories \
	$(find src/org.codefx.demo.advent.factories -name '*.java')
jar9 \
	-c \
	--file mods/org.codefx.demo.advent.factories.jar \
	-C classes/org.codefx.demo.advent.factories/ .

echo " > compiling and packaging Advent"
mkdir classes/org.codefx.demo.advent
javac9 \
	-p mods \
	-d classes/org.codefx.demo.advent \
	$(find src/org.codefx.demo.advent -name '*.java')
jar9 \
	-c \
	--file mods/org.codefx.demo.advent.jar \
	--main-class org.codefx.demo.advent.Main \
	-C classes/org.codefx.demo.advent/ .

echo " > running Advent"
java9 -p mods -m org.codefx.demo.advent
