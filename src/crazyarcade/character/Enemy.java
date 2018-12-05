package crazyarcade.character;

import java.util.Random;

import crazyarcade.gui.ArcadeGamePanel;
import crazyarcade.Constant;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.LinkedList;
import java.util.Queue;


public class Enemy extends Character implements Constant {

    class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    int dir[][] = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};//������ ��Ÿ���� �迭
    int nx, ny;
    int bomblength = 1;
    int speed = 6;
    boolean CanGo[][] = new boolean[15][15];//�� �� �ִ� ���
    int[][] mapBlockNum;
    Random rand = new Random();
    public static Image img= Toolkit.getDefaultToolkit().getImage("src\\crazyarcade\\character\\enemy.png");

    Queue<Point> queue = new LinkedList<Point>();//Ž���� ���� ť ����

    public void start() {
        mapBlockNum = ArcadeGamePanel.mapBlockNum;
    }

    @Override
    public void ToNextCoord(int x, int y, int nx, int ny) {
        //x,y���� ��ǥ nx,ny �̿��� ��ǥ


    }

    @Override
    public void ToOtherCoord(int nx, int ny) {

    }

    public void RandomWalk() {//�������� �� �� �ִ� ������� ����
        for (int j = 1; j <= 3; j++) {
            int i = rand.nextInt(4);
            if (mapBlockNum[this.nx + dir[i][0]][this.ny + dir[i][1]] == 0) {
                this.nx += dir[i][0];
                this.ny += dir[i][1];
                break;
            }
        }
    }

    public void SearchDir() {
        int posx = this.nx;
        int posy = this.ny;
        int blockcount = 1;//�� �� �ִ� ��� ����
        int casedir[] = new int[4];// �� ���⿡ ǳ�� ������ �� ���� �� �� �ִ� ��� ��
        int qx, qy;
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                CanGo[i][j] = false;
            }
        }

        CanGo[posx][posy] = true;
        queue.add(new Point(posx, posy));
        while (!queue.isEmpty()) {
            qx = queue.peek().x;
            qy = queue.peek().y;
            queue.poll();
            for (int i = 0; i < 4; i++) {
                if (mapBlockNum[qx + dir[i][0]][qy + dir[i][1]] == 0 && !CanGo[qx + dir[i][0]][qy + dir[i][1]]) {
                    CanGo[qx + dir[i][0]][qy + dir[i][1]] = true;
                    queue.add(new Point(qx + dir[i][0], qy + dir[i][1]));
                    blockcount++;
                }
            }
        }

        for (int i = 0; i < 4; i++) {
            casedir[i] = blockcount;
            if (CanGo[posx + dir[i][0]][posy + dir[i][1]]) {
                casedir[i]--;
            }
            for (int j = 0; j < 4; j++) {
                for (int k = 1; k <= this.bomblength; k++) {
                    if (CanGo[posx + dir[i][0] + k * dir[j][0]][posy + dir[i][1] + k * dir[j][1]]) {
                        casedir[i]--;
                    }
                }
            }
        }

        int maxdir = 0;//�� �� �ִ� ����� �ִ��� ����
        int maxmap = 0;
        for (int i = 0; i < 4; i++) {
            if (maxmap < casedir[i]) {
                maxmap = casedir[i];
                maxdir = i;
            }
        }

        for (int j = 0; j < 4; j++) {
            for (int k = 1; k <= this.bomblength; k++) {
                if (CanGo[posx + dir[maxdir][0] + k * dir[j][0]][posy + dir[maxdir][1] + k * dir[j][1]] == true) {
                    maxmap--;
                }
                CanGo[posx + dir[maxdir][0] + k * dir[j][0]][posy + dir[maxdir][1] + k * dir[j][1]] = false;
            }
        }

        if (maxmap <= 0) return;//�� ���� ������ ��ź�� ���� �ʴ´�.
        int minlength = 30;
        int minx = posx;
        int miny = posy;
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (!CanGo[i][j])
                    continue;
                if (i == posx && j == posy)
                    continue;
                if (minlength > Math.abs(i - posx) + Math.abs(j - posy)) {
                    minx = i;
                    miny = j;
                }

            }
        }
        ToOtherCoord(minx, miny);
    }

}
/*
package crazyarcade.character;
import java.util.Random;
import crazyarcade.gui.ArcadeGamePanel;
import crazyarcade.Constant;
import java.util.LinkedList;
import java.util.Queue;
class Point{
	int x;
	int y;
	public Point(int x,int y) {
		this.x=x;
		this.y=y;
	}
}
public class Enemy {
	int dir[][]= {{1,0},{0,1},{-1,0},{0,-1}};//������ ��Ÿ���� �迭
	int nx,ny;
	int bomblength=1;
	int speed=6;
	boolean CanGo[][]=new boolean[15][15];//�� �� �ִ� ���
	int [][] mapBlockNum;
	Random rand=new Random();
	
	Queue<Point> queue=new LinkedList<Point>();//Ž���� ���� ť ����
	public void start() {
		mapBlockNum=ArcadeGamePanel.mapBlockNum;
	}
	public void ToNextCoord(int x,int y,int nx,int ny) {
		//x,y���� ��ǥ nx,ny �̿��� ��ǥ
		
		
	}
	public void ToOtherCoord(int nx,int ny){
		
	}
	public void RandomWalk() {//�������� �� �� �ִ� ������� ����
		for(int j=1;j<=3;j++) {
			int i=rand.nextInt(4);
			if(mapBlockNum[this.nx+dir[i][0]][this.ny+dir[i][1]]==0) {
				this.nx+=dir[i][0];
				this.ny+=dir[i][1];
				break;
			}
		}
	}
	
	public void SearchDir() {
		int posx=this.nx;
		int posy=this.ny;
		int blockcount=1;//�� �� �ִ� ��� ����
		int casedir[]=new int[4];// �� ���⿡ ǳ�� ������ �� ���� �� �� �ִ� ��� ��
		int qx,qy;
		for(int i=0;i<15;i++) {
			for(int j=0;j<15;j++) {
				CanGo[i][j]=false;
			}
		}
		
		CanGo[posx][posy]=true;
		queue.add(new Point(posx,posy));
		while(!queue.isEmpty()) {
			qx=queue.peek().x;
			qy=queue.peek().y;
			queue.poll();
			for(int i=0;i<4;i++) {
				if(mapBlockNum[qx+dir[i][0]][qy+dir[i][1]]==0&&!CanGo[qx+dir[i][0]][qy+dir[i][1]]) {
					CanGo[qx+dir[i][0]][qy+dir[i][1]]=true;
					queue.add(new Point(qx+dir[i][0],qy+dir[i][1]));
					blockcount++;
				}
			}
		}
		
		for(int i=0;i<4;i++) {
			casedir[i]=blockcount;
			if(CanGo[posx+dir[i][0]][posy+dir[i][1]]) {
				casedir[i]--;
			}
			for(int j=0;j<4;j++) {
				for(int k=1;k<=this.bomblength;k++) {
					if(CanGo[posx+dir[i][0]+k*dir[j][0]][posy+dir[i][1]+k*dir[j][1]]) {
						casedir[i]--;
					}
				}
			}
		}
		
		int maxdir = 0;//�� �� �ִ� ����� �ִ��� ����
		int maxmap = 0;
		for(int i=0;i<4;i++) {
			if(maxmap<casedir[i]) {
				maxmap=casedir[i];
				maxdir=i;
			}
		}
		
		for(int j=0;j<4;j++) {
			for(int k=1;k<=this.bomblength;k++) {
				if(CanGo[posx+dir[maxdir][0]+k*dir[j][0]][posy+dir[maxdir][1]+k*dir[j][1]]==true) {
					maxmap--;
				}
				CanGo[posx+dir[maxdir][0]+k*dir[j][0]][posy+dir[maxdir][1]+k*dir[j][1]]=false;
			}
		}
		
		if(maxmap<=0) return;//�� ���� ������ ��ź�� ���� �ʴ´�.
		int minlength=30;
		int minx=posx;
		int miny=posy;
		for(int i=0;i<15;i++) {
			for(int j=0;j<15;j++) {
				if(!CanGo[i][j])
					continue;
				if(i==posx&&j==posy)
					continue;
				if(minlength>Math.abs(i-posx)+Math.abs(j-posy)) {
					minx=i;
					miny=j;
				}
				
			}
		}
		ToOtherCoord(minx,miny);
	}
}
*/