package nl.biopet.tools.rebuildcontigmap

import java.io.File

import nl.biopet.utils.tool.{AbstractOptParser, ToolCommand}

class ArgsParser(toolCommand: ToolCommand[Args])
    extends AbstractOptParser[Args](toolCommand) {
  opt[File]('I', "inputContigMap") required () unbounded () valueName "<file>" action {
    (x, c) =>
      c.copy(inputContigMap = x)
  } text "Input contig map"
  opt[File]('o', "outputContigMap") required () unbounded () valueName "<file>" action {
    (x, c) =>
      c.copy(outputContigMap = x)
  } text "output contig map"
  opt[File]('R', "referenceFasta") required () unbounded () valueName "<file>" action {
    (x, c) =>
      c.copy(referenceFasta = x)
  } text "Reference fasta file"
}
