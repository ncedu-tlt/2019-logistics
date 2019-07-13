package ru.ncedu.logistics.api.util;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PageUtils {
    private static final int DEFAULT_START = 1;
    private static final int DEFAULT_END = 10;

    public static final String CONTENT_RANGE_HEADER = "Content-Range";

    private static final Pattern RANGE_PATTERN = Pattern.compile("^([0-9]+)-([0-9]+)$");
    private static final Pattern SORT_PATTERN = Pattern.compile("^([^:]+)(:(asc|desc))?$");

    @RequiredArgsConstructor
    public static class ConstPageable implements Pageable {

        private final int offset;
        private final int limit;
        private final Sort sort;

        @Override
        public int getPageNumber() {
            return limit/offset;
        }

        @Override
        public int getPageSize() {
            return limit;
        }

        @Override
        public long getOffset() {
            return offset;
        }

        @Override
        public Sort getSort() {
            return sort;
        }

        @Override
        public Pageable next() {
            return new ConstPageable(offset + limit, limit, sort);
        }

        @Override
        public Pageable previousOrFirst() {
            return new ConstPageable(Math.max(offset - limit, 0), limit, sort);
        }

        @Override
        public Pageable first() {
            return new ConstPageable(0, limit, sort);
        }

        @Override
        public boolean hasPrevious() {
            return offset > 0;
        }
    }

    public static Pageable parse(String ranging, List<String> sorting) {
        final int start;
        final int end;

        if (!StringUtils.isEmpty(ranging)) {
            Matcher rangeMatcher = RANGE_PATTERN.matcher(ranging);

            if (rangeMatcher.find()) {
                start = Integer.parseInt(rangeMatcher.group(1))-1;
                end = Integer.parseInt(rangeMatcher.group(2));
            } else {
                throw new IllegalArgumentException("Invalid Range header value \"" + ranging + "\"");
            }
        } else {
            start = DEFAULT_START-1;
            end = DEFAULT_END;
        }

        if (!CollectionUtils.isEmpty(sorting)) {
            List<Sort.Order> sortOrders = new LinkedList<>();
            sorting.forEach(sort -> {
                Matcher sortMatcher = SORT_PATTERN.matcher(sort);

                if (sortMatcher.find()) {
                    String property = sortMatcher.group(1);
                    String direction = sortMatcher.group(3);
                    if(direction == null) {
                        direction = "asc";
                    }
                    sortOrders.add(new Sort.Order(Sort.Direction.fromString(direction), property));
                } else {
                    throw new IllegalArgumentException("Invalid sort \"" + sort + "\"");
                }
            });
            return new ConstPageable(start, end-start, Sort.by(sortOrders));
        } else {
            return new ConstPageable(start, end-start, Sort.unsorted());
        }
    }

    public static void setContentRange(HttpServletResponse response, Pageable pageable, long total) {
        response.setHeader(CONTENT_RANGE_HEADER,
                (pageable.getOffset() + 1) + "-" + (pageable.getOffset() + pageable.getPageSize()) + "/" + total);
    }
}
