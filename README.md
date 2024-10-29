# MC-Booking

Booking type application. This has the following functionalities:

**LogIn or Register**

![alt text](https://user-images.githubusercontent.com/48184314/150221609-027dcc4d-0196-4935-b0f3-dc5fe60dc04b.png)

When a e-mail address is typed in the application offers suggestions by searching the phone`s contacts or the history.
The password is than verified. The authentication is simulates a network call. To avoid blocking the UI thread, the log-in request is made from an AsyncTask:
```public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {```


The ```OnPostExecute()``` method checks if authentication was succesfull and updates the UI.

![alt text](https://user-images.githubusercontent.com/48184314/150222329-107d634a-cd41-455a-8945-548527537b56.png)



**Searching for hotels**

![alt text](https://user-images.githubusercontent.com/48184314/150222329-107d634a-cd41-455a-8945-548527537b56.png)

To search for hotels, the user must enter: Location, date of check-in, check-out dates and number of people traveling.
Google-Places is used to provide the placement suggestions:

![alt text](https://user-images.githubusercontent.com/48184314/150222428-81f72087-99ab-49cd-932a-f4faf81a222f.png)

```
       mGoogleApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, this)
                .build();
        // get suggestions
        mPlaceAutocompleteAdapter = new PlaceAutocompleteAdapter(this, mGoogleApiClient, LAT_LNG_BOUNDS ,null);
        mSearchView.setAdapter(mPlaceAutocompleteAdapter);
```

The fields containing the check-in / check-out data force the user enter a valid date in DD / MM / YYYY format. 
Check-out date cannot be earlier than the check-in date.

![alt text](https://user-images.githubusercontent.com/48184314/150226274-6067f078-0f8c-4489-b5d3-a6f28a5c0517.png)

If all the information is entered correctly, the user will be presented with a list of hotels. Each item in the list has an image, 
hotel name, number of rooms available, rating and price.


![alt text](https://user-images.githubusercontent.com/48184314/150226470-983cf6bf-d19f-45ad-b9b9-181e4301762f.png)

To create the list of hotels, a generic UI for a hotel was created (```{$ Project} /res/layout/Hotel.xml```). T
he data in the application is taken over by the class ```HotelRecyclerViewAdapter.java``` and displayed in a dynamic list.

**Settings**

By taping the round button in the top-right the user can access the settings menu:

![alt text](https://user-images.githubusercontent.com/48184314/150226692-8cadf4a3-fdbf-4582-8d46-254ddbaeb1b5.png)

**Manifest File and Google Services**

The ```AndroidManifest.xml``` file holds the project`s main configuration settings:
required permissions, authentication key for use of google`s services, the theme used as a UI template, 
the ‘activities’ the user can navigate through.

```
    <!-- To auto-complete the email text field in the login form with the user's emails -->
    <uses-permission android:name="android.permission.GET_ACCOUNTS" />
    <uses-permission android:name="android.permission.READ_PROFILE" />
    <uses-permission android:name="android.permission.READ_CONTACTS" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    
    <activity
            android:name=".activity.SearchActivity"
            android:label="@string/title_activity_search"
            android:theme="@style/AppTheme.NoActionBar" />```
            
  The build.gradle file holds the project`s dependencies. To use google services API we added:
  ```
    implementation 'com.google.firebase:firebase-core:16.0.8'
    implementation 'com.google.android.gms:play-services-location:16.0.0'
    implementation 'com.google.android.gms:play-services-places:16.0.0'
    implementation 'androidx.room:room-runtime:2.1.0'```
    
  
  **Data Storage**
  
  We use **Room** (note the build.gradle above) to persist the data to a local database. 
  Room offers a ORM layer and generates the necessary queries by using a DAO class (Data Access Object).
  ```
  @Dao
public interface HotelDao {
    @Insert
    void insert(Hotel hotel);
    ```
  
  Entities are created based on a model class.  
  ```
  @Entity(foreignKeys = @ForeignKey(entity = Reservation.class,
        parentColumns = "uid",
        childColumns = "uid",
        onDelete = CASCADE))
public class Hotel {

    @PrimaryKey(autoGenerate = true)
    public int uid;
    @ColumnInfo(name = "image_id")
    public int imageId;
    ```
    
  For data to be transfered between activieties and views it needs to be serialized.
  This is done through the use of a DTO class (Data Transfel Object) that implements the ```Parcelable``` interface:
  ```
  public class HotelDto implements Parcelable { 
  ```
