[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/J_c8sizy)
# MassiveMotion
CS 245 Project 02

## Overview
MassiveMotion is a Java animation that simulates moving celestials bodies (comets and a star). This project demonstrates the use of different list data structure implementations in a real-time graphical application.

## Features
- **Animated celestial bodies**: A red star (stationary) and multiple black comets that bounce around the screen.
- **Configurable list implementations**: Choose between different list data structures for storing comet objects
- **Properties-based configuration**: Customize window size, animation speed, and object properties via configuration file
- **Real-time animation**: Smooth movement with configurable timer delays

## Project Structure
```
src/
├── MassiveMotion.java          # GUI and animation logic
├── Config.java                 # Configuration accessor and parser
├── List.java                   # List interface
├── ArrayList.java              # ArrayList implementation
├── SinglyLinkedList.java       # SinglyLinkedList implementation
├── DoublyLinkedList.java       # DoublyLinkedList implementation
└── DummyHeadLinkedList.java    # DummyHeadLinkedList implementation
MassiveMotion.txt               # Configuration file
```

## List Implementations
This project includes four list implementations, all conforming to the `List<T>` interface:
1. **ArrayList**: Array based implementation with dynamic resizing
2. **SinglyLinkedList**: Node based implementation with forward links only
3. **DoublyLinkedList**: Node based implementation with bidirectional links
4. **DummyHeadLinkedList**: Singly linked list with a sentinel head node for simplified operations

## Contents of the Configuration File
Create a `MassiveMotion.txt` file in the project root with the following properties

```properties
timer_delay = 75
list = arraylist            #Options: arraylist, singlylinkedlist, doublylinkedlist, dummyheadlinkedlist.

window_size_x = 1024
window_size_y = 768

gen_x = 0.06
gen_y = 0.06
body_size = 10
body_mass = 1E21
body_velocity = 3

star_position_x = 512
star_position_y = 384
star_size = 30
star_mass = 2E29
star_velocity_x = 0
star_velocity_y = 0
```

## How to Run:

### To compile
```bash
javac src/*.java
```
### To Execute
```bash
javac src/MassiveMotion MassiveMotion.txt
```

## How it works
1. **Initialization**
   - Reads configuration from the properties file
   - Creates the appropriate list implementation based on the `list` property
   - Generates 10 comets with random positions and velocities
   - Sets up the GUI window and timer

2. **Animation Loop**:
   - Timer triggers the `actionPerformed` method every `timer_delay` milliseconds
   - Each comet's position is updated based on its velocity
   - Comets bounce off window edges (velocity reversal)
   - Screen is repainted to show new positions

3. **List Usage**:
   - Comets are stored in the configured list implementation
   - The animation loop iterates through the list using `get(i)`
   - Demonstrates real-world usage of different data structures
  
## Demostration



https://github.com/user-attachments/assets/6cac6a4c-fdf9-449f-9c38-dd5d3e337869

