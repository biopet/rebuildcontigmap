package nl.biopet.tools.rebuildcontigmap

import nl.biopet.test.BiopetTest
import org.testng.annotations.Test

class RebuildContigmapTest extends BiopetTest {
  @Test
  def testNoArgs(): Unit = {
    intercept[IllegalArgumentException] {
      RebuildContigmap.main(Array())
    }
  }
}
