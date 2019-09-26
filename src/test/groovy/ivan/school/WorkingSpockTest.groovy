package ivan.school

import spock.lang.Specification

class WorkingSpockTest extends Specification {
    def 'test'() {
        given:
        def a = 1

        expect:
        a == 1
    }
}
