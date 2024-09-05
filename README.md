# blueskyACK
## NEEDED SOYWARE

- openjdk (make sure you have the java compiler installed and not only java on your system) [https://adoptium.net/]

- clojure [https://clojure.org/]

- leningen [https://leiningen.org/]

- chrome and chromedriver [https://sites.google.com/chromium.org/driver/downloads]

or automatically with the following cmd commands:
```
winget install --id=Google.Chrome --silent
winget install --id=Google.ChromeDriver --silent
winget install --id=AdoptOpenJDK.OpenJDK --silent
winget install --id=Clojure.Clojure --silent
winget install --id=Leiningen.Leiningen --silent
```
## SETUP

After correctly installing the needed soyware download the code (click on the green "<> Code" button and then download zip), once you extracted the folder edit the "message(.txt)" file, that's the message that is going to be posted over and over, after you did it create a folder "img" and inside insert any IMAGE from you soyfolder, in each upload a random image will be selected and uploaded **DO NOT PLACE VIDEOS AS IM PRETTY SURE THEY WON'T WORK AT ALL!**

## RUNNING

**BEFORE RUNNING THE PROGRAM MAKE SURE YOU ALREADY CREATED AN ACCOUNT!**

Finally to run the software double click on "startcommand(.bat)", it will start a cmd in the local directory, once its open type the following command:
```
lein run NAME PASS PPS
```
lein run is to simply start up the program, NAME is either the username or email associated with the account, PASS is the password and PPS is how many seconds to wait before making another post.

**!!! VERY LOW PPS MIGHT INCREASE THE CHANCHE OF A CRASH, IF IT CRASH JUST RAISE IT, THE RECCOMENDED PPS IS 5 !!!**

example:
```
lein run babytjak@cacasoob.com secretpassword 5
```
## TROUBLESHOOTING

contact "sooterson" at irc.soyak.party
