package controllers

import play.api.mvc.{Controller, Action}

// Non-atmosphere controller
object Hello extends Controller {

  def index = Action {
    Ok("Hello")
  }

}
