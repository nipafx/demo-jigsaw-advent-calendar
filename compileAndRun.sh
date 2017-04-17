#!/bin/bash

echo " > creating clean directories"
rm -r classes
mkdir classes
rm -r mods
mkdir mods

echo " > compiling modules"
mkdir classes/org.codefx.demo.advent.surprise
javac9 -d classes \
	--module-source-path "src" \
	--add-modules org.codefx.demo.advent.factory.chocolate,org.codefx.demo.advent.factory.quote \
	--module org.codefx.demo.advent

echo " > packaging modules"
jar9 --create \
	--file mods/org.codefx.demo.advent.surprise.jar \
	-C classes/org.codefx.demo.advent.surprise/ .
jar9 --create \
	--file mods/org.codefx.demo.advent.calendar.jar \
	-C classes/org.codefx.demo.advent.calendar/ .
jar9 --create \
	--file mods/org.codefx.demo.advent.factory.chocolate.jar \
	-C classes/org.codefx.demo.advent.factory.chocolate/ .
jar9  --create \
	--file mods/org.codefx.demo.advent.factory.quote.jar \
	-C classes/org.codefx.demo.advent.factory.quote/ .
jar9 --create \
	--file mods/org.codefx.demo.advent.jar \
	--main-class org.codefx.demo.advent.Main \
	-C classes/org.codefx.demo.advent/ .

echo " > running Advent"
java9 --module-path mods \
	--module org.codefx.demo.advent
