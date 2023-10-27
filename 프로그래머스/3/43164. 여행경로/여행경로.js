let answer;
function solution(tickets) {
    answer = [];
    let visited = [];
    for(let i = 0; i < tickets.length; i ++) {
        visited.push(false);
    }
    dfs("ICN", tickets, visited, ["ICN"]);
    return answer;
}

function dfs(city, tickets, visited, check) {
    // 재귀 종료 조건
    let flag = true;
    for(let i = 0; i < visited.length; i ++) {
        if(!visited[i]) {
            flag = false;
            break;
        }
    }
    if(flag) {
        let str1 = "";
        for(let i = 0; i < answer.length; i ++) {
            str1 += answer[i];
        }
        let str2 = "";
        for(let i = 0; i < check.length; i ++) {
            str2 += check[i];
        }
        if(str1 === "" || str1 > str2) {
            answer = [...check];
        }
        return;
    }
    // city와 같은 도시 찾아서 이동
    for(let i = 0; i < tickets.length; i ++) {
        if(visited[i]) continue;
        if(tickets[i][0] === city) {
            check.push(tickets[i][1]);
            visited[i] = true;
            dfs(tickets[i][1], tickets, visited, check);
            visited[i] = false;
            check.pop();
        }
    }
    return;
}