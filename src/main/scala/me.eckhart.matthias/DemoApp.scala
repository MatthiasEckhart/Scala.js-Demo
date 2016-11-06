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

    /* Sketchpad:
     * More details: http://www.lihaoyi.com/hands-on-scala-js/#MakingaSketchpadusingMouseInput */
    val sketchpadCanvas = document.getElementById("sketchpad").asInstanceOf[html.Canvas]
    sketchpadCanvas.width = sketchpadCanvas.parentElement.clientWidth
    sketchpadCanvas.height = sketchpadCanvas.parentElement.clientHeight
    val fillColorSelect = document.getElementById("sketchpad-color").asInstanceOf[html.Select]
    val renderer = sketchpadCanvas.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D]
    renderer.fillStyle = "#f8f8f8"
    renderer.fillRect(0, 0, sketchpadCanvas.width, sketchpadCanvas.height)
    renderer.fillStyle = fillColorSelect.value

    fillColorSelect.onchange = { (e: dom.Event) => renderer.fillStyle = fillColorSelect.value }

    var down = false

    sketchpadCanvas.onmousedown = (e: dom.MouseEvent) => down = true

    sketchpadCanvas.onmouseup = (e: dom.MouseEvent) => down = false

    sketchpadCanvas.onmousemove = {
      (e: dom.MouseEvent) =>
        val rect =
          sketchpadCanvas.getBoundingClientRect()
        if (down) renderer.fillRect(
          e.clientX - rect.left,
          e.clientY - rect.top,
          10, 10
        )
    }
  }
}
