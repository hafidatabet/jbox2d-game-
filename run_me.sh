#!/bin/sh


javac -d bin -sourcepath src -cp lib/jbox2d-library-2.1.2.jar src/com/zerubeus/main/Main.java;src/com/zerubeus/screen/*.java;src/com/zerubeus/characters/*.java
java -cp bin;lib/jbox2d-library-2.1.2.jar com.zerubeus.main.Main

