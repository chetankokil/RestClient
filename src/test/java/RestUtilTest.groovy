/**
 * Created by chetankokil on 2/4/16.
 */
class RestUtilTest extends groovy.util.GroovyTestCase {
    void testGet() {
        String str = RestUtil.get("http://gturnquist-quoters.cfapps.io/api/random")
        println(str)
        assertNotNull(str)
    }
}
