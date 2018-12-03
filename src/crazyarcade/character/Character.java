package crazyarcade.character;

public abstract class Character {
    public abstract void ToNextCoord(int x, int y, int nx, int ny);

    public abstract void ToOtherCoord(int nx, int ny);

    public abstract void RandomWalk();
}
