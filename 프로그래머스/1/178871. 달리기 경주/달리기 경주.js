function solution(players, callings) {
    var answer = [];
    // map에 선수와 등수 저장하기
    const name = new Map(); // 선수 등록
    const rank = new Map(); // 등수 등록
    // 초기 플레이어 정보를 map에 저장
    for(let i = 0; i < players.length; i ++) {
        name.set(players[i], i);
        rank.set(i, players[i]);
    }
    // calling 배열 순회하며 선수 변경하기
    let str = "";   // 추월당한 선수 이름
    let num = 0;    // 추월한 선수의 등수
    for(let i = 0; i < callings.length; i ++) {
        num = name.get(callings[i]);    // 추월한 선수의 기존 등수
        str = rank.get(num - 1);    // 추월 당한 선수 이름
        // 두 선수의 정보 업데이트
        name.set(callings[i], num - 1);
        name.set(str, num);
        rank.set(num, str);
        rank.set(num - 1, callings[i]);
    }
    
    for(let info of rank.keys()) {
        answer.push(rank.get(info));
    }
    
    return answer;
}