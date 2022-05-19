# Recipe Spaces
Recipe Spaces is a web-based interface that provides insight into the effect of changes in ingredients to optimize products.

## Description
With the web application recipes or ingredients can be searched.
The web application provides besides the search a network of recipes based on ingredients and their amount in certain recipes.
For example, with flour, butter, sugar, and eggs can multiple recipes be made. So this network shows how close recipes lie to each other based on the different amounts of ingredients.
So if you add more butter than the recipes changes to another recipe and this relationship is shown in the network, which is a visualization created with manifold machine learning methods.
It also provides information on replacing animal-based ingredients with other ingredients based on the flavors of molecules.

### Background
The web application is supported by a database containing recipes. These recipes contain ingredients and steps. An ingredient contains molecules and each molecule has different flavor profiles.
The main references for this web application are the [RecipeNLG paper](https://aclanthology.org/2020.inlg-1.4.pdf) and its [dataset](https://recipenlg.cs.put.poznan.pl/#read).  
A provided script by Fenna Feenstra to fill recipe database from the dataset above is based on the following [script](https://github.com/BehzadBarati/Ingredient-Maps/blob/main/Food_Recipes_RecipeNLG.ipynb).

## Requirements
* JDK16

## Getting Started
* Clone this project 
```{}
git clone https://github.com/JurrienDeJong/RecipeSpaces.git
```
* Open the project in IntelliJ, it will automatically build the `build.gradle`
* Change the path in `src/main/resources/application.properties` to your path where the database is stored
* Open `src/main/java.nl.bioinf.recipespaces/RecipeSpacesApplication.java`
* Next run the application
* Open `http://localhost:8080/home` in any browser


## Usage
On the home page, you can search for a recipe or ingredient by clicking an option (recipe or ingredient). Then search for any recipe or ingredient.
When you click search, it will give information about a recipe or ingredient.
The recipe page will give the ingredients and the steps.  
The ingredient page will give the molecules and recipe containing the ingredient.  
For both the options, you can click on any link (in blue) and it redirects to its page. For example, in recipes, you can click on the ingredients and in ingredients, you can click on molecules.

To send an email using the contact page, please enter your Gmail and password in `src/main/resources/application.properties`.
```{}
# replace the *** in the properties file like:
spring.mail.username=***@gmail.com
spring.mail.password=***
```

## Authors & acknowledgment
Jorick Baron, Jurriën de Jong and Rose Hazenberg  
Bioinformatics at Hanze University of Applied Science

We would like to thank Fenna Feenstra for the database and associated data. Also, for giving more information about the data and database.
We would like to thank Michiel Noback for helping us with issues. And we would like to thank Tsjerk Wassenaar for supervising this project.

## Support
For support, please email one of the authors:  
[Jorick Baron](j.baron@st.hanze.nl)   
[Jurriën de Jong](ju.de.jong@st.hanze.nl)    
[Rose Hazenberg](c.r.hazenberg@st.hanze.nl)

For more information about spring boot, in which the web application is made, please visit [JetBrains](https://www.jetbrains.com/help/idea/spring-boot.html#spring-boot-endpoints)

## License
Copyright 2022. All right reserved. [License](LICENSE.md)

## Project status
This project is currently still in development, so it doesn't provide all functionality as described in the description.