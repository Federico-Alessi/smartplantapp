# smartplantapp

Smartplantapp is an application that allows you to manage all your plants, providing them water and fertiliser as needed.

Using specific Smartplantapp™ sensors, the app checks if your plants need more or less light and if the temperature is good (Please note that support for other sensors may be limited, but if you contact us, we will be happy to implement it).

You wount even need to know how to care for them!


## Overview
![smart_plant_app](https://github.com/user-attachments/assets/b2cff482-e6b7-425c-81b9-1a18312bd370)




### House
Smartplantapp most important interface is House, which defines methods that both plants and locations must implement:
- `showDetails()` returns a string containing all the details about the object; plants and locations are stored in this form in both locations and collections
- `getName()` returns a string containing the name of the object, it is used by different classes to clarify to the user which object they are working on
- `printDetails()` prints the string returned by `showDetails()`

### Location
Location class designed to store plants and other locations within a list; it provides a way to virtually represent the house of the user, but it opens the door for future implementations of other functionalities.

Locations can be created using the proper constructor (see [Creating Locations](#creating-locations))

### Plant
Represents the actual plants, providing details about their light, temperature, soil moisture needs and the date of the last fertilization (set to null by default: must either be updated manually or by the correct handler).

The Categories enum provides 3 categories used to automatically use the correct fertiliser type and allows to add other categories without needing to modify existing plants.

The sensors map stores sensors for each plant thanks to the `connectSensors()` methods, which handles both empty sensors map (adding new sensors) and already filled sensors map (removing existing ones an adding new ones, simulating reconnection).

`readSensor` allows to read specific sensors, also managing an empty sensors map and eventual problems while connecting sensors.

_Here is a table representing what sensor each key refers to:_

|KEY           |SENSOR         |
|:------------:|:-------------:|
|"Hygrometer"  |Hygrometer     |
|"Thermometer" |Thermometer    |
|"Photometer"  |Light exposure |

#### Plant factory
Plant parameters are specific to plant categories, that's why the plant factory simplify the creation of a plant by only requiring the name and the category as parameters.

The factory creates plant objects assigning them the correct parameters based on the category.

### Collections
`Collection` class allows to create simple .txt databases for plant and location objects.

Elements are stored in the form of text lines, specifically the output of the `showDetails()` method of House objects, providing a way for users to rapidly check all their plants or locations whithout needing to call the method repeatedly.

A collection can store either plants or locations, but not both simultaneously; the reason for this is that, while they are implementations of the same interface, they are radically different elements:

Location collections, for example, can be useful when trying to remember the location of a specific plant, because calling `displayElements()` will display all the children of each location.
Plant collections, on the other hand, can be useful when trying to remember the needs of different plants simultaneously.

### Sensors
Sensors are objects used to check the real-time parameters of plants.

each plant has 3 sensors:
- The hygrometer checks soil moisture
- The thermometer checks the room temperature
- The photometer checks the cumulative count of daily sun exposure in hours

Each plant has 1 instance of each sensor, so their readings can be called from each plant individually using `plant.readSensor(String sensorkey)`.

#### Sensor adapter
The `LegacySensor` class represents a thermometer not specifically designed to be used with smartplantapp.

The `SensorAdapter` class `readValue()` method uses the legacy sensor's specific call to read the value.

> In the final app, the legcy sensor would be recognized automatically, but due to this being a prototype it has to be selected manually from the `Plant.connectSensors()` method's code.

### Care Handlers
Care Handler is an interface that defines methods to automatically care for plants, based on their needs and their sensors readings.

Handlers can also implement a chain of responsibilities using the `setNextStep(CareHandler nextStep)`.

#### Temperature and Light handlers
Temperature and light handlers check the difference between temperature and light needs and sensors redings; if requirements are not met, the app notifies the user to move the plant in a different location.

#### Watering handler
The watering handler class `careForPlant(Plant plant)` method checks the hygrometer and the plant's `lastFertilized` parameter to know if watering and fertilization are needed.
- If only watering is needed, the handler uses the `Water` strategy to give only water to the plant
- If water and fertilizer are needed, the handler checks the plant's category and chooses the correct fertilizer type to use, then water the plant using the correct `WaterAndFertilizer` strategy.

### Miscellaneous
The `Misc` class contains methods for general purpose.

At the moment the only method is `sanitize` which, given a string, removes all prohibited characters, removes spaces at the beginning and at the end and replaces internal spaces with "_".

In future developements this class will be used to add methods that aren't strictly tied to specific classes, helping to reduce boilerplate.

### Logger
For logging, here's an explanation on how it is configured:

The global logger is retrieved in all the classes that use it:
```java
private static final Logger logger = Logger.getLogger("");
```

In the `main` class, a new .log file is created:
```java
private static final Path loggingPath = Paths.get("smartplantapp", "src", "main", "resources", "logs", "logs.log");
```

Finally, in the `main()` method, the global logger is configured:
```java
// Allow logger to manage all log levels
logger.setLevel(Level.ALL);

// Remove existing handlers
for (Handler handler : logger.getHandlers()) {
    logger.removeHandler(handler);
}

// setup console logger for all levels
ConsoleHandler consoleHandler = new ConsoleHandler();
consoleHandler.setLevel(Level.ALL);
consoleHandler.setFormatter(new SimpleFormatter());
logger.addHandler(consoleHandler);

// setup file logger for severe logs
try {
  FileHandler fileHandler = new FileHandler(loggingPath.toString(), 10000, 5, true);
  fileHandler.setLevel(Level.WARNING);
  fileHandler.setFormatter(new SimpleFormatter());
  logger.addHandler(fileHandler);
} catch (IOException | SecurityException e) {
    System.err.println(e.getMessage());
}
```
> This implementation makes sure that all the logging levels are logged in the terminal, while levels WARNING and SEVERE are logged in the .log file specified in the `loggingPath`.



#
## Creating locations
Locations are objects that can store plants and other rooms as childrens; thanks to the `sunExposure` parameter, they are useful for choosing the right place to put your plants.

Before creating locations, you need to instantiate the `IndoorCreator()` and the `OutdoorCreator()`; the first one allows to create rooms, while the second is used to create gardens or balconies.
```java
IndoorCreator indoor = new IndoorCreator();
OutdoorCreator outdoor  = new OutdoorCreator();
```
Once instantiated, you can use them to create actual locations:
```java
Location living = indoor.createLocation("living room", 6);
Location balcony = outdoor.createLocation("balcony", 6);
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
