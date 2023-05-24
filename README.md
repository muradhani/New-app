News Application
This News Application is designed to fetch news from the News API using Retrofit and OkHttp. The app is built using the MVVM architecture and utilizes Room database to cache the news data retrieved from the API.

Features
Fetches news articles from the News API.
Displays a list of news articles with their titles, descriptions, and sources.
Allows users to read the full article by tapping on a news item.
Implements pagination to load more news articles as the user scrolls.
Caches the fetched news articles using Room database for offline access.
Supports filtering news articles by category or source.
Technologies and Libraries Used
Kotlin
Retrofit: For making API requests and handling network operations.
OkHttp: For handling HTTP requests and responses.
Room: For local data caching and offline access.
LiveData: For observing and updating the UI in response to data changes.
ViewModel: For managing and maintaining UI-related data.
RecyclerView: For displaying the list of news articles.
Navigation Component: For handling navigation between different screens.
Coroutines: For asynchronous programming and handling background tasks.
Glide: For loading and displaying images.
Material Components: For implementing a modern and user-friendly UI.
![Image Alt Text](images\Screenshot_20230524_130612.png)
![Image Alt Text](images\Screenshot_20230524_130645.png)
![Image Alt Text](images\Screenshot_20230524_130700.png)
![Image Alt Text](images\Screenshot_20230524_130713.png)
