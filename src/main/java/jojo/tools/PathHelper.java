package jojo.tools;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public interface PathHelper {

    static URL classPathURL() {
        return resourceURL("/");
    }

    static URL resourceURL(String resource) {
        return PathHelper.class.getResource(resource);
    }

    static Path classPath() throws URISyntaxException {
        return resourcePath("/");
    }

    static Path resourcePath(String resource) throws URISyntaxException {
        return Paths.get(PathHelper.class.getResource("/").toURI());
    }

}