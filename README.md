Simple Food Delivery App

This is a small Android app I made to practice layouts, RecyclerView, passing data between screens, and basic cart management. The idea is just a simple food ordering flow where you can browse items, open the details page, add them to a cart, and see the total.

The app is not connected to any backend. Everything runs locally using simple classes and a TinyDB helper for saving cart items.

What the app does

Shows a list of food items with images, names, and prices

Lets you open a detail screen for each item

Allows adding items to the cart

Cart screen shows all added items, total price, and lets you increase or decrease quantities

Checkout button clears the cart and shows a simple confirmation message

This project is mainly for learning purposes.

How to run it

Clone the project

Open it in Android Studio

Let Gradle sync

Run the app on an emulator or your phone

There are no special instructions or setup steps. Everything should build without any extra configuration.

Project structure (simple overview)

app/src/main/java/com/example/food_app_23

Activities for home, details, and cart

Adapters for RecyclerViews

Helper folder with the TinyDB and cart manager

Domain classes for the food item data

app/src/main/res

Layout XML files

Drawable images used for the food items

Colors, themes, etc.

Why I made this

I built this to practice basic Android concepts like RecyclerView, passing objects through intents, card layouts, and a simple cart system. It’s a small project, not a production app, but it helped me understand UI flow and data handling better.

Notes

The app currently uses hardcoded food data. There is no real authentication, API, or backend. All totals and cart data are calculated locally.

Author : Goutham Krishna P
