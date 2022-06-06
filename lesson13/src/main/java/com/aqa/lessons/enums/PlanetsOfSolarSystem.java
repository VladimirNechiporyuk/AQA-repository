package com.aqa.lessons.enums;

public enum PlanetsOfSolarSystem {
    MERCURY(0, 57910006.0, 2439.7, null, 0.0, "VENUS"),
    VENUS(1, 6051.8, MERCURY, 50289989.0, "EARTH"),
    EARTH(2, 149597870.7, 6371.0, VENUS, 41399956.0, "MARS"),
    MARS(3, 3389.5, EARTH, 78339969.0, "JUPITER"),
    JUPITER(4, 69911, MARS, 550390337.0, "SATURN"),
    SATURN(5, 58232, JUPITER, 651069771.0, "URANUS"),
    URANUS(6, 25362.0, SATURN, 1441589200.0, "NEPTUNE"),
    NEPTUNE(7, 24622.0, URANUS, 1633310351.0);

    int serialNumber;
    double howFarFromTheSun; // in km
    double radius; // in km^2
    PlanetsOfSolarSystem previousPlanet;

    //    source for getting length between planets https://kvn201.com.ua/distances-of-the-planets-from-the-sun.htm
//    for example: 108 199 995 - 57 910 006 = 50 289 989
    double howFarFromThePreviousPlanet; // in km
    String nextPlanet;

    PlanetsOfSolarSystem(int serialNumber, double radius, PlanetsOfSolarSystem previousPlanet, double howFarFromThePreviousPlanet, String nextPlanet) {
        this.serialNumber = serialNumber;
        this.radius = radius;
        this.previousPlanet = previousPlanet;
        this.howFarFromThePreviousPlanet = howFarFromThePreviousPlanet;
        this.nextPlanet = nextPlanet;
        howFarFromTheSun = calculateHowPlanetFarFromTheSun(previousPlanet.howFarFromTheSun, previousPlanet.radius, howFarFromThePreviousPlanet);
    }

    //    only for the first planet
    PlanetsOfSolarSystem(int serialNumber, double howFarFromTheSun, double radius, PlanetsOfSolarSystem previousPlanet, double howFarFromThePreviousPlanet, String nextPlanet) {
        this.serialNumber = serialNumber;
        this.howFarFromTheSun = howFarFromTheSun;
        this.radius = radius;
        this.previousPlanet = previousPlanet;
        this.howFarFromThePreviousPlanet = howFarFromThePreviousPlanet;
        this.nextPlanet = nextPlanet;
    }

    //    only for the last planet
    PlanetsOfSolarSystem(int serialNumber, double radius, PlanetsOfSolarSystem previousPlanet, double howFarFromThePreviousPlanet) {
        this.serialNumber = serialNumber;
        this.radius = radius;
        this.previousPlanet = previousPlanet;
        this.howFarFromThePreviousPlanet = howFarFromThePreviousPlanet;
        howFarFromTheSun = calculateHowPlanetFarFromTheSun(previousPlanet.howFarFromTheSun, previousPlanet.radius, howFarFromThePreviousPlanet);
    }

    @Override
    public String toString() {
        if (previousPlanet == null) {
            return "PlanetsOfSolarSystem{" +
                    "serialNumber=" + serialNumber +
                    ", howFarFromTheSun=" + howFarFromTheSun +
                    ", radius=" + radius +
                    ", previousPlanet=" + "no planet" +
                    ", howFarFromThePreviousPlanet=" + howFarFromThePreviousPlanet +
                    ", nextPlanet='" + nextPlanet + '\'' +
                    '}';
        } else if (nextPlanet == null) {
            return "PlanetsOfSolarSystem{" +
                    "serialNumber=" + serialNumber +
                    ", howFarFromTheSun=" + howFarFromTheSun +
                    ", radius=" + radius +
                    ", previousPlanet=" + previousPlanet.name() +
                    ", howFarFromThePreviousPlanet=" + howFarFromThePreviousPlanet +
                    ", nextPlanet='" + "no planet" +
                    '}';
        }
        return "PlanetsOfSolarSystem{" +
                "serialNumber=" + serialNumber +
                ", howFarFromTheSun=" + howFarFromTheSun +
                ", radius=" + radius +
                ", previousPlanet=" + previousPlanet.name() +
                ", howFarFromThePreviousPlanet=" + howFarFromThePreviousPlanet +
                ", nextPlanet='" + nextPlanet + '\'' +
                '}';
    }

    private double calculateHowPlanetFarFromTheSun(double howPreviousPlanetFarFromTheSun, double previousPlanetRadius, double howFarFromThePreviousPlanet) {
        return howPreviousPlanetFarFromTheSun + previousPlanetRadius + howFarFromThePreviousPlanet;
    }
}
