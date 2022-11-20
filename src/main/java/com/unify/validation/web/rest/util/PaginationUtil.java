package com.unify.validation.web.rest.util;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;

public class PaginationUtil {

    public static <T> HttpHeaders generateHeaders(Page<T> page) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(page.getTotalElements()));

        return headers;
    }
}
