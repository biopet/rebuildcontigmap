package nl.biopet.tools.rebuildcontigmap

import nl.biopet.test.BiopetTest
import org.testng.annotations.Test

object RebuildContigmapTest extends BiopetTest {
  @Test
  def testNoArgs(): Unit = {
    intercept[IllegalArgumentException] {
      ToolTemplate.main(Array())
    }
  }
}
