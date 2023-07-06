package com.hust.crawler.link;

public interface Link {
    final String baseFilePath = "E:\\Game\\history-master\\history-master\\src\\main\\resources\\";
    void writeToFile(String fileName);
    void crawl(String url);
}
