<div align="center">
  <br>
  <h1>Product Management 🍔🌭🥔🍠</h1>
</div>
<br>

## Background

A store needs a Product information management program. With basic requirements such as creating a Product, displaying the Product information, and updating information, .... The Product information is stored in a text or binary file (Product.dat).❤️

## Table of Contents
- [Table of Contents](#table-of-contents)
- [Background](#Background)
- [Program Specifications](#program-specification)
- [Features](#Features)
    - [Function 1: Create a Product](#function1)
    - [Function 2: Check to exist Product](#function2)
    - [Function 3: Search Product information by name](#function3)
    - [Function 4: Update Product](#function4)
       - [Function 4.1: Update Product information](#function4-1)
       - [Function 4.2: Delete Product information](#function4-2)
    - [Function 5: Save to file](#function5)
    - [Function 6: Print all lists from file](#function6)
    - [Function 7: Create a layout](#function7)
## Program Specifications
<pre>
Build a Product management program. With the following basic functions:
	1. Print
	2. Create a Product
	3. Check exits Product.
	4. Search Product’ information by Name
	5. Update Product:
		5.1. Update Product.
		5.2. Delete Product.
	6. Save Products to file.
	7. Print list Products from the file.
	Others- Quit.
 </pre>
## Features
This system contains the following functions:
<pre> 
<strong>▪ Function 1: Create a Product </strong>
	o The user is required to input a piece of Product information including ProductID, ProductName,
	UnitPrice, Quantity, Status
	o The application should check the valid data with the following conditions:
		- A Product Name field must be at least five characters and no spaces.
		- A Product Name field is not allowed to duplicate.
		- A UnitPrice field is a real number and ranges from 0 to 10000
		- A Quantity field is an integer number and ranges from 0 to 1000
		- A Status field is a string and has values: Available or Not Available
	o After that, the system creates new a Product.
	o The application asks user to go back to the main menu.
<strong>▪ Function 2: Check to exist Product</strong>
	o The system will check the Product name that is stored in the Product.dat file.
	o A message “Exist Product” should be displayed in the case the ProductName exists in the
	Product.dat file.
	o Otherwise, the message “No Product Found!” will be displayed.
	o The application asks user to go back to the main menu.
<strong>▪ Function 3: Search Product information by name </strong>
	o The user is required to enter a search string (a part of product name). Then, the application
	should return a list of Product information containing the search string.
	o If the list Product is null, the notification "Have no any Product" will be shown. Otherwise, the
	application will print the list Product information that is ordered by the Product Name.
	o The application asks user to go back to the main menu.
<strong>▪ Function 4: Update Product</strong>
	<strong>▪ Function 4.1: Update Product information</strong>
		o The user enters the ProductID from the keyboard.
		o If it does not exist, the notification "Productname does not exist" will be shown. Otherwise, the
		Product can be edited the remaining information. If the inputted information is blank, old
		information will not be changed.
		o The result of the update should be shown as success or failure status.
		o The application asks user to go back to the main menu.
	<strong>▪ Function 4.2: Delete Product information </strong>
		o The user enters the ProductID from the keyboard
		o If it does not exist, the notification "Productname does not exist" will be shown. Otherwise, the
		Product can be deleted
		o The result of the delete action should be shown as success or fail status.
		o The application asks user to go back to the main menu.
	<strong>▪ Function 5: Save to file </strong>
		o The application allows the user write a list of the Product’s information to the Product.dat file.
		o The application asks user to go back to the main menu.
<strong>▪ Function 6: Print all lists from file</strong>
	o The application can allow to load Product information list from the file into the Collection.
	o The application shows the list of Product information order by Quantity descending. If the
	products have same quantity, the list will be sorted with the ascending UnitPrice field.
	o The application asks user to go back to the main menu.
<strong>▪ Function 7: Create a layout </strong>
	o The program is organized in the form of a function menu.
</pre>
  <p align="center">
  <img alt="Cat" width="250px" src="https://github.com/caocong2404/caocong2404/blob/main/cat-coding.png"/>
  <br>
  <strong>Happy Coding</strong> ❤️
</p>
