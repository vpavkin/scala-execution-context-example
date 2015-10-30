import java.util.concurrent.Executors

import scala.concurrent.duration._
import scala.concurrent.{Await, ExecutionContext, Future}


object Main extends App {

  val list = (0 until 1000).toList

  def future(v: Int) = Future {
    Thread.sleep(2000)
    println(v)
    v
  }

  /*
  * Uncomment to use default context.
  *
  * Source: http://docs.scala-lang.org/overviews/core/futures.html
  * By default the ExecutionContext.global sets the parallelism level of its underlying fork-join pool
  * to the amount of available processors (Runtime.availableProcessors).
  *
  * For my machine it's 8
  */
  // implicit val ec = scala.concurrent.ExecutionContext.Implicits.global


  implicit val ec = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(50))

  Await.result(Future.sequence(list.map(future)), 10 minutes)


}
