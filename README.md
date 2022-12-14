# Snakee

#### by Ainsley Lee

## Brief project description

This project is based on the classic retro game called Snake. A new implementation has been created but not completed and I have been tasked with the objective to maintain and extend the re-implementation.

## How to install and run the project

### Installation:
Select device platform when downloading, to run game your machine must be up to date with Java19.

* [IntelliJ](https://www.jetbrains.com/idea/download/)
* [Java19](https://www.oracle.com/uk/java/technologies/downloads/#jdk19)

### Run Steps:
```
1. unzip the project folder
2. load IntelliJ and open the Project
3. at the top select File -> Project Structure -> Project -> SDk
4. locate your jdk-19 directory and select this
5. at the right click the maven tab 
6. in this tab COMP2013-Courswork -> Plugins -> javafx -> javafx:run
7. this will run the game!
```

## How to use the project

### Start Screen

When the game first loads you will be greeted with the start screen.

To customise your experience fill in the first box with your player name - this will be displayed on the screen as you play
and used when storing your score on the leaderboard.

Chose a theme and speed from the dropdowns and press start to play!

The leaderboard button can be pressed to view the top five players.

### Gameplay

Use WASD to move the snake/pacman/spaceship.

To grow in length and increase your score you need to eat the food objects.

Avoid the obstacles generated as these reduce your score and length, be careful as the obstacles move often!

Click below to view the different theme images for food and obstacles:
* [Snake](src/main/resources/com/Snake/images/snake)
* [Pacman](src/main/resources/com/Snake/images/pacman)
* [SpaceInvaders](src/main/resources/com/Snake/images/spaceinvaders)

As you progress through the levels the target score increases as well as the spawn speed of the food and obstacle objects, good luck!


## Credits [if any third-party element were used]

* Intellij
* javafx
* SceneBuilder