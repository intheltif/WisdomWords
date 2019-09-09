# Wisdom Words

## Description
A networking application that allows a client to contact a server for
a snippet of wisdom. There are two versions of this application: UDP
and TCP.

## Usage
To run this application first compile all the java files.

Move into the udp/ or tcp/ directory and type:

```bash
javac *.java
```

Next, start the respective server with the text file containing the wise
words:
```bash
java WisdomWordsServTCP [FILE_WITH_WISDOM]
```
or
```bash
java WisdomWordsServUDP [FILE_WITH_WISDOM]
```
The server will continue to accept requests until it is killed it CTRL+C

Finally, run the client with the hostname and port as arguements:
```bash
java WisdomWordsCLTCP [HOSTNAME] [PORT]
```
or

```bash
java WisdomWordsCLUDP [HOSTNAME] [PORT]
```

## Known Issues
There are currently no known issues.