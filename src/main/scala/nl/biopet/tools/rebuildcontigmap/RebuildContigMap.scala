/*
 * Copyright (c) 2017 Sequencing Analysis Support Core - Leiden University Medical Center
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

import java.io.PrintWriter

import nl.biopet.utils.ngs.fasta
import nl.biopet.utils.tool.ToolCommand

object RebuildContigMap extends ToolCommand[Args] {
  def emptyArgs: Args = Args()
  def argsParser = new ArgsParser(this)
  def main(args: Array[String]): Unit = {
    val cmdArgs = cmdArrayToArgs(args)

    logger.info("Start")

    val newMap =
      fasta.rebuildContigMap(cmdArgs.inputContigMap, cmdArgs.referenceFasta)

    val writer = new PrintWriter(cmdArgs.outputContigMap)
    writer.println("#Name_in_fasta\tAlternative_names")
    for ((contigName, alternativeNames) <- newMap) {
      writer.println(contigName + "\t" + alternativeNames.mkString(";"))
    }
    writer.close()

    logger.info("Done")
  }

  def descriptionText: String =
    """
      |This tool rebuilds a contig map, so the contig names used
      |are equal to those in a given fasta reference.
    """.stripMargin

  def manualText: String =
    s"""
      |$toolName requires the original contig map and a reference fasta file.
      |It will then reconstruct the contig map and output it at the given path.
      |Contig maps should be in tsv format.
    """.stripMargin

  def exampleText: String =
    s"""
       |To construct a new contig map with contig names that match the fasta
       |reference:
       |
       |${(example("-I",
                   "oldContigMap.tsv",
                   "-R",
                   "reference.fasta",
                   "-o",
                   "newContigMap.tsv"))}
     """.stripMargin
}
