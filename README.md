# tdt4250_Assigment2
This is the second assigment for tdt4250 about OSGI.

Written by Henrik Knudsen and Hallvard Echtermeyer.


Model
tdt4250_Assigment2_API
 - The core interfaces and classes that will be used by converter users and implementations
tdt4250_Assigment2_CurrencyConverter
 - Precreated implementation which converts cash values
tdt4250_Assigment2_TemperatureConverter
 - Precreated implementation which converts temperature values
tdt4250_Assigment2_WeightConverter
 - Precreated implementation which converts Weight values
tdt4250_Assigment2_Servlet
 - Logic for the connection to the HTTP localhost  
tdt4250_Assigment2_Gogo
 -Added Gogo logic which lets you 
  - List
  - Add
  - Remove
  - Convert
  
Content

tdt4250_Assigment2_API
 - interfaces and classes used to convert users input into their desired output.
 
tdt4250_Assigment2_Servlet
 - Lets you connect to the Localhost on your computer, in order to interact with this servlet you need to type in http://localhost:8080/example in order to access the webservice. the format for getting a conversion is http://localhost:8080/example?c=Kilo&n=Pounds&v=32
 c = the unit of conversion you want to convert from
 n = the unit of conversion you want to convert to
 v = the value you want to convert 
 
 tdt4250_Assigment2_Gogo
 Here are the Gogo created commands which you can type into the Gogo shell in order to List, Add, Remove and Convert
 

