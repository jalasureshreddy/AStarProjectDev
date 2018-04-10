# AStarProjectDev
Coded using Java, using TDD(Test-driven development) and appropriate OO(Object oriented) patterns.
<h2>A* Algorithm </h2>
The A* algorithm is a path finding algorithm that calculates the cost to walk from a start of map to the end. The algorithm uses a cost metric, the difficulty that is needed to reach the tile, and a heuristic, in this case the Manhattan distance 
(|x1 - x2| + |y1 - y2|), to find the cheapest path to traverse the map. The algorithm ensures that tiles are not checked multiple times or that it circles back.

The git repository contains a file called 'output.txt'. This file contains the path walked by the algorithm that has been implemented here on the map in resources/src/large_map.txt.

To run the algorithm, please first build the contents of the repository by running `ant` in the root of the directory pulled from git. The algorithm can then be run from the root directory as `java -jar dist/AstarProject.jar <full path to map input file>`.