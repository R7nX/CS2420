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
        backward = new LinkedListStack<>();
        forward = new LinkedListStack<>();
        currentSite = null;

        LinkedListStack<URL> tempStack = new LinkedListStack<>();

        for (URL site : history) {
            tempStack.push(site);
        }

        while(!tempStack.isEmpty())
            backward.push(tempStack.pop());

        currentSite = backward.pop();
    }

    public void visit(URL webpage) {
        if (!(currentSite == null))
            backward.push(currentSite);
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

        while (!backward.isEmpty()){
            URL url = backward.pop();
            tempStack.push(url);
        }

        while (!tempStack.isEmpty()){
            URL url = tempStack.pop();
            historyList.insertFirst(url);
            backward.push(url);
        }
        historyList.insertFirst(currentSite);
        return historyList;
    }

}
