package assign06;

import java.net.URL;
import java.util.NoSuchElementException;

public class WebBrowser {
    private LinkedListStack<URL> backward;
    private LinkedListStack<URL> forward;
    private URL currentSite;

    public WebBrowser() {
        backward = new LinkedListStack<>();
        forward = new LinkedListStack<>();
        currentSite = null;
    }

    public WebBrowser(SinglyLinkedList<URL> history) {
        for (URL site : history) {
            backward.push(site);
        }
        currentSite = backward.pop();
    }

    public void visit(URL webpage) {
        backward.push(webpage);
        currentSite = webpage;
        forward.clear();
    }

    public URL back() throws NoSuchElementException {
        if (backward.isEmpty())
            throw new NoSuchElementException();
        forward.push(currentSite);
        currentSite = backward.pop();
        return currentSite;
    }

    public URL forward() throws NoSuchElementException {
        if (forward.isEmpty())
            throw new NoSuchElementException();
        backward.push(currentSite);
        currentSite = forward.pop();
        return currentSite;
    }

    public SinglyLinkedList<URL> history() {
        SinglyLinkedList<URL> historyList = new SinglyLinkedList<>();
        LinkedListStack<URL> tempStack = new LinkedListStack<>();

        tempStack.push(currentSite);
        while (!backward.isEmpty()){
            URL url = backward.pop();
            historyList.insertFirst(url);
            tempStack.push(url);
        }

        while (!tempStack.isEmpty()){
            backward.push(tempStack.pop());
        }
        return historyList;
    }

}
