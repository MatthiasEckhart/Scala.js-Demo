package me.eckhart.matthias

import scala.scalajs.js.JSApp
import org.scalajs.dom
import dom.document
import dom.html

object DemoApp extends JSApp {
  def main(): Unit = {
    /* Console.log("Hello world!"); */
    println("Hello world!")

    /* Count clicks: */
    val btn1 = document.getElementById("button-1")
    val btn1Clicks = document.getElementById("button-1-clicks")
    btn1.addEventListener("click", { (e0: dom.Event) =>
      val e = e0.asInstanceOf[dom.MouseEvent]
      println(s"Click on button '${btn1.id}'. Event: '${e.`type`}'.")
      val cnt = Integer.parseInt(btn1Clicks.textContent)
      btn1Clicks.textContent = String.valueOf(cnt + 1)
    }, false)

    /* Input listener: */
    val text1 = document.getElementById("text-1").asInstanceOf[html.Input]
    val text1Input = document.getElementById("text-1-input")
    text1.oninput = { (e: dom.Event) =>
      println(s"Event: ${e.`type`}.")
      text1Input.textContent = text1.value
    }

  }
}
