# Review from Reviewer 

## Reviewer Note 
> Great work really
>
> well-formatted code along with a smoothly working app with so nice UI and transition animations, you did a great job here really. MVVM implemented correctly along with efficiently using data-binding, also navigation works great. This is one of the best projects I've reviewed.
>
> I really enjoyed reviewing your project, thank you.
>
> Congratulations 🏆


## Code Quality 
✅ Correctly use ViewModel and LiveData lifecycle classes in an Android app
> Well-done implementing activity-scoped shared ViewModel between fragments,these fragments can share a ViewModel using their activity scope to handle this communication,Shoe list fragment updates correctly upon adding new shoe item in details fragment. 👌 further resources:
> 
> [Shared ViewModel in Android: Shared between Fragments](https://blog.mindorks.com/shared-viewmodel-in-android-shared-between-fragments/)
> 
> [Share data between fragments](https://developer.android.com/topic/libraries/architecture/viewmodel#sharing)

✅ Correctly implement Single Activity architecture
> Really good, Single-activity multiple fragments approach implemented very well, few resources to discuss this approach vs multiple activities:
> 
> [A Single-Activity Android Application. Why not?!](https://medium.com/rosberryapps/a-single-activity-android-application-why-not-fa2a5458a099)
> 
> [Reasons to use Android Single-Activity Architecture with Navigation Component(opens in a new tab)](https://oozou.com/blog/reasons-to-use-android-single-activity-architecture-with-navigation-component-36)
> 
> [10 best practices for moving to a single activity](https://www.youtube.com/watch?v=9O1D_Ytk0xg&ab_channel=AndroidDevelopers)

✅ Write error-free code
> app builds and runs without errors, 👏

## Layouts 
✅ Create layouts using the correct ViewGroups and Views in an Android app.
> Great,ConstraintLayout allows you to create large and complex layouts with a flat view hierarchy (no nested view groups). It's similar to RelativeLayout in that all views are laid out according to relationships between sibling views and the parent layout, but it's more flexible than RelativeLayout and easier to use with Android Studio's Layout Editor. here are a couple of resources to master ConstrainLayout:
>
> [ConstraintLayout Tutorial for Android: Complex Layouts](https://www.kodeco.com/9475-constraintlayout-tutorial-for-android-complex-layouts)
>
> [5 tips to master ConstraintLayout](https://www.youtube.com/watch?v=hqEfshM5Vfw&ab_channel=AndroidDevelopers)

✅ Apply Databinding in Layouts to show the correct data to users in multiple layouts.
> All layouts use the <layout> tag..
>
> Details fragment layout uses the ShoeViewModel <data> element.
>
> Databinding is enabled correctly in app build.gradle file
>
> DataBinding used effeciently, it helps binding UI components in your layouts to data sources in your app using a declarative format rather than programmatically.
>
> [Advanced Android Data Binding by Jose Alcérreca, Google EN](https://www.youtube.com/watch?v=bhZ3riUdkIA&ab_channel=AndroidMakers)

✅ Create a multi-screened Android Application using Android widgets.

✅ List screen uses ScrollView and LinearLayout for showing a list of items and one Floating Action button for going to the detail screen. Creates a layout for the item.
> Great job using also DataBinding to inflate item view, many students use the common way of infalting and creating a View() manually. good job 👏

✅ Create a detail screen that shows two columns of labels and edit views to enter in a new item.

✅ Correctly use the and elements within the layout.
> two-way databinding implemented very-well, here are a couple of resources besides the [official guides](https://developer.android.com/topic/libraries/data-binding/two-way)
> 
> [Android MVVM LiveData Data Binding](https://www.digitalocean.com/community/tutorials/android-mvvm-livedata-data-binding)
>
> [Two-way Android Data Binding](https://medium.com/@fabioCollini/android-data-binding-f9f9d3afc761)
> 
> [Advanced Data Binding: Binding to LiveData (One- and Two-Way Binding)](https://proandroiddev.com/advanced-data-binding-binding-to-livedata-one-and-two-way-binding-dae1cd68530f)

## Navigation 
✅ Use Databinding for click listeners on a navigation screen in an Android app.

✅ Create a Logout menu to return to the Login screen.
> Really good, menu inflation and logic is handled inside the Shoe listing fragment only 👌

✅ Create a navigation file that correctly takes a user from one page to the next in an Android app
> App flow works as expected with nice transition animations upon the screen transitions, also you can utilize shared elements transitions. a couple of resources for transitions and shared element transitions with navigation component :
>
> [Custom Transition Animations with Navigation Component | Android Studio Tutorial](https://www.youtube.com/watch?v=lejBUeOSnf8&ab_channel=Stevdza-San)
>
> [Transitions in Android Navigation Component](https://medium.com/@sergiobelda/shared-elements-in-android-navigation-architecture-component-bc5e7922ecdf)
