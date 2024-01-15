# Movie App
[![en](https://img.shields.io/badge/lang-en-red.svg)](https://github.com/kmdzinarishvili/finalProj/blob/georgianReadme/README.md)
[![pt-br](https://img.shields.io/badge/lang-ka-green.svg)](https://github.com/kmdzinarishvili/finalProj/blob/georgianReadme/README.ka.md)
## Purpose

The Movie App aims to provide users with a seamless experience to explore and search through a curated list of movies.
Users can view detailed information, including descriptions and ratings, for each movie.
Additionally, the app allows users to mark movies as favorites, view their favorite movies, and download the list to a CSV file for offline access.

## How to Use

1. **Explore Movies:**
   - The user can navigate through the list of movies using the intuitive interface.

2. **Search Functionality:**
   - Use the search feature to find specific movies based on titles or genres.

3. **Movie Details:**
   - Tap on a movie to view detailed information, including the description and rating.

4. **Favorite Movies:**
   - Mark movies as favorites by tapping the heart icon.
   - Access the Favorites section to view a personalized list of favorite movies.

5. **Download to CSV:**
   - Save the list of favorite movies to a CSV file for offline access.

## Implementation Details

1. **Movie Data Retrieval:**
   - The app uses Retrofit to fetch movie data from a remote API.
   - Moshi is used for JSON parsing.
   - Glide is used for loading and caching images in the user interface.

2. **Offline Storage:**
   - Favorite movies are stored locally using the Room database.

3. **Downloads:**
   - Work Manager is used to download the CSV file of favorite movies. 

4. **User Navigation:**
   - Navigation between the splash page, container fragment and details page is handled by the Navigation Component.
   - ViewPager2 is used to swipe between the Home and Favorites fragments. 

5. **Dependency Injection:**
   - Koin is leveraged for dependency injection, ensuring a modular architecture.
   
6. **API Call Interception and Logging:**
   - OkHttp3 is used for intercepting and logging API calls, providing insights into network requests.
     
7. **Broadcast Receiver:**
   - A Broadcast Receiver is implemented to capture the download action and inform the user about the success.


## Screenshots

***Splash***

<img src="https://github.com/kmdzinarishvili/finalProj/assets/55915632/50c8b175-44b3-48e3-9c0c-4cabebb20564" alt="splash page screenshot" width="200"/>

***Home***

<img src="https://github.com/kmdzinarishvili/finalProj/assets/55915632/2d157886-c441-4859-9ae2-0fc5d3da4763" alt="home page screenshot" width="200"/>

***Favorites***

<img src="https://github.com/kmdzinarishvili/finalProj/assets/55915632/3ba7d601-b3c7-47c4-9afd-42452bcac476" alt="favorites page screenshot" width="200"/>

***Search***

<img src="https://github.com/kmdzinarishvili/finalProj/assets/55915632/85400d13-3719-4c5c-940b-819f0d8dc401" alt="favorites page screenshot" width="200"/>
