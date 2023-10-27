function solution(name, yearning, photo) {
    var answer = [];
    // map 생성
    const map = new Map();
    // name에 따른 그리움 점수 mapping 하기
    for(let i = 0; i < name.length; i ++) {
        map.set(name[i], yearning[i]);
    }
    // 사진 순회하며 그리움 점수 저장하기
    let sum;
    for(let i = 0; i < photo.length; i ++) {
        sum = 0;
        for(let j = 0; j < photo[i].length; j ++) {
            map.has(photo[i][j]) ? sum += map.get(photo[i][j]) : sum += 0;
        }
        answer.push(sum);
    }
    return answer;
}