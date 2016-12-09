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

echo " > compiling and packaging ChocolateFactory"
mkdir classes/org.codefx.demo.advent.factory.chocolate
javac9 \
	-p mods \
	-d classes/org.codefx.demo.advent.factory.chocolate \
	$(find src/org.codefx.demo.advent.factory.chocolate -name '*.java')
jar9 \
	-c \
	--file mods/org.codefx.demo.advent.factory.chocolate.jar \
	-C classes/org.codefx.demo.advent.factory.chocolate/ .

echo " > compiling and packaging QuoteFactory"
mkdir classes/org.codefx.demo.advent.factory.quote
javac9 \
	-p mods \
	-d classes/org.codefx.demo.advent.factory.quote \
	$(find src/org.codefx.demo.advent.factory.quote -name '*.java')
jar9 \
	-c \
	--file mods/org.codefx.demo.advent.factory.quote.jar \
	-C classes/org.codefx.demo.advent.factory.quote/ .

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

echo " > running Advent by specifying additional modules"
java9 \
	--add-modules org.codefx.demo.advent.factory.chocolate,org.codefx.demo.advent.factory.quote \
	-p mods -m org.codefx.demo.advent
