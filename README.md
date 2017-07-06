# README

## How to install ?

Clone the repo

```
git clone git@github.com:BenCoDev/kolor_lines.git
```

Compile the .java files

 ```
 cd /path/to/repo
 mkdir production
 cd src
 javac controller/KolorLinesGUI.java -d ../production  # if you want to play with the GUI 
 javac controller/KolorLinesCLI.java -d ../production  # if you want to play with the CLI
 cd ..
 cp -R src/view/assets production/view/
 ```

## How to play ?

Go over the install steps and run the entry point you want to play to.

For the CLI:

 ```
 cd /path/to/repo/production
 java controller.KolorLinesCLI 
 ```

For the GUI:

 ```
 cd /path/to/repo/production
 java controller.KolorLinesGUI 
 ```

## How to run the tests ?

[TBD] I am currently using IntelliJ


## How to contribute ?

No guidelines defined, feel free to submit PR :) 
