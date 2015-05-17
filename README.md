Example application that uses Atmosphere with Play Framework
============================================================

Based upon [Chat example from Atmosphere repository](https://github.com/Atmosphere/atmosphere-samples/tree/master/play-samples/chat).

Main differences:

  * The Play Application is of Scala nature (PlayScala plugin instead of PlayJava)
  * the Global object falls back to standard Play Router if there is no matching Atmosphere handler
  * the Atmosphere managed handler is implemented in Scala (ScalaChat)
  * the handler does not return outgoing Message directly but uses Broadcaster
    * this is to demonstrate that the response mechanism does not need to be synchronous and the response can be sent from another thread
