package com.example.demo

import com.example.demo.fileReaderWriter.TextReader
import spock.lang.Specification

/**
 * Created by sujatha.bandi on 6/8/18.
 *
 */
class TextReaderTest extends Specification {
    def setup() {

    }

    def 'Test File read'() {
        when:
        def txtReader = new TextReader()
        def lines  =txtReader.readLines("./src/test/resources/Input.txt")

        then:
        lines.size == 5
    }


    def 'Get substring'() {
        when:
        def subStr = TextReader.getFiled("This is line1", 8, 5 )

        then:
        subStr == 'line1'
    }

}
