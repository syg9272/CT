#include <vector>

using namespace std;

int solution(vector<vector<int>> sizes) {
    int left = 0;
    int right = 0;
    for(int i = 0; i < sizes.size(); i ++) {
        if(sizes[i][0] < sizes[i][1]) {
            int temp = sizes[i][1];
            sizes[i][1] = sizes[i][0];
            sizes[i][0] = temp;
        }
        left = max(left, sizes[i][0]);
        right = max(right, sizes[i][1]);
    }
    return left * right;
}