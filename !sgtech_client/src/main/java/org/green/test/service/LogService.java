package org.green.test.service;

import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class LogService {

    private final String LOG_FILE_PATH = "./logs/user_access.log";

    /**
     * 로그 파일에서 키워드를 포함한 행만 추출하여 최신순으로 반환
     */
    public List<String> getFilteredLogs(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return Collections.emptyList();
        }

        try (Stream<String> lines = Files.lines(Paths.get(LOG_FILE_PATH))) {
            List<String> result = lines
                    .filter(line -> line.contains(keyword))
                    .collect(Collectors.toList());
            
            // [추가] 최신 로그를 위로 올리기 위해 리스트를 역순으로 정렬
            Collections.reverse(result);
            return result;
            
        } catch (IOException e) {
            return Collections.singletonList("로그 파일을 읽는 중 오류가 발생했습니다. 파일이 아직 생성되지 않았을 수 있습니다.");
        }
    }
}