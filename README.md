# SpaceInvaders

Following are the packages description and implementation details of the game

# Collidable (package):
• Alien
    Extends Block class. Responsible for a single alien object and its behavior on a collision.
    Also contains a method for moving a single alien object by given units.
    <br>
• AlienFormation
    This class holds a matrix of Alien objects and is responsible for their entire game behavior as a formation of moving aliens.
    It holds members such as starting position, bounds and movement speed for a formation.
    An Alien-Formation holds methods such as position resetting, or entire formation movement considering its bounds
• SpaceShip
    Space-Ship is a Rectangle controlled by user – extends paddle class. Responsible for destroying Aliens in their formation by shooting on a key press, so user can pass levels and gain score.


# Game (package) :
• Ass7Game
    Main class that holds the main function for the current game.
    Responsible for Creating a main menu with matching tasks, creating a new game flow object on user's demand (when starting a game) and running it.
• SpaceInvaderLevel
    Class is used to define a default structure for a level.
    It holds most of the game logic and is responsible for level-initializing methods such as background creation.
    It also holds shooting behaviors for player and enemies, and animation running methods for a game level.
• GameFlow
    Is responsible for the levels game flow–
    Which means that it is initializing and running a new game level from the above class with given start speed, that is increasing in each new level. It's also responsible to know if a player lost – and stops the changing levels loop, to present the player with the right end screen.


# game.listener(package):
▪ AlienListener (Inteface)
    This interface wraps listeners classes who implement it with a hitEvent method. Aliens will hold a list of AlienListeners to react to hit events.
▪ AlienRemove
    This class implements the above interface and is responsible for the reaction of an alien in occasion of a hit event.
    it is used to remove an alien from the game in the occasion of player hit and from its formation.
    If an alien hits another alien this listener removes the ball with no further action.
▪ SpaceShipHitListener
    Is responsible to set all space ship objects who hold it as hit.
    game reacts according to space ship state and decrease the lives counter)


# Aliens formation Implementation:

First, I created a single alien object class – that extends the block class.
It holds a constructor, alien graphics, and methods to move a single alien in position, draw a single alien , and react on an hit event using hitListeners and alienListeners interfaces.
(alienListeners – are used to react in the event of alien being hit.
It removes an alien from the game and from its formation if needed, and holds other properties.)
Aliens holds an alienListeners list.
After creating this class, I created another class for an Alien formation .
This class holds a matrix of aliens, with starting position and movement speed for an entire formation . its time passed method moves the formation around the screen according to requirements , and holds other methods to wrap an entire formation behavior .
Shields Implementation:
Shields are basically just blocks.
I used the blocks class, and implemented a shield creation method on SpaceInvaderslevel Class (according to requirements; such as number of shields and position) –
so when a game has started shields will be initialized. Shields hold ball remover listener.
Shots Implementation:
Shot is a ball with constant velocity, released from an alien or a spaceship.
Both Player and Alien shots are implemented in SpaceInvadersLevel class.
Alien shots are released from a random alien every 0.5 seconds .
Player shots can be released every 0.35 sec and released only on space key-press.
Game level holds 2 arrays; 1 for player shots and 1 for alien shots –
To react according to each shot and its hit. Balls are removed when hitting an object, or when they are out of boundaries.
