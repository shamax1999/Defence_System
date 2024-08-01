

https://github.com/user-attachments/assets/80b959c9-13d5-4a12-8058-66ce86a90f73

Defense System

Welcome to the Defense System project repository! This project is a Java-based simulation that manages and monitors various defense units, such as Helicopters, Tanks, and Submarines. It showcases key Object-Oriented Programming (OOP) concepts, including encapsulation, inheritance, polymorphism, and abstraction.

Project Overview

The Defense System project simulates a defense management system, focusing on the coordination and communication between different defense units through graphical user interfaces (GUIs) and an observer pattern.

Key Features:
- Defense Units: Simulate Helicopters, Tanks, and Submarines, each with unique attributes and functionalities.
- Communication: Facilitates interactions and information exchange between defense units and a central controller.
- Central Controller (MainController): Manages and observes defense units, coordinating overall operations.
- Observer Pattern: Implements an observer pattern to notify the MainController about changes and updates in defense units.

OOP Concepts

- Encapsulation
- Inheritance
- Polymorphism
- Abstraction

Project Components

1. Helicopter:
   - Role: Aerial defense unit with mobility and firepower.
   - User Interaction: Allows actions like shooting, missile operations, and laser operations.
   - Observer Interaction: Notifies the MainController of changes in area, war strength, and messages.

2. Tank:
   - Role: Ground-based defense unit with firepower and durability.
   - User Interaction: Manages shooting, soldier count, and ammo count.
   - Observer Interaction: Reports changes in area, war strength, and messages to the MainController.

3. Submarine:
   - Role: Underwater defense unit specialized in missile deployment and sonar operations.
   - User Interaction: Manages underwater shooting, missile deployment, and energy and oxygen levels.
   - Observer Interaction: Communicates changes in area, war strength, and messages to the MainController.

4. MainController:
   - Role: Central coordinator for managing interactions among defense units.
   - Strength Calculation: Aggregates defense strength from individual units and broadcasts updates.
   - Global Operations: Facilitates coordinated responses among units and manages area messages.

Flow of the System

- Initialization: The Starter class sets up the observer and MainController, creating instances of Helicopter, Tank, and Submarine.
- User Interaction: Users interact with GUIs, triggering events handled by the defense units.
- Strength Signal: Simulates strength changes and updates all units accordingly.

Technologies Used

- Java Swing: For creating graphical user interfaces.
- Observer Pattern: To manage communication and updates among defense units.

 


