/*
 * Copyright (c) 2017 Biopet
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package nl.biopet.tools.rebuildcontigmap

import java.io.{File, PrintWriter}

import nl.biopet.utils.test.tools.ToolTest
import org.testng.annotations.Test

import scala.io.Source

class RebuildContigMapTest extends ToolTest[Args] {
  def toolCommand: RebuildContigMap.type = RebuildContigMap

  /**
    * Simple tool that does not need elaborate description.
    * @return Minimum number of words in the description.
    */
  override def minDescriptionWords = 20
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
    reader.getLines().toList.filter(!_.startsWith("#")) shouldBe List(
      "chrQ\tchrT")
    reader.close()
  }
}
