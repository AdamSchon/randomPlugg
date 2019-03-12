import random
import numpy as np

class Plant:
    def __init__(self):
        self.growingTime = 0
        self.timeSinceWater = 0
        self.alive = False

    def step(self):
        if (self.alive):
            self.growingTime += 1
            self.timeSinceWater += 1
            if (self.timeSinceWater > 4):
                self.alive = False
                self.growingTime = 0
                self.timeSinceWater = 0

    def water(self):
        if (self.timeSinceWater < 3):
            self.alive = False
        self.timeSinceWater = 0

    def harvest(self):
        if ((self.growingTime > 5) & self.alive):
            return True
        else:
            return False

    def putSeed(self):
        if (self.alive == False):
            self.alive = True
            self.timeSinceWater = 0
            self.growingTime = 0

    def look(self):
        return("Plant is " +  str(self.alive) + ", timeSinceWater = " + str(self.timeSinceWater) + ". Growth = " + str(self.growingTime))

class Position:
    def __init__(self):
        self.plant1 = Plant()
        self.plant2 = Plant()
        self.plant3 = Plant()
        self.points = 0

    def water(self, n):
        if (n == 1):
            self.plant1.water()
            print("Watering plant1: " + str(self.plant1))
        elif (n == 2):
            self.plant2.water()
            print("Watering plant2: " + str(self.plant2))
        elif (n == 3):
            self.plant3.water()
            print("Watering plant3: " + str(self.plant3))
        else:
            print("Unavailable plant to water")

    def plant(self, n):
        if (n == 1):
            self.plant1.putSeed()
            print("Planting seed1: " + str(self.plant1))
        elif (n == 2):
            self.plant2.putSeed()
            print("Planting seed2: " + str(self.plant2))
        elif (n == 3):
            self.plant3.putSeed()
            print("Planting seed2: " + str(self.plant3))
        else:
            print("Unavailable planting")

    def wait(self):
        print("Doing nothing")

    def look(self):
        print(self.plant1.look())
        print(self.plant2.look())
        print(self.plant3.look())

    def harvest(self, n):
        if (n == 1):
            if (self.plant1.harvest()):
                self.points += 5
            else:
                print("Too early to harvest!")
                self.plant1.alive = False
        elif (n == 2):
            if (self.plant2.harvest()):
                self.points += 5
            else:
                print("Too early to harvest!")
                self.plant2.alive = False
        elif (n == 3):
            if (self.plant3.harvest()):
                self.points += 5
            else:
                print("Too early to harvest!")
                self.plant3.alive = False

    def getState(self):
        return np.array([plant1.growingTime,plant1.timeSinceWater,plant2.growingTime,plant2.timeSinceWater,plant3.growingTime,plant3.timeSinceWater])

##if __name__ == '__main__':
print("Welcome to the game! Let's do this.")
pos = Position()
turns = 0
while (turns < 10):
    ##print("Currently it's turn " + str(turns) + ". You have " + str(pos.points) + " points.")
    ##answer = input("What do you wish to do? Look(0), Water(1), Plant(2), Wait(3), Harvest(4)?")

    answer = random.randint(1,4)
    if (answer == 0):
        pos.look()
    else:
        if (answer == 1):
            pos.water()
        if (answer == 2):
            pos.plant()
        if (answer == 3):
            pos.wait()
        if (answer == 4):
            pos.harvest()
        pos.plant1.step()
        turns+=1
        print("Game over! You scored " + str(pos.points) + " points.")
