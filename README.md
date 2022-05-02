

## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [About Project](#About-Project)
* [Instalation](#Instalation)
* [Instructions](#Instructions)

## General info
Szyfrator is a group project, created in java 15 with JavaFX and Jfoenix libraries. I’ve presented this application at "Ogólnopolska Konferencja Interdyscyplinarna „Współczesne Zastosowania Informatyki 2021”".

## Technologies
Project is created with:
* Java SDK 15
* JavaFX 11.0.2
* Jfoenix 9.0.10
        
## About Project
The idea for the project came from my sister’s friend whose parents read’s her messages directly from her monitor.
Szyfrator is a group project, the purpose of which is to encrypt the text with traditional cartographic methods.
This app was made in a group of 5, my reponsibility was to connect frontend with backend, aswell as provide main functionality of three ciphers - „przestawieniowy”, „podstawieniowy” and „Polibiusza”, aswell as implementing save/load functionality for the app.

The most difficult part in this project was to manage work for another people - and as it was my first such task i’ve learned alot about working in a group. The biggest task was to implement „Podstawieniowy” cipher -as we wanted to give the user free choice of own symbols/length, after alot of tweaks we’ve created separate table for that, to allow custom ciphering and not making that task too tidious for the user.

Future updates will protect user from in-room peepers with real-time ciphering, aswell as making mode for diary 

## Instalation
To run this app you need to add sample.App as the Main class in Run/Debug Configurations
## Instructions
1. Choose desired cipher

![image](https://user-images.githubusercontent.com/74488031/166237421-e68dcd5b-57a1-49fd-bd59-35c1a9a948c0.png)

b) Some Cipher require additional info, like symbols in Podstawieniowy Cipherc or keywords in Polibiusz/Vigener

![image](https://user-images.githubusercontent.com/74488031/166237490-9c129ed9-cbd9-4405-9ee0-f8c82fbb666b.png)

2. Press "Szyfruj" To cipher a massage/ or "Deszyfruj" to Decipher crypted message

![image](https://user-images.githubusercontent.com/74488031/166237670-6088d804-ed1f-44e1-9abc-016356301bfd.png)

3. To save or load message press correspond button, and choose proper index

![image](https://user-images.githubusercontent.com/74488031/166237874-4aed3f0d-9cdb-4333-bc41-3bb7a73877ca.png)






