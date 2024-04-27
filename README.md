# DEMO-Financial-Trade System
## This is a simple web application coded in spring-boot backend and angular for the front-end.

The code for each section of the app is held in separate packages: 01-backend, 02-frontend, and 03-dockerfiles -> running or testing.

## Getting Started

Prerequisites
Java 17 or higher,
AngularCLI v12,
Maven,
Docker

## Running & Instalation

Clone the repository:
git clone https://github.com/Sstefu/financial-trade-demo.git

open terminal in 03-dockerfiles/docker-compose;

1. build the docker container: docker-compose up --build (this will build and start up the mysql container and spring-boot application)

2. open the terminal in 02-frontend/financial-trade, and type the following commands:

=> a. npm install: wait for all the node dependencies to get installed;

=> b. ng serve (this will start the development environment for angular and access: http://localhost:4200/

3. Play around and test.



