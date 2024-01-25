public class Skill {
    private String skillName;
    private int power;
    public Skill(){}
    public Skill(String skillName,int power){
        this.skillName=skillName;
        this.power=power;
    }
    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }
    public void setPower(int power) {
        this.power = power;
    }

    public int getPower() {
        return power;
    }

    public String getSkillName() {
        return skillName;
    }
}
