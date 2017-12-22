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
       |${(example("-I", "oldContigMap.tsv", "-R", "reference.fasta", "-o", "newContigMap.tsv"))}
     """.stripMargin
}
