
import java.util.List;
public class modul06prac {
    public static void main(String[] args) {
        


    }
}





// Prototype
interface IDeepClonable<T>{
    T DeepClone();
}



class Character implements IDeepClonable<Character>{
    public String name;
    public double health;
    public double strength;
    public double agility;
    public double intellegence;

    public Weapon weapon;
    Armor armor;
    List<Skill> skill;

    Character(String name, double health, double strength, double agility, double intellegence, Weapon weapon, Armor armor, List<Skill> skill){

    }

    @Override
    public Character DeepClone(){
        
    }

}

class Weapon implements IDeepClonable<Weapon>{
    public String Name;
    public int Damage;

    Weapon(String Name, int Damage){
        this.Name = Name;
        this.Damage = Damage;

        @Override
        public Weapon DeepClone(){
            return new Weapon(this.Name, this.Damage);
        }
    }
}
class Armor implements IDeepClonable<Armor>{
    public String Name;
    public int Defense;

    Armor(String Name, int Defense){
        this.Name = Name;
        this.Defense = Defense;
    }

    @Override
    public Armor DeepClone(){
        return new Armor(this.Name ,this.Defense);
    }
}


class Skill implements IDeepClonable<Skill>{
    public String Name;
    public String SkillType;

    Skill(String Name, String SkillType){
        this.Name = Name;
        this.SkillType = SkillType;
    }

    @Override
    public Skill DeepClone(){
        return new Skill(this.Name, this.SkillType);
    }
}