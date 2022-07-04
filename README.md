# Recipe Spaces
Recipe Spaces is a web-based interface that provides insight into the effect of changes in ingredients to optimize products.

## Description
With the web application recipes or ingredients can be searched to provide more information and to follow a recipe of interest.
It also gives you information about the difference between ingredients if a recipe has more variants. For example, guacamole has multiple variants and with the visualization, you can view the differences in ingredients of all variants of guacamole.
Another main thing is that a recipe provides information on replacing ingredients with other ingredients based on the molecule's flavor profile.  

'Recipe Spaces' is implemented as the network of ingredients, which is a multidimensional scaling plot with distances between ingredients, so you can find clusters and closely related ingredients.

### Background
This web application is supported by a database containing recipes. These recipes contain ingredients and steps. An ingredient contains molecules and each molecule has different flavor profiles.
The main references for this web application are the [RecipeNLG paper](https://aclanthology.org/2020.inlg-1.4.pdf) and its [dataset](https://recipenlg.cs.put.poznan.pl/#read).  
A provided script by Fenna Feenstra to fill the recipe database from the dataset above is based on the following [script](https://github.com/BehzadBarati/Ingredient-Maps/blob/main/Food_Recipes_RecipeNLG.ipynb).

## Requirements
* JDK16
* SQLite3

## Getting Started
* Clone this project 
```{}
git clone https://github.com/JurrienDeJong/RecipeSpaces.git
```
* Open the project in any software tool you like, it will automatically build the `build.gradle`
* Change the path in `src/main/resources/application.properties` by `spring.datasource.url`to your path where the database is stored
* Open `src/main/java.nl.bioinf.recipespaces/RecipeSpacesApplication.java`
* Next run the application
* Open `http://localhost:8080/home` in any browser and explore

## Usage
On the home page, you can search for a recipe or ingredient by clicking an option (recipe or ingredient). Then search for any recipe or ingredient.
When you click search, it will give information about a recipe or ingredient. On the recipe page, you can visualize replacements of ingredients, 
the network of ingredients, histogram showing the difference in ingredients between recipes that has multiple variants, and you can convert the ingredient amount to create the recipe for fewer or more people.
The ingredient page shows you information about a particular ingredient. And so is there also a molecule's information page.  

To send an email using the contact page, please enter your Gmail and password in `src/main/resources/application.properties`.
```{}
# replace the *** in the properties file like:
spring.mail.username=***@gmail.com
spring.mail.password=***
```

## Authors & acknowledgment
Jorick Baron, Jurriën de Jong and Rose Hazenberg  
Bioinformatics at Hanze University of Applied Science

We would like to thank Fenna Feenstra for the database and associated data but also for giving more information about the data and the database.
And Michiel Noback for helping us with issues. And we also wanted to thank Tsjerk Wassenaar for supervising this project.

## Support
For support, please email one of the authors:  
[Jorick Baron](j.baron@st.hanze.nl)   
[Jurriën de Jong](ju.de.jong@st.hanze.nl)    
[Rose Hazenberg](c.r.hazenberg@st.hanze.nl)

For more information about spring boot, in which the web application is made, please visit [JetBrains](https://www.jetbrains.com/help/idea/spring-boot.html#spring-boot-endpoints)

## License
Copyright 2022. All right reserved. [License](LICENSE.md)

<img src="src/main/resources/static/images/RecipeSpaces.png" width="100" alt="recipe-spaces-logo">