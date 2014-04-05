import scala.language.experimental.macros
import scala.reflect.macros.whitebox.Context
import scala.reflect.macros._

object Provider {
  def testMacro[T]: Unit = macro testMacroImpl[T]
  def testMacroImpl[T](c: Context) = {
    import c.universe._
    val tpe = c.weakTypeOf[T]
    val members = tpe.members.toString
    q"println($members)"
  }
}

object Foo extends App {

	import scala.tools.reflect.ToolBox
	val tb = scala.reflect.runtime.currentMirror.mkToolBox()

  import tb.u._
  tb.eval(q"Provider.testMacro[Int]")

}