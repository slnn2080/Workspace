import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.AntPathMatcher;

import java.time.LocalDateTime;

@SpringBootTest(classes = FeatureTest.class)
public class FeatureTest {
  @Test
  public void matcherTest() {
    AntPathMatcher matcher = new AntPathMatcher();
    String url = "/backend/**";
    boolean match = matcher.match(url, "/backend/index.html");
    System.out.println("match = " + match);
  }
  
  @Test
  public void localDateTimeTest() {
    LocalDateTime now = LocalDateTime.now();
    System.out.println("now = " + now);
    // 2023-03-22T21:43:56.549
  }

  @Test
  public void testAns() {
    int num = 3;
    switch (num) {
      case 1:
        System.out.println("巨蟹");
        break;
      case 2:
        System.out.println("射手");
        break;
      case 3:
        System.out.println("双子");
      case 4:
        System.out.println("白羊");
        break;
      default:
        System.out.println("天蝎");
    }
  }
}
