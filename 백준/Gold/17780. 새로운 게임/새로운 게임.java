import java.io.*;
import java.util.*;

public class Main{
    static int N, K;
    static int[][] horse;
    static Horse[][] map;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    public static class Horse {
        int x;
        int y;
        int color;
        List<Integer> list;

        public Horse(int x, int y, int color) {
            this.x = x;
            this.y = y;
            this.color = color;
            list = new ArrayList<>();
        }
        public void setList(List<Integer> item) {
            this.list.addAll(item);
        }
        public void setReverse() {
            Collections.reverse(this.list);
        }
        public boolean isEnd() {
            if(this.list.size() >= 4) return true;
            return false;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 체스판 크기
        N = Integer.parseInt(st.nextToken());
        // 말 개수
        K = Integer.parseInt(st.nextToken());

        // 체스판에 말 객체 놓기
        map = new Horse[N][N];
        for(int i = 0; i < N; i ++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j ++) {
                map[i][j] = new Horse(i, j, Integer.parseInt(st.nextToken()));
            }
        }
        // 말 정보 담기
        horse = new int[K][3];
        for(int i = 0; i < K; i ++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            map[x - 1][y - 1].list.add(i);
            horse[i][0] = x - 1;
            horse[i][1] = y - 1;
            horse[i][2] = Integer.parseInt(st.nextToken()) - 1;
        }

        int cnt = 1;
        outline : while(cnt <= 1001) {
            for (int i = 0; i < K; i++) {
                if(map[horse[i][0]][horse[i][1]].list.get(0) != i) continue;
                // 이동 방향 설정
                int d = horse[i][2];
                int nx = horse[i][0] + dx[d];
                int ny = horse[i][1] + dy[d];

                // 체스판을 벗어나는 경우 방향바꿔 이동
                if(nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny].color == 2) {
                    if(d == 0) d = 1;
                    else if(d == 1) d = 0;
                    else if(d == 2) d = 3;
                    else if(d == 3) d = 2;

                    nx = horse[i][0] + dx[d];
                    ny = horse[i][1] + dy[d];
                    horse[i][2] = d;
                }
                if(nx < 0 || nx >= N || ny < 0 || ny >= N) {
                	horse[i][2] = d;
                	continue;
                }
                
                // 옮기려는 판의 색이 흰색일 경우
                if(map[nx][ny].color == 0) {
                    moveWhite(nx, ny, i);
                }
                // 빨간색일 경우
                else if(map[nx][ny].color == 1) {
                    moveRed(nx, ny, i);
                }
                // 파란색일 경우
                else if(map[nx][ny].color == 2) {
                    // 이미 방향을 옮겼는데 파란색일 경우
                    if(d == 0) d = 1;
                    if(d == 1) d = 0;
                    if(d == 2) d = 3;
                    if(d == 3) d = 2;

                    horse[i][2] = d;
                }
                if(map[nx][ny].isEnd()) break outline;
            }
            cnt++;
        }
        if(cnt > 1000) System.out.println(-1);
        else System.out.println(cnt);

    }

    public static void moveWhite(int nx, int ny, int i) {
        // 리스트 뒤에 붙이기
        map[nx][ny].setList(map[horse[i][0]][horse[i][1]].list);
        // 기존 판에 있던 말 지우기
        map[horse[i][0]][horse[i][1]].list.clear();
        // 해당 말이 엎힌 경우
        for(int len = 0; len < map[nx][ny].list.size(); len ++) {
        	horse[map[nx][ny].list.get(len)][0] = nx;
            horse[map[nx][ny].list.get(len)][1] = ny;
        }
    }
    public static void moveRed(int nx, int ny, int i) {
        // 리스트 뒤집어서 뒤에 붙이기
        map[horse[i][0]][horse[i][1]].setReverse();
        map[nx][ny].setList(map[horse[i][0]][horse[i][1]].list);
        // 기존 판에 있던 말 지우기
        map[horse[i][0]][horse[i][1]].list.clear();
        // 해당 말이 엎힌 경우
        for(int len = 0; len < map[nx][ny].list.size(); len ++) {
        	horse[map[nx][ny].list.get(len)][0] = nx;
            horse[map[nx][ny].list.get(len)][1] = ny;
        }
    }
}

