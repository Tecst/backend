import http from 'k6/http';
import { sleep } from 'k6';

export let options = {
    vus: 1,  // 1개의 가상 사용자
    duration: '30s',  // 30초 동안 테스트 수행
};

export default function () {
    var url = 'http://localhost:8080/api/v1/question';
    var params = {
        headers: {
            'Authorization': 'Bearer <토큰 입력>',
        },
    };

    http.get(url, params);
    sleep(1);
}
