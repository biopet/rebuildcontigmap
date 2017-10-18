package nl.biopet.tools.rebuildcontigmap

import java.io.File

case class Args(inputContigMap: File = null,
                outputContigMap: File = null,
                referenceFasta: File = null)
