# MovieRecommender
**Video Demo:** 
---------------------------------
<https://youtu.be/6PpNtiVvfMw>

**Description:**
------------------------------------
A JAVA program that recommends movies for individual user sessions. The program only needs the two source files explained below. First a ORM(object relational mapping) layer is created that read each row from the text files and translates them into object which has attributes and methods. The objects are put into a list and an algorithm to solve the problem is created. The output i written back into the usersession files and the output is user specific.


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