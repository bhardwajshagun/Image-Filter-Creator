Project Description:

Our project represents the Model, Controller, and View for altering and generating images.

Our program utilizes 1 main model interface:
	Image
All of the classes fall under thus interface.
Our program can be used by creating objects of these 2
data types, and using the "apply" function to perform the action.
The user then received the RGB array that stores their new image.
The user can than save the image to see the product.

This model and controller for this project are now complete.

Instructions to run this program:
	The controller can be used by typing in the terminal/command prompt:
		java -jar "Final Project.jar" -interactive
		OR
		java -jar "Final Project.jar" -script file.txt
	For GUI:
		The user can perform all actions on a GUI.
		The GUI will show the images as they are changed.
		The user can also under/redo any changes. However,
		after saving, the user cannot undo.
		The user can also type in a script into the panel,
		and execute it through the GUI.
	For script file:
	The controller will than read each line, executing the actions.
	List is controller actions:
		"load": loads the image file
			1 argument: image filename
		"save": saves the current image
			1 argument: new image filename
		"blur": blurs the current image
			0 arguments
		"sharpen": sharpens the current image
			0 arguments
		"greyscale": greyscales the current image
			0 arguments
		"sepia": sepias the current image
			0 arguments
		"dither": dithers the current image
			0 arguments
		"mosiac": creates a mosaic of current image based on number of seeds
			1 argument: number of seeds
		"generate": creates an image, based on follwing commands:
			"france": creates a france flag
				1 argument: height of image in pixels
			"switzerland": creates a swiss flag
				1 argument: height of image in pixels
			"greece": creates a greece flag
				1 argument: height of image in pixels
			"checkerboard": creates a checkerboard image
				1 argument: side of square in pixels
			"verticalstripes": creates an image with rainbow vertical stripes
				2 arguments: height and width of image in pixels
			"horizontalstripes": creates an image with rainbow horizontal stripes
				2 arguments: height and width of image in pixels
	The controller prints messages if the there is any problem executing any of these commands.



Image citations:

test1.png:
Dids. “Photo of High-Rise Building.” Pexels, 18 Mar. 2019,
www.pexels.com/photo/photo-of-high-rise-building-2033332/.

test2.png:
Pixabay. “Heart Red Leafed Tree on Red Field.” Pexels, 22 Oct. 2018,
www.pexels.com/photo/artistic-blossom-bright-clouds-207962/.



CLASS DESCRIPTIONS:

We altered the model so that all of the classes are implemented from 1 interface.
We made this change because this makes it easier to utilize the classes.
We renamed some of the methods (from filter and createImage to apply),
to fit all of the classes under 1 model interface.

Our project has 1 main interface it uses for the model:
Image

Image has the following methods:
	apply: applies the change that that specific class does
	getHeight: returns the height of the image in pixels
	getWidth: returns the width of the image in pixels
	
ImageAlter is an abstract class that implements Image.
This class holds code that all our subclasses require.
The constructor takes in an rgb array, width, and height as parameters
and stores them as attributes.
The getHeight and getWidth methods return height and width, respectively.
clampRGB is a method that clamps the rgb value after any modifications,
so they are always between 0 and 255.

ImageFilter is an abstract class that extends ImageAlter.
This class contains a method applyHelp.
applyHelp alters and returns the RGB array of an image based on the kernel, which
it takes as a parameter.

Blur is a class that extends ImageFilter.
Blur has 1 method: apply.
apply passes in the Blur kernel into applyHelp.
It returns the new RGB array.

Sharpen is a class that extends ImageFilter.
23791 has 1 method: apply.
apply passes in the Sharpen kernel into applyHelp.
It returns the new RGB array.

ColorTransformation is an abstract class that extends ImageAlter.
This class contains a method applyHelp.
transformHelp alters and returns the RGB array of an image based on a matrix, which
it takes as a parameter.

Greyscale is a class that extends ColorTransformation.
This class has 1 method: apply.
apply passes in the Greyscale matrix as a parameter into transformHelp,
and returns the RGB array.

Sepia is a class that extends ColorTransformation.
This class has 1 method: apply.
apply passes in the Sepia matrix as a parameter into transformHelp,
and returns the RGB array.

Dither is a class that extends ColorTransformation.
This class had 1 method: apply.
apply calls the ditherHelp method, and returns the new dithered RGB array.

Mosaic is a class that extends ColorTransformation.
This class had 1 method: apply.
apply calls the mosaicHelp, which takes in number of seeds as a parameter method,
and returns the new mosaiced RGB array.

StripesImage is an abstract class that implements Image.
It creates a stripes image of rainbow colors.
This class holds code that all our subclasses require.
The constructor takes in the height and width as parameters, and stores
them as attributes.
The getHeight and getWidth methods returns height and width, respectively.
createHelper is a function that takes in an enum StripesPath, which helps
figure out if the image should have Horizontal stripes or vertical stripes.
It returns the RGB array of the image.

HorizontalStripes is a class that extends StripesImage.
It has the apply method.
It passes in an enum HORIZONTAL intro the createHelper method,
and returns the horizontal rainbow stripes RGB array.

VerticalStripes is a class that extends StripesImage.
It has the apply method.
It passes in an enum VERTICAL intro the createHelper method,
and returns the horizontal rainbow stripes RGB array.

CheckerBoardImage is a class that implements Image.
It creates an 8x8 checkerboard pattern.
It's constructor takes in the side of a square as a parameter,
and stores it as an attribute.
It has a apply method, which creates the checkerboard pattern,
with the squares length side.
It has getHeight and getWidth methods returns the length of the image, respectively.

FlagImage is an abstract class that implements Image.
It takes in the height and width as parameters, and stores the
height as an attribute.

FranceFlag is a class that extends FlagImage.
It calculates the width from the flag from the height,
as it has a 2:3 aspect ratio.
The apply method creates and returns a RGB array that
represents France's Flag.

SwitzerlandFlag is a class that extends FlagImage.
It calculates the width from the flag from the height,
as it has a 1:1 aspect ratio.
The apply method creates and returns a RGB array that
represents Switzerland's Flag.

GreeceFlag is a class that extends FlagImage.
It calculates the width from the flag from the height,
as it has a 2:3 aspect ratio.
The apply method creates and returns a RGB array that
represents Greece's Flag.