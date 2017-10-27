# Manual

## Introduction
This tool rebuilds a contig map, so it fits the contig names used in a given fasta reference.

## Example
To run this tool:
```bash
java -jar RebuildContigMap-version.jar -I oldContigMap.tsv -o newContigMap.tsv -R myReference.fa
```

To get help:
```bash
java -jar RebuildContigMap-version.jar --help
General Biopet options


Options for RebuildContigMap

Usage: RebuildContigMap [options]

  -l, --log_level <value>  Level of log information printed. Possible levels: 'debug', 'info', 'warn', 'error'
  -h, --help               Print usage
  -v, --version            Print version
  -I, --inputContigMap <file>
                           Input contig map
  -o, --outputContigMap <file>
                           output contig map
  -R, --referenceFasta <file>
                           Reference fasta file
```

## Ouput
A new contig map adjusted to fit the given reference.