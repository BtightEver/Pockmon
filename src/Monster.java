import javax.swing.*;

public class Monster {
    private String name;
    private JLabel fightLabel;
    private JLabel monsterLabel;
    protected int blood;
    protected int currentBlood;
    protected int ATK;
    private int x;
    private int y;
    private Skill skill1;
    private Skill skill2;
    public Monster(JLabel fightLabel,JLabel monsterLabel,int blood,int currentBlood,Skill skill1,Skill skill2,int ATk,String name,int x,int y){
        this.blood=blood;
        this.currentBlood=currentBlood;
        this.fightLabel=fightLabel;
        this.monsterLabel=monsterLabel;
        this.skill1=skill1;
        this.skill2=skill2;
        this.ATK=ATk;
        this.name=name;
        this.x=x;
        this.y=y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setFightLabel(JLabel fightLabel) {
        this.fightLabel = fightLabel;
    }

    public String getName() {
        return name;
    }

    public void setMonsterLabel(JLabel monsterLabel) {
        this.monsterLabel = monsterLabel;
    }

    public void setBlood(int blood) {
        this.blood = blood;
    }

    public void setCurrentBlood(int currentBlood) {
        this.currentBlood = currentBlood;
    }

    public void setSkill1(Skill skill1) {
        this.skill1 = skill1;
    }

    public void setSkill2(Skill skill2) {
        this.skill2 = skill2;
    }

    public void setATK(int ATK) {
        this.ATK = ATK;
    }

    public int getATK() {
        return ATK;
    }

    public int getBlood() {
        return blood;
    }

    public int getCurrentBlood() {
        return currentBlood;
    }

    public JLabel getFightLabel() {
        return fightLabel;
    }

    public JLabel getMonsterLabel() {
        return monsterLabel;
    }

    public Skill getSkill1() {
        return skill1;
    }

    public Skill getSkill2() {
        return skill2;
    }
}
