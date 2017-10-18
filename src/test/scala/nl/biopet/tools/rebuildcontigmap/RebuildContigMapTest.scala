package nl.biopet.tools.rebuildcontigmap

import java.io.{File, PrintWriter}

import nl.biopet.test.BiopetTest
import org.testng.annotations.Test

import scala.io.Source

class RebuildContigMapTest extends BiopetTest {
  @Test
  def testNoArgs(): Unit = {
    intercept[IllegalArgumentException] {
      RebuildContigMap.main(Array())
    }
  }

  @Test
  def testMain(): Unit = {
    val outputFile = File.createTempFile("contigMap.", ".tsv")
    outputFile.deleteOnExit()
    val inputFile = File.createTempFile("contigMap.", ".tsv")
    inputFile.deleteOnExit()

    val writer = new PrintWriter(inputFile)
    writer.println("chrT\tchrQ")
    writer.close()

    RebuildContigMap.main(
      Array("-I",
        inputFile.getAbsolutePath,
        "-o",
        outputFile.getAbsolutePath,
        "-R",
        resourcePath("/fake_chrQ.fa")))

    val reader = Source.fromFile(outputFile)
    reader.getLines().toList.filter(!_.startsWith("#")) shouldBe List("chrQ\tchrT")
    reader.close()
  }
}