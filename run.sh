#!/bin/sh
cd "$(dirname "$0")"
mkdir -p out
javac -d out -cp lib/sqlite-jdbc.jar $(find src -name "*.java") && java -cp "out:lib/sqlite-jdbc.jar" Main
