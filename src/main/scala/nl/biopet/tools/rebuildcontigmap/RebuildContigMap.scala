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
}
