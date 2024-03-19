package assign06;

import static org.junit.jupiter.api.Assertions.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

class WebBrowserTest {

  @SuppressWarnings("deprecation")
  @Test
  void testWebBrowser() throws MalformedURLException {

    URL link = new URL("https://a");
    assertEquals("https://a", link.toString()); // true
    assertNotEquals("https://b", link.toString()); // false
    assertEquals(new URL("https://b"), link); // unexpectedly true

  }

  @Test
  void testwebbrowserhistory() throws MalformedURLException {
    URL URL1 = new URL("https://a");
    URL URL2 = new URL("https://b");
    URL URL3 = new URL("https://c");

    // test visit method
    WebBrowser w = new WebBrowser();
    w.visit(URL1);
    w.visit(URL2);
    w.visit(URL3);

    // Test history method
    SinglyLinkedList<URL> list = w.history();

    // list should be [ URL3, URL2, URL1 ]
    assertEquals(URL3, list.get(0));
    assertEquals(URL2, list.get(1));
    assertEquals(URL1, list.get(2));

    // Test constructor with history parameter
    WebBrowser w2 = new WebBrowser(list); // URL3 is the "current" webpage, with URL2 and URL1 in the history
    // Test back method
    // should be URL2
    assertEquals(URL2, w2.back());
    // should be URL1
    assertEquals(URL1, w2.back());

  }

  @Test
  void testWebBrowserBack() throws MalformedURLException {
    WebBrowser webBrowser = new WebBrowser();

    // Visit some URLs
    URL URL1 = new URL("https://example1.com");
    URL URL2 = new URL("https://example2.com");
    URL URL3 = new URL("https://example3.com");
    webBrowser.visit(URL1);
    webBrowser.visit(URL2);
    webBrowser.visit(URL3);

    // Verify back functionality
    assertEquals(URL2, webBrowser.back()); // Navigate back to url2
    assertEquals(URL1, webBrowser.back()); // Navigate back to initial URL
  }

  @Test
  void testWebBrowserBackThrow() throws MalformedURLException {
    WebBrowser webBrowser = new WebBrowser();

    // Try to navigate back when there's no more history
    assertThrows(NoSuchElementException.class, webBrowser::back);
  }

  @Test
  void testWebBrowserForth() throws MalformedURLException {
    WebBrowser webBrowser = new WebBrowser();

    // Visit some URLs
    URL URL1 = new URL("https://example1.com");
    URL URL2 = new URL("https://example2.com");
    URL URL3 = new URL("https://example3.com");
    webBrowser.visit(URL1);
    webBrowser.visit(URL2);
    webBrowser.visit(URL3);

    // Verify back functionality
    webBrowser.back(); // Navigate back to URL2
    URL result = webBrowser.forward(); // Navigate forward to URL3

    // Verify that the forward method correctly returns the URL of the current site
    // (URL3)
    assertEquals(URL3, result);
  }

  @Test
  void testWebBrowserForwardThrow() throws MalformedURLException {
    WebBrowser webBrowser = new WebBrowser();

    // Try to navigate back when there's no more history
    assertThrows(NoSuchElementException.class, webBrowser::forward);
  }
}
