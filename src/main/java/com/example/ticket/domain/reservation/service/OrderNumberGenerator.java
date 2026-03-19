package com.example.ticket.domain.reservation.service;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class OrderNumberGenerator {

    // 1. 사용할 62개의 문자 재료들 (알파벳 대소문자 + 숫자)
    private static final char[] BASE62 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();

    // 2. Base62 인코딩 로직 (숫자를 짧은 문자로 압축)
    private static String encodeBase62(long value) {
        StringBuilder sb = new StringBuilder();
        do {
            sb.append(BASE62[(int) (value % 62)]);
            value /= 62;
        } while (value > 0);
        return sb.reverse().toString();
    }

    public static String createTicketNum() {
        // 절대 안 겹치게 하려면? -> "현재 시간의 밀리초"를 사용 (시간은 계속 흐르니까 절대 안 겹침!)
        long currentTime = System.currentTimeMillis();

        // 여기에 혹시라도 같은 1/1000초에 동시에 결제하는 사람을 막기 위해 난수(0~999)를 살짝 더해줌
        int randomSalt = new Random().nextInt(1000);

        // 시간 + 난수를 합친 고유한 숫자를 Base62로 압축!
        long uniqueNumber = (currentTime * 1000) + randomSalt;

        // 결과: "8fK3zQp" (영화관 뺨치는 딱 7~8자리 번호 탄생!)
        return encodeBase62(uniqueNumber);
    }
}
