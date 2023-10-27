let answer = 100;
function solution(begin, target, words) {
    // dfs로 단어 찾기
    let checked = [];
    for(let i = 0; i < words.length; i ++) {
        checked.push(false);
    }
    dfs(0, begin, target, words, checked);
    return answer === 100 ? 0 : answer;
}

function dfs(cnt, begin, target, words, checked) {
    if(begin === target) {
        answer = Math.min(answer, cnt);
        return;
    }
    if(cnt > answer) return;
    let diff;
    for(let i = 0; i < words.length; i ++) {
        if(!!checked[i]) continue;
        diff = 0;
        for(let j = 0; j < begin.length; j ++) {
            if(begin[j] !== words[i][j]) diff ++;
        }
        if(diff === 1) {
            checked[i] = 1;
            dfs(cnt + 1, words[i], target, words, checked);
            checked[i] = 0;
        }
    }
    
    return;
}