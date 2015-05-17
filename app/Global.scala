import org.atmosphere.cpr.ApplicationConfig
import org.atmosphere.play.{AtmosphereCoordinator, Router}
import org.atmosphere.samples.play.ScalaChat
import play.api.mvc.{Handler, RequestHeader}
import play.api.{Application, GlobalSettings}

object Global extends GlobalSettings {

  override def onStart(app: Application): Unit = {
    atmosphere
      .discover(classOf[ScalaChat])
      .framework().addInitParameter(ApplicationConfig.ANALYTICS, "false")
    atmosphere.ready()
  }

  override def onStop(app: Application): Unit = {
    atmosphere.shutdown()
  }

  override def onRouteRequest(request: RequestHeader): Option[Handler] = {
    Router.dispatch(request).orElse {
      super.onRouteRequest(request)
    }
  }

  private def atmosphere = AtmosphereCoordinator.instance()

}
