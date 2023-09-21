#!/bin/bash -ex

javac ItemType.java
javac NodeType.java
javac SortedLinkedList.java
javac LinkedListDriver.java
java  LinkedListDriver input.txt
