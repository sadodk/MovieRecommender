# MovieRecommender
**Video Demo:** 
---------------------------------
<https://youtu.be/6PpNtiVvfMw>

**Background:**
------------------------------------
Initially i wanted to create a program with JAVA, as its a language which is used for many enterprise webapplication. So i used a couple of weeks to teach myself java language and then thought of building a program that would recommend movies to a user (similar to what we see in Netflix). To do this I found som source files i could use; one with a list of movies and corresponding attributes and with info of each usersession. The info form each usersessions initially contained userID's and the movie the user was currently viewing.

I wanted the movie recommendations to be unique based on the genre of the movie the user was viewing. I also wanted to list the 3 most popular movies to the user, popularity should be based on IMDB user ratings. I wanted all this to be a backend recommender system that extracted from files and outputted into files. 

First i thought i would read from the data files by importing a SQLite package into my JAVAprogram. But then i thought it would not be a optimised solution working with query string in my java program. So i used some days researching different methods of how to read from text files into java and i found that i could turn the rows in the files into objects with attributes. The object definitions would be in classes and within these classes there would be object specific methods. So I created the object types as classes and then started to build the algorithm which was going to produce the wanted output. 

Finally i wanted the output to be place back into the user session file, which was I reading from, as both the input and the output results was user session specific. However writing output to existing user session files should not deleted the input data. So in order to get the read and write to work on the same file i created a clean output file method which first could read from the file, then truncate the file and lastly populate the file with both input and output.

The usersession file would then contain both the input:  Information of userID and what movie the user was viewing. 
and the output: Top3 popular movies based on user ratings and recommend movies with similar genre as the movie the user is viewing.

**Description:**
------------------------------------
This JAVA program that recommends movies for individual user sessions. The program only needs the two source files explained below. First a ORM(object relational mapping) layer is created that read each row from the text files and translates them into object which has attributes and methods. The objects are put into a list and an algorithm to solve the problem is created. The output i written back into the usersession files and the output is user specific.


**Source files**
-------------------------------------

| *Products.txt* contains |
| ----------- |
| productid | 
| year | 
| keyword 1 |
| keyword 2 |
| keyword 3 |
| keyword 4 |
| keyword 5 |
| rating |
| price |


| *CurrentUserSessions.txt* contains |
| ----------- |
| userid | 
| productid currently being viewed | 
| popular* (productid's seperated by ;) |
| recommend* (productid's seperated by ;) |

/* Program output