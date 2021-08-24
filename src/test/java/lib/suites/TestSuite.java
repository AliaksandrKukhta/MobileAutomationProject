package lib.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tests.ArticleTests;
import tests.SearchTests;
import tests.SwipeTests;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        ArticleTests.class,
        SearchTests.class,
        SwipeTests.class
})

public class TestSuite {
}
