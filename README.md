# weather app

Hello! 
Here I have implemented a weather app which display the current, minimum and maximum temperature which available in the weather API.

The temperature I am displaying in Celcius. I am calculating the Celcius by substracting Kelvin value 273.15 with the temp value in API response. 

There is no special set-up required to run this application in Android Studio. 
--> Clone the repo, 
--> Open the project in Android Studio
--> Hit "Run". Done! 
The application is compatible with Android 21 (cannot be smaller than version 21 declared in the library) and onwards

The app is a client: where operations are performed by calling API endpoints over the network and local data is in effect mutable. Local data is only modified as a result of user requests.

Dependencies:
Retrofit2
RXJava

Design:
As a single screen application, I have create a home module which contains its model, view and viewmodel.
I have placed the HomeActivity in the view, HomeViewModel in the viewmodel and model objects and HomeRepository in the model packages.

Out side of the home package, there are utils and network packages.
Utils: Hold a utils page where we put some common methods
Network: Holds ApiClient & ApiInterface where I implemented the retrofit

I have created a basic unit testing for the utils methods which ensure the celcius calculation is correct.