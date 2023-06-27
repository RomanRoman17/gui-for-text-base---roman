# make a program that uses no libraries and only uses the standard library
# the program should do 2-d k means clustering using x, y ,z values
# makes 4 cluters centroids using the average of the points
# the program should print out each point and what cluster it belongs to and how many points belong to each cluster
# the program should print out the average x, y, z values for each cluster

import sys
import math

# read in the data
data = []
for line in sys.stdin:
    line = line.strip()
    data.append(line.split())

# convert the data to floats
for i in range(len(data)):
    for j in range(len(data[i])):
        data[i][j] = float(data[i][j])

# make the centroids
centroids = []
for i in range(4):
    centroids.append([0, 0, 0])

# make the clusters
clusters = []
for i in range(4):
    clusters.append([])

# make the clusters
for i in range(len(data)):
    # find the closest centroid
    closest = 0
    distance = math.sqrt((data[i][0] - centroids[0][0])**2 + (data[i][1] - centroids[0][1])**2 + (data[i][2] - centroids[0][2])**2)
    for j in range(1, len(centroids)):
        temp_distance = math.sqrt((data[i][0] - centroids[j][0])**2 + (data[i][1] - centroids[j][1])**2 + (data[i][2] - centroids[j][2])**2)
        if temp_distance < distance:
            distance = temp_distance
            closest = j
    # add the point to the cluster
    clusters[closest].append(data[i])
    
# find the new centroids
for i in range(len(centroids)):
    # find the average of the points in the cluster
    x = 0
    y = 0
    z = 0
    for j in range(len(clusters[i])):
        x += clusters[i][j][0]
        y += clusters[i][j][1]
        z += clusters[i][j][2]
    x /= len(clusters[i])
    y /= len(clusters[i])
    z /= len(clusters[i])
    centroids[i] = [x, y, z]

# print the clusters
for i in range(len(clusters)):
    print("Cluster", i)
    for j in range(len(clusters[i])):
        print(clusters[i][j])
    print("Centroid:", centroids[i])
    print("Number of points:", len(clusters[i]))
    print()

# print the average of the clusters
x = 0
y = 0
z = 0
for i in range(len(centroids)):
    x += centroids[i][0]
    y += centroids[i][1]
    z += centroids[i][2]
x /= len(centroids)
y /= len(centroids)
z /= len(centroids)
print("Average of the clusters:", x, y, z)

# print the average of the points
x = 0
y = 0
z = 0
for i in range(len(data)):
    x += data[i][0]
    y += data[i][1]
    z += data[i][2]
x /= len(data)
y /= len(data)
z /= len(data)
print("Average of the points:", x, y, z)

