# SpaceInvaders

Following are the packages description and implementation details of the game


# Animation(package) :
•	Animation Runner
This class is used to wrap the animation running algorithms in our code.
It excepts an Animation object, and runs it one frame at a time, with proper fps values until given animation is supposed to be stopped.
•	Animation (Interface)
Targets all of our animation-related template-methods and put them into one interface.
•	Countdown Animation
Holds the properties for a countdown animation graphics, and is responsible
To run it according to given values of seconds (for entire animation) , and starting number.
•	EndScreen
Shows the proper End-screen animation.
Responsible for showing the right animation for a winner or a loser.
•	HighScoresAnimation
Hold the properties for a high-score table animation graphics, with updated values.
•	KeyPressStoppableAnimation
Used to extract the "waiting-for-key-press" behavior away from the different screens, and into a class - that will wrap an existing animation and add a "waiting-for-key" behavior to it.
•	PauseScreen
Runs a pause screen animation.
•	TextBorder
Used to draw borders for given strings.


# Animation.menu(package):
        • Menu (Inteface)
        General menu interface used to wrap menu's methods.
        • MenuAnimation
        Implement the Interface above for a menu animation object,
        Which is responsible to hold tasks for a menu object.
        Its used to run menu's animation –  by displaying all of the menu's options and react according to user's key-press.
        • Task(Interface)
        This interface wraps a run method for a task, so when a user selection is made on a menu, the task will run according to his key press, and return a value for a task.







# Collidable (package):
•	Alien
Extends Block class. Responsible for a single alien object, its behavior on a collision,
And graphics.
•	AlienFormation 
This class holds a matrix of Alien objects as a list and is responsible for their entire game behavior as a formation of moving aliens.
It holds a starting position, bounds, movement speed for a formation, as well as position resetting and velocity changes on certain events.
•	Block
A Class for a block object. Defines properties such as width, height and position for a block.
Class is also responsible for defining basic blocks behavior on a collision (Velocity change for hitter), or other non-basic behaviors that react as defined for a hit event and drawing methods.
•	Collidable(Interface)
Wraps methods for a collidable object. Main target is to define a method for each object on hit event.
•	CollisionInfo
Responsible for storing and returning information of a collision event : object who participated in the collision and point of collision .
•	Paddle
Paddle is a Rectangle controlled by user , responsible for bouncing the ball in the event of collision with a ball object.
•	SpaceShip
Space-Ship is a Rectangle controlled by user – extends paddle class . Responsible for destroying Aliens in their formation by shooting on a key press , so user can pass levels and gain score.
Constants (package) :
•	Consts
Class holds game related constants , that are used in the entire program , and are not related to a specific level.
•	LevelConsts
Holds all of the Constants for default game levels defined on Assignment 3;
 such as number of balls per level , number and position of blocks and more.
Factories (package) :
•	BlockColorDrawer
Responsible for drawing a block object as a single colored rectangle.
•	BlockCreator (Interface)
Wraps block-creation factories in an interface, for a creation method.
•	BlockDrawer (Interface)
Wraps block-drawing factories in an interface, for a drawing method.
Each block drawer holds the right drawing method for a block as defined .
Block objects hold a list of block drawers that are used to draw the block as defined.
•	BlockFactory
•	BlockImageDrawer
•	BlocksFromSymbolFactory
•	ColorParser
•	ImageParser
•	LevelFactory









# Game (package) :
•	Ass7Game
Main class that holds the main function for the current game.
Responsible for Creating a menu with a start game option , high score table , and exit tasks. 
•	GameEnvironment
•	GameFlow
•	SpaceInvaderLevel
Class is used to define a default structure for a level.
It holds most of the game logic and is responsible for holding initializing methods of a level such as background creation and shooting behaviors for player and enemies.


game.Levels(package)  :
	LevelInformation (Inteface)
	LevelSet
	VelocityConstructor
	ColorBackground
	ImageBackground
        game.levels.defaultLevels (package):
        •	LevelDirectHit 
        •	LevelFinalFour
        •	LevelGreenThree
        •	LevelWideEasy
                Game.levels.defaultLevels.backgroundGraphics:
                o	LevelDirectHit 
                o	DirectHitBackground
                o	FinalFourBackground
                o	GreenThreeeBackground
                o	WideEasyBackground


game.listener(package):
	AlienListener (Inteface)
This interface wraps listeners classes who implements it with a hitEvent method . Aliens will hold a list of AlienListeners to react to an hit event.
	AlienRemove
This class implements the above interface , and is responsible for the reaction of an alien in occasion of a hit event.
it is used to remove an alien from the game in the occasion of player hit and from its formation, and add to player's score.
If an alien hits another alien this listener remove the ball with no further action.
	SpaceShipHitListener
Sets a space ship as hitted .( game reacts according to space ship state and remove from lives counter)


Geometry (package):

•	Line
•	LineEquation
•	Point
•	Rectangle
Io (package) :
•	BlockDefinitionReader
•	LevelSetReader
•	LevelSpecificationReader







Sprites (package):
•	Ball
•	InfoBlock
•	LevelNameIndicator
•	ScoreIndicator
•	Sprite (Interface)
•	SpriteCollection
•	Velocity


