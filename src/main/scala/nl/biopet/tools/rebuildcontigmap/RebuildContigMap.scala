package nl.biopet.tools.rebuildcontigmap

import java.io.PrintWriter

import nl.biopet.utils.ngs.fasta
import nl.biopet.utils.tool.ToolCommand

object RebuildContigMap extends ToolCommand {
  def main(args: Array[String]): Unit = {
    val parser = new ArgsParser(toolName)
    val cmdArgs =
      parser.parse(args, Args()).getOrElse(throw new IllegalArgumentException)

    logger.info("Start")

    val newMap = fasta.rebuildContigMap(cmdArgs.inputContigMap, cmdArgs.referenceFasta)

    val writer = new PrintWriter(cmdArgs.outputContigMap)
    writer.println("#Name_in_fasta\tAlternative_names")
    for ((contigName, alternativeNames) <- newMap) {
      writer.println(contigName + "\t" + alternativeNames.mkString(";"))
    }
    writer.close()

    logger.info("Done")
  }
}
