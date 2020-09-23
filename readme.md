# Scala Prettyprint

A prettyprinting library for Scala following paper *A prettier printer* by Philip Wadler.

- Automatic indentation
- Automatic line length limit


## Include Library

[![Jitpack](https://jitpack.io/v/peterzeller/scala-prettyprint.svg)](https://jitpack.io/#peterzeller/scala-prettyprint)

Use Jitpack to include the library.

## Basic usage

Import the library:

```
import com.github.peterzeller.prettyprint.PrettyPrintDoc._
```

Create a document of type `Doc`.

```
val d: Doc = group("hello(" <> nested(2, line <> sep("," <> lineOrSpace, 
                       (0 to 20).map(_.toString))) <> ")")
```

Print the document with a maximum line length of 50 characters:

```
val s: String = d.prettyStr(50)
```

## Example: Printing a Tree

```
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
println(s)
```

Output:

```
aaa[bbbb[ccc,
         dd],
    eee,
    fff[gg,
        hhh,
        ii]]
```



## Document API

Strings are implicitly converted to `Doc`:

```
val d: Doc = "Hello"
```

Use the following operators and function to create bigger documents:

- `A <> B` Concatenation of two documents.
- `A :<|> B` First try rendering A, if it exceeds maximum line length try B.
- `A.flatten` Remove all linebreaks from A.
- `line` A linebreak.
- `lineOrSpace` A linebreak that is changed to a space when removed by the formatter (flatten).
- `group(A)` Short for `A.flatten :<|> (_ => A)`. First try to print in one line. If that is to long print in several lines.
- `A <+> B` Concatenation with a space between the documents. Short for `A <> " " <> B`.
- `A </> B` Concatenation with a linebreak or space between the documents. Short for `A <> lineOrSpace <> B`.
- `sep(S, Ds)` Documents Ds separated by S.





