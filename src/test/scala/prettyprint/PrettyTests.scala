package prettyprint

import com.github.peterzeller.prettyprint.PrettyPrintDoc._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class PrettyTests extends AnyFunSuite with Matchers {


  test("simpleNesting") {

    val d: Doc = group("hello(" <> nested(2, line <> sep("," <> lineOrSpace, (0 to 20).map(_.toString))) <> ")")

    val s = d.prettyStr(50)
    assert(s ==
      """hello(
        |  0,
        |  1,
        |  2,
        |  3,
        |  4,
        |  5,
        |  6,
        |  7,
        |  8,
        |  9,
        |  10,
        |  11,
        |  12,
        |  13,
        |  14,
        |  15,
        |  16,
        |  17,
        |  18,
        |  19,
        |  20)""".stripMargin)

  }

  test("group") {

      val d: Doc = group("hello(" <> nested(2, line <> sep("," <> lineOrSpace, (0 to 20).map(_.toString))) <> ")")

      val s = d.prettyStr(120)
      assert(s == """hello(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20)""")

    }


  test("Print tree") {
    // example from the Wadler paper
    case class Tree(text: String, children: List[Tree])

    def tree(text: String, children: Tree*): Tree = Tree(text, children.toList)

    def showTree(t: Tree): Doc =
      t.text <> nested(t.text.length, showBracket(t.children))

    def showBracket(ts: List[Tree]): Doc =
      if (ts.isEmpty) ""
      else "[" <> nested(1, sep("," <> lineOrSpace, ts.map(showTree))) <> "]"

    val t = tree("aaa",
      tree("bbbb",
        tree("ccc"),
        tree("dd")
      ),
      tree("eee"),
      tree("fff",
        tree("gg"),
        tree("hhh"),
        tree("ii")
      )
    )

    val d: Doc = showTree(t)
    val s: String = d.prettyStr(30)

    assert(s ==
      """aaa[bbbb[ccc,
        |         dd],
        |    eee,
        |    fff[gg,
        |        hhh,
        |        ii]]""".stripMargin)

  }


}
