//import com.nhaarman.mockitokotlin2.mock
package main.kotlin.stockWatcher

import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions

class WatcherTest {
    @Test
    fun getsNearestTo5() {
        Assertions.assertThat(roundTo5(74.2)).isEqualTo(70.0)
        Assertions.assertThat(roundTo5(76.2)).isEqualTo(75.0)
    }

    @Test
    fun shouldUpdateIfExistingIsSmallerByMoreThan5() {
        Assertions.assertThat(shouldBeUpdated(70.0, 76.1)).isEqualTo(true)
        Assertions.assertThat(shouldBeUpdated(75.0, 76.2)).isEqualTo(false)
    }
}