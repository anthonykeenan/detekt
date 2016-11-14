package io.gitlab.arturbosch.detekt.rules.formatting

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import com.natpryce.hamkrest.hasSize
import com.natpryce.hamkrest.isEmpty
import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.Rule
import io.gitlab.arturbosch.detekt.rules.format
import io.gitlab.arturbosch.detekt.rules.lint
import org.junit.jupiter.api.Test

/**
 * @author Shyiko
 */
class SpacingAroundOperatorsTest : RuleTest {

	override val rule: Rule = SpacingAroundOperator(Config.empty)

	@Test
	fun testLint() {
		assertThat(rule.lint(
				"""
            import a.b.*
            fun main() {
                val v = 0 - 1 * 2
                val v1 = 0-1*2
                val v2 = -0 - 1
                val v3 = v * 2
                i++
                val y = +1
                var x = 1 in 3..4
                val b = 1 < 2
                fun(a = true)
                val res = ArrayList<LintError>()
                fn(*arrayOfNulls<Any>(0 * 1))
                fun <T>List<T>.head() {}
                val a= ""
                d *= 1
                call(*v)
            }
            """
		), hasSize(equalTo(3)))
	}

	@Test
	fun testFormat() {
		assertThat(rule.format(
				"""
            fun main() {
                val v1 = 0-1*2
                val v2 = -0-1
                val v3 = v*2
                i++
                val y = +1
                var x = 1 in 3..4
            }
            """
		), equalTo(
				"""
            fun main() {
                val v1 = 0 - 1 * 2
                val v2 = -0 - 1
                val v3 = v * 2
                i++
                val y = +1
                var x = 1 in 3..4
            }
            """.trimIndent()
		))
	}
}