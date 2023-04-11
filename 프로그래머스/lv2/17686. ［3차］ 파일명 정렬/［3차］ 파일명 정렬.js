function solution(files) {
    var answer = files.sort((a, b) => {
        let aNumber = 0;
        let aTail = 0;
        let bNumber = 0;
        let bTail = 0;
        
        for(let i = 0; i < a.length; i += 1) {
            if(a.charCodeAt(i) >= 48 && a.charCodeAt(i) <= 57 && aNumber === 0) {
                aNumber = i;
            }
            if(a.charCodeAt(i) > 57 && aNumber !== 0) {
                aTail = i;
                break;
            }
        }
        for(let i = 0; i < b.length; i += 1) {
            if(b.charCodeAt(i) >= 48 && b.charCodeAt(i) <= 57 && bNumber === 0) {
                bNumber = i;
            }
            if(b.charCodeAt(i) > 57 && bNumber !== 0) {
                bTail = i;
                break;
            }
        }
        aTail = (aTail === 0 ? a.length + 1 : aTail);
        bTail = (bTail === 0 ? b.length + 1 : bTail);
        let aStr = a.slice(0, aNumber).toUpperCase();
        let bStr = b.slice(0, bNumber).toUpperCase();
        if(aTail - 1 - aNumber > 5) {
            aTail = aNumber + 5;
        }
        if(bTail - 1 - bNumber > 5) {
            bTail = bNumber + 5;
        }
        if(aStr === bStr) {
            return Number(a.slice(aNumber, aTail - 1)) - Number(b.slice(bNumber, bTail - 1));
        } else {
            return aStr > bStr ? 1 : -1;
        }
    })
    return answer;
}