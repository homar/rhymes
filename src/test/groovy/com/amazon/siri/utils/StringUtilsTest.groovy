package com.amazon.siri.utils

import spock.lang.Specification

class StringUtilsTest extends Specification {

    def "should reverse string, which length is an even number, correctly"() {
        given:
            def toReverse = "1234".toCharArray()
            def expected = "4321".toCharArray()

        when:
            def result = Utils.reverse(toReverse)

        then:
            result == expected
    }

    def "should reverse string, which length is not an even number, correctly"() {
        given:
            def toReverse = "12345".toCharArray()
            def expected = "54321".toCharArray()

        when:
            def result = Utils.reverse(toReverse)

        then:
            result == expected
    }

}
