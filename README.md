# RestaurantDemoApp 
This app folows a MVP architecture pattern. This android app retrieves a set of restaurants from a json file stored in the assets folder and visualize them using a recycler view(as shown in screenshots at the end of page). One can sort the restaurant list based on it’s current openings state, you can favourite a restaurant and you can select a sort value to further sort the list. You can also search for restaurant word by word using a search bar provided.

Basic things considered while making this app:

1. Favourites: Favourite restaurants are at the top of the list, your current favourite restaurants are stored locally on the phone.
2. Openings state: Restaurant is either open (top), you can order ahead (middle) or a restaurant is currently closed (bottom).
3. Sort options: Always one sort option is chosen and this can be best match, newest, rating average, distance, popularity, average product price, delivery costs or the minimum cost.
4. Filtering: It’s up to you how you how you want to search by restaurant name.

### PROJECT DETAILS
**App Name**: Restaurants App

**Tools & Languages Used**
1. Android Studio 3.5.2
2. Java Programming

Dependenices/Libraries Used:
1. GSON
2. RecyclerView
3. Room DB
4. JUnit
5. Espresso
6. Mockito

Unit Test covered:
1. Main Activity UI test
2. Favourite DAO test
3. MainView Presenter Test
4. Favourite Activity UI test


Steps to run the project
1. Download the project in your local directory or clone it whichever is feasible for you.
2. Open Android Studio-> File-> Open-> Select android directory.


"ScreenShots of App"

![alt text](https://github.com/yogeshMarutiPatil/RestaurantDemoApp/edit/master/RestaurantApp.jpg)

![alt text](https://github.com/yogeshMarutiPatil/RestaurantDemoApp/edit/master/RestaurantApp1.jpg)





