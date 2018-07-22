# Collision-verifier
Application to verify collisions between geometric figures in 2D using QuadTree. 
App is written in java and implements QuadTree data structure. QuadTree is the tree in which each node has 4 children. As an input program receives dimension of 2D plan. 
It supports 4 main functionalities :
1) Insertion in QuadTree. Ex: 11 figure_type ID coordonates
2) Delete from QuadTree. Ex: 22 ID
3) Detect collision if point gives as a parameter is not in collision with figures from tree. Ex : 33 x y
4) Detect if exists collision between the figure given as a parameter and figures from tree. Ex : 44 coordonates

Application is able to analize collisions between circles, rectangles, diamonds and triangles.

In case of rectangle coordonates should be given in form : x1 y1 x2 y2
ID in this case is 1

In case of triangle coordonates should be given in form : x1 y1 x2 y2 x3 y3
ID in this case is 2

In case of circle coordonates should be given in form : Radius x y 
ID in this case is 3

In case of diamond coordonates should be given in form : x1 y1 x2 y2 x3 y3 x4 y4
ID in this case is 4

Test file is attached(quadtree.in).

