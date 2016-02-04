import com.test.pojo.Quote
import com.test.restclient.RestUtil

/**
 * Created by chetankokil on 2/4/16.
 */
class RestUtilTest extends groovy.util.GroovyTestCase {
    void testGet() {
        String str = RestUtil.get("http://gturnquist-quoters.cfapps.io/api/random", "application/json")
        println(str)
        assertNotNull(str)
    }


    void testGetUsingClass() {
        Quote quote = RestUtil.get("http://gturnquist-quoters.cfapps.io/api/random", "application/json", Quote.class)
        assertNotNull(quote)
        println(quote)
    }
}
