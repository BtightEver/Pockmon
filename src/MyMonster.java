import javax.swing.*;

public class MyMonster extends Monster {
    private boolean isGetElectricBall=false;
    private int level=1;
    private int Exp=0;
    private static MyMonster ElectricMouse=new MyMonster(null,null,100,100,null,null,5,null,0,0);
    private MyMonster(JLabel fightLabel, JLabel monsterLabel, int blood, int currentBlood, Skill skill1, Skill skill2,int ATK,String name,int x,int y)
    {
        super(fightLabel, monsterLabel, blood, currentBlood, skill1, skill2,ATK,name,x,y);
    }
    public static  MyMonster getInstance(){
        return  ElectricMouse;
    }
    public boolean isGetElectricBall() {
        return isGetElectricBall;
    }
    public void getGetElectricBall(){
        isGetElectricBall=true;
    }
    public int getLevel() {
        return level;
    }
    public int lookExp(){
        return Exp;
    }
    public void getExp(){
        Exp+=50;
        if(Exp>=100){
            Exp-=100;
            upLevel();
        }
    }
    public void upLevel(){
        level++;
        blood+=50;
        currentBlood=blood;
        ATK+=2;
    }
}
