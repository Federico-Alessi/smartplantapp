# smartplantapp

Smartplantapp is an application that allows you to manage all your plants, providing them water and fertiliser as needed.

Using specific Smartplantapp™ sensors, the app checks if your plants need more or less light and if the temperature is good (Please note that support for other sensors may be limited, but if you contact us, we will be happy to implement it).

You wount even need to know how to care for them!


## Overview

### House
Smartplantapp most important interface is House, which defines methods that both plants and locations must implement:
- `showDetails()` returns a string containing all the details about the object; plants and locations are stored in this form in both locations and collections
- `getName()` returns a string containing the name of the object, it is used by different classes to clarify to the user which object they are working on
- `printDetails()` prints the string returned by `showDetails()`

### Location
Location class designed to store plants and other locations within a list; it provides a way to virtually represent the house of the user, but it opens the door for future implementations of other functionalities.

### Plant
Represents the actual plants, providing details about their light, temperature, soil moisture needs and the date of the last fertilization (set to null by default: must either be updated manually or by the correct handler).

The Categories enum provides 3 categories used to automatically use the correct fertiliser type and allows to add other categories without needing to modify existing plants.

The sensors map stores sensors for each plant thanks to the `connectSensors()` methods, which handles both empty sensors map (adding new sensors) and already filled sensors map (removing existing ones an adding new ones, simulating reconnection).

`readSensor` allows to read specific sensors, also managing an empty sensors map and eventual problems while connecting sensors.

> continue from here



#
## Creating locations
Locations are objects that can store plants and other rooms as childrens; they are useful for choosing the right place to put your plants.
To create a location, you only need to instantiate a new element of the `Location` class, inserting as parameters:
1. The name of the location
2. A boolean true if is an indoor location, otherwise false
3. The daily sun exposure in hours
```java
Location location = new Location(String name, bool isIndoor, int sunExposure);
```
#
### Accessing locations

#### Manage children
- To add a children, use the method `addComponent(House component)`
- To remove a children, use the method `removeComponent(House component)`

*Example:*
```java
Location living = new Location("livingroom", true, 6);
Location balcony = new Location("balcony", false, 6);
living.addComponent(balcony);
living.addComponent(pilea);
balcony.addComponent(gaillardia);
```

#### Location details
You can see all the details of the location with the `printDetails()` method.
```java
myplant.printDetails()
```
This will show you if the location is indoor (room) or if it's an outdoor location, the name and the sun exposure, finally it will show all the children it has.

You can also print all the detail individually:
- `getName()` will return the location's name
- `getIsIndoor()` will return the location's indoor boolean
- `getSunExposure()` will return the light daily amount of light it receives in hours
- `getChildren()` will return all the location's children
- `getChildren()` will show you all the location's children


#
## Creating plants
to create new plants, follow this steps:
1. Create a new variable of type "Plant"
```java
Plant myplant;
```
3. Using the PlantFactory class, create a new plant assigning the name and the category
```java
Plant myplant = new PlantFactory("plant name", Plant.Categories.GREENPLANT)
```
The plant factory class automatically assigns plant needs based on the category.


#
### Accessing plants

#### Sensors
Now that you've created your plant, you can access the sensors directly by using the `readSensor(String sensorkey)` method.
```java
System.out.println(pilea.readSensor("Hygrometer"));
```
The available sensor keys are `Hygrometer`, `Thermometer` and `Fotometer`.
When you try to read a sensor, the application will check if there are sensor assigned to the plant and, if not, will try to connect them.
If a log tells you that there was an error connecting the sensors, check if the sensor's wire is plugged and refere to the sensor's manual to solve further errors.

#### Plant details
You can see all the details of the plant with the `printDetails()` method. This will show you the plant name, it's category and all its needs.
```java
myplant.printDetails()
```
You can also print all the detail individually:
- `getName()` will return the plant's name
- `getCategory()` will return the plant's category
- `getSunHousrsNeeded()` will return the light needs
- `getTemperatureNeeds()` will return the temperature needs
- `getWateringNeeds()` will return the humidity needs
- `getLastFertilized()` will return the exact date of the last time the plant was given fertilser

*Example:*
```java
System.out.println(myplant.getName());
System.out.println(myplant.getCategory());
System.out.println(myplant.getLastFertilized())
```

#### Fertilise
If you wish to manually fertilise your plants, you should update the last fertilization date using `myplant.setLastFertilized()`; this will automatically set the value to the the instant in which it gets called.


#
## Letting the app care for plants
If you want the app to automatically care for your plants, you can let it handle all the parameters individually or set up a chain of responsibility

### Individual control
Smartplantapp offers 3 handlers (1 for each sensor), that compare the plant's needs with the sensors readings and take action if needed.
The light handler and the temperature handler send you a notification if plant's needs are not met, while the watering handler is able to manage watering and fertilization needs.
To instantiate handlers:
```java
WateringHandler wateringHandler = new WateringHandler(); #instantiate the watering handler
TemperatureHandler temperatureHandler = new TemperatureHandler(); #instantiate the temperature handler
LightHandler lightHandler = new LightHandler(); #instantiate th light handler
```
After instantiation, you can use them with the method `careForPlant(Plant)`:
```java
wateringHandler.careForPlant(myplant);
temperatureHandler.careForPlant(myplant);
lightHandler.careForPlant(myplant);
```

### Chain of responsibility
After instantiating the three handler, you can set up a chain of responsibility; this allows to call all the 3 handlers by just calling the first one.
To correctly configure the chain, use the method `setNextStep(CareHandler)` to assign to each handler the next one.

*Example:*
```java
wateringHandler.setNextStep(temperatureHandler); #watering handler will call temperature handler
temperatureHandler.setNextStep(lightHandler); #temperature handler will call light handler
```
*To start the chain:*
```java
wateringHandler.careForPlant(myplant);
```



#
## Collections
Smartplantapp offers a simple solution not to forget where you placed all your precious plants.
A collection can store Locations or Plants, so that wether you forgot where you have placed your cactus or you need to remember which fertiliser you should buy for your monstera, you can easilly look it up.

#
### Setup
Setting up a collection is easy: you just need to instantiate an object of type `Collection<T extends Home>`:
```java
Collection<Plant> plants = new Collection<>("my_plants"); #plant collection
Collection<Location> locations = new Collection<>("my_house"); #location collection
```

### Use

#### Add elements
To add elements to your collection, use the method `addElement(T)`
```java
plants.addElement(myplant);
```

#### Remove elements
To remove an element from a collection, use `removeElement(T)`
```java
plants.removeElement(myplant);
```
> Note:
> removing the only element of a collection will result in the deletion of the collection file

#### Display the collection
When you need to look at your collection, call `displayElements()` method.
This will display each the elements using `printDetails()`.
```java
plants.displayElements();
```

#### Other methods
There are two methods that can be used for testing purposes:
- `getPath()` will return the path of the collection file
- `getFileName()` will return the name of the file without the extension
#
