Socket Server Demo
==================

_We'll just write on a socket!_

This is a simple Java TCP socket server.  Just like you'd find in many Java tutorials. It also has a mini-framework for plugging in your own SocketHandler class. 

It uses a Thread-Per-Request model and blocking I/O (not web-scale).


Requirements
------------

This project uses [Apache Ant](http://ant.apache.com) to build, because I hadn't written an ant build file in years and was feeling nostolgic.


Testing
-------

In a console, run 

    ant

This will compile sources and launch the server. You then will see the following:

    [java] Apr 20, 2013 7:03:13 AM example.Log info
    [java] INFO: Waiting for connections on port 7000 for 15000 milliseconds (Ctrl-C to exit)

In another console, run

    telnet localhost 7000

and type a line of text.

You can also send multiple requests to the server in parallel with this one liner:

    echo 'A,B,C,D' | xargs -d, -i -P 4 sh -c  'echo "{}" | nc localhost 7000'

The resulting console output will be jumbled, but if you look at the server-side logging, you'll see that it works as expected.

# License

Creative Commons CC0 1.0 Universal 

See: http://creativecommons.org/publicdomain/zero/1.0/legalcode

Noah Zucker (nzucker@gmail.com / [@noahlz](http://twitter.com/noahlz))

