# A simple Football app that uses MVVM and Clean architecture
the projects has two modules :
1. data module -> it provides the access to data source, either from asset file or local database
2. app -> it contains the app itself where there are all UIs and resources
   The project has 3 layers, the UI layer, the Domain layer and the Data layer.
   the data layer is inside the data module. While UI and Domain layer are in app module
   By using this app, a user can bookmark their favorite movie and search a movie based on the title the app has 3 screens :

The home screen where it contains the list of Football teams and The detail screen where it contains a detail information of a football team.
In the home screen there is a function to sort the list by name or by  value of the clubs.

this mini projet took about 5-6 hours to finish.

techstack :
1. Jetpack Compose
2. Jetpack Navigation
3. Kotlin Coroutine
4. Kotlin Flow
5. Gson
6. Glide
7. Dagger Hilt and Dagger 2
8. Room database
9. Mockk
