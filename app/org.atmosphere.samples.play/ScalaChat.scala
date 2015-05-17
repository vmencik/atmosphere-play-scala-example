package org.atmosphere.samples.play

import java.io.IOException

import org.atmosphere.config.managed.ManagedAtmosphereHandler.Managed
import org.atmosphere.config.service.{ManagedService, Ready, Disconnect}
import org.atmosphere.cpr.{AtmosphereResource, AtmosphereResourceEvent}
import play.api.Logger

@ManagedService(path = "/chat")
class ScalaChat {

  val logger = Logger(getClass)

  /**
   * Invoked when the connection as been fully established and suspended, e.g ready for receiving messages.
   *
   * @param r
   */
  @Ready def onReady(r: AtmosphereResource) {
    logger.info(s"Browser ${r.uuid} connected.")
  }

  /**
   * Invoked when the client disconnect or when an unexpected closing of the underlying connection happens.
   *
   * @param event
   */
  @Disconnect def onDisconnect(event: AtmosphereResourceEvent) {
    if (event.isCancelled) {
      logger.info(s"Browser ${event.getResource.uuid} unexpectedly disconnected")
    }
    else if (event.isClosedByClient) {
      logger.info(s"Browser ${event.getResource.uuid} closed the connection")
    }
  }

  @org.atmosphere.config.service.Message(encoders = Array(classOf[JacksonEncoder]), decoders = Array(classOf[JacksonDecoder]))
  @throws(classOf[IOException])
  def onMessage(resource: AtmosphereResource, message: Message): Unit = {
    logger.info(s"${message.getAuthor} just send ${message.getMessage}")
    val encoded = new JacksonEncoder().encode(message)

    // the broadcast can be called from anywhere (Future callback, Akka actor)
//    resource.getBroadcaster.broadcast(new Managed(encoded))
    resource.getBroadcaster.broadcast(encoded)
  }

}
