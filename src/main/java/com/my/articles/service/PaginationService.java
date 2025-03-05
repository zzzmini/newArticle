package com.my.articles.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;

@Service
public class PaginationService {
    // 페이지 당 보여질 자료 수
    private static final int BAR_LENGTH = 5;

    public List<Integer> getPaginationBarNumber(
            int currentPageNumber,
            int totalPageNumber
    ) {
        int startNumber =
                Math.max(currentPageNumber - (BAR_LENGTH / 2), 0);
        int endNumber =
                Math.min(startNumber + BAR_LENGTH, totalPageNumber);
        return IntStream.range(startNumber, endNumber)
                .boxed().toList();
    }

    public int currentBarLength() {
        return BAR_LENGTH;
    }
}
