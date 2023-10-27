function solution(brown, yellow) {
    var answer = [];
    // 가로와 세로의 길이 차이가 최소가 되는 경우 찾기
    let sum = brown + yellow;   // 격자 개수
    let width = sum, height = 1;  // 가로 세로 길이
    for(let i = Math.ceil(Math.pow(sum, 0.5)); i > 2; i --) {
        // 나누어 떨어질 경우
        if(sum % i === 0) {
            width = Math.max(i, sum / i);
            height = Math.min(i, sum / i);
            if(sum - (width * 2 + height * 2 - 4) <= yellow) break;
        }
    }
    answer.push(width);
    answer.push(height);
    return answer;
}