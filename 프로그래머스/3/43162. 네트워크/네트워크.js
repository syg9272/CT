function solution(n, computers) {
    var answer = 0;
    // 2차원 배열 생성
    let node = Array.from(Array(n + 1), () => Array(n + 1).fill(false));
    // 인접행렬 생성
    for(let i = 0; i < n; i ++) {
        for(let j = 0; j < n; j ++) {
            if(!!computers[i][j]) {
                node[i + 1][j + 1] = true;
                node[j + 1][i + 1] = true;
            }
        }
    }
    // bfs
    let q = []; // 큐 생성
    let visited = Array(n + 1).fill(false); // 방문체크 배열 초기화
    for(let i = 1; i <= n; i ++) {
        if(visited[i]) continue;
        q.push(i);  // 처음 노드 추가
        visited[i] = true;  // 방문 체크
        while(q.length > 0) {
            let time = q.length;
            for(let t = 0; t < time; t ++) {
                let temp = q.shift();
                for(let j = 1; j <= n; j ++) {
                    if(j == temp || !node[temp][j] || visited[j]) continue;
                    q.push(j);
                    visited[j] = true;
                }
            }
        }
        answer ++;
    }
    return answer;
}