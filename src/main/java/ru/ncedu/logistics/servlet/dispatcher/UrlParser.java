package ru.ncedu.logistics.servlet.dispatcher;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlParser {

    private static final Pattern VARIABLE_PATTERN = Pattern.compile("\\{([^}]+)}");
    private static final String VARIABLE_REPLACEMENT = "([^/]+)";

    private static final Pattern URL_EXTRACTOR_PATTERN = Pattern.compile("(http|https)://[^/]+(.*)");

    private Pattern pattern;
    private Map<Integer, String> groups = new HashMap<>();

    public UrlParser(String path) {
        Matcher matcher = VARIABLE_PATTERN.matcher(path);
        for(int groupNumber = 1; matcher.find(); ++groupNumber) {
            groups.put(groupNumber, matcher.group(1));
            path = path.replace(matcher.group(0), VARIABLE_REPLACEMENT);
            matcher = VARIABLE_PATTERN.matcher(path);
        }
        pattern = Pattern.compile(path);
    }

    public boolean matches(String requestUrl) {
        Matcher requestUrlMatcher = URL_EXTRACTOR_PATTERN.matcher(requestUrl);
        if(requestUrlMatcher.find()) {
            return pattern.matcher(requestUrlMatcher.group(2)).matches();
        } else {
            return false;
        }
    }

    public Map<String, String> parse(String requestUrl) {
        Matcher requestUrlMatcher = URL_EXTRACTOR_PATTERN.matcher(requestUrl);
        if(requestUrlMatcher.find()) {
            Matcher matcher = pattern.matcher(requestUrlMatcher.group(2));
            if(matcher.find()) {
                Map<String, String> variableValues = new HashMap<>();
                for(Map.Entry<Integer, String> group: groups.entrySet()) {
                    variableValues.put(group.getValue(), matcher.group(group.getKey()));
                }
                return variableValues;
            } else {
                throw new IllegalArgumentException();
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

}
