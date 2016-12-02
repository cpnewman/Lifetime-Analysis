# Lifetime-Analysis
Exercise in both learning MVC implementation in Java and using the FreeFlyer API. This program inputs satellite TLEs and parameters, then runs the FreeFlyer API to estimate a coefficient of drag, then propagates the satellite to re-entry and returns the date.

The API launches FreeFlyer (required to run), which runs the MissionPlan script that performs the astrodynamics.

This includes the MissionPlan, and a sample historical set of TLEs for BeeSat 3, a cubesat.

BeeSat: https://amsat-uk.org/tag/beesat-3/
