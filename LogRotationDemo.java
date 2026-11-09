package com.storex.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogRotationDemo {

    private static final Logger log = LoggerFactory.getLogger(LogRotationDemo.class);

    public static void main(String[] args) {
        log.info("=== Bắt đầu kịch bản kiểm thử Log Rotation ===");
        log.info("Cấu hình chính sách: MaxFileSize = 10MB, MaxHistory = 30 ngày, TotalSizeCap = 3GB");

        StringBuilder payloadBuilder = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            payloadBuilder.append("StoreX-TxData-OrderDetails-CustomerPayload-Demo-Data-");
        }
        String payload = payloadBuilder.toString();

        long startTime = System.currentTimeMillis();
        int totalLogs = 15000;

        log.info("Bắt đầu sinh {} dòng log để kiểm tra kích hoạt cuộn file log...", totalLogs);

        for (int i = 1; i <= totalLogs; i++) {
            log.info("Log index #{} - Transaction processing simulated event: {}", i, payload);

            if (i % 2000 == 0) {
                log.info("Đã sinh {} / {} dòng log...", i, totalLogs);
            }
        }

        long duration = System.currentTimeMillis() - startTime;
        log.info("=== Hoàn tất kiểm thử Log Rotation ===");
        log.info("Thời gian thực thi: {} ms", duration);
        log.info("Kiểm tra thư mục logs/ để xác nhận các file đã được cắt:");
        log.info("  1. logs/application.log (file đang hoạt động)");
        log.info("  2. logs/application-<date>.0.log (file đã cuộn khi đạt 10MB)");
    }
}
